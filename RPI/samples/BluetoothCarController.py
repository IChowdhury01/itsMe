#!/usr/bin/python

import RPi.GPIO as GPIO
from Adafruit_PWM_Servo_Driver import PWM
import time
import signal
import sys
import pygame
import os

# Constants
FORWARD_GPIO = 17
BACKWARD_GPIO = 21
PWM_MAX = 2**12-1 # 12bits -> 0..4095
MOTOR_PWM = 0
STEERING_SERVO_PWM = 15
LIGHT1_GPIO = 23
LIGHT2_GPIO = 24
TEST_SERVO_ON_START = False
TEST_LEDS_ON_START = False
JS_STEERING_AXIS = 0
JS_ENGINE_AXIS = 3
JS_LIGHT1_BUTTON = 14
JS_LIGHT2_BUTTON = 15
JS_SHUTDOWN_BUTTON = 13 # TODO Need to check what button this is!
SHUTDOWN_BUTTON_PRESS_TIME = 4 # In seconds

# Max switching frequency of the TB6612FNG motor driver is 100KHz
# Adafruit PWM driver - Adjustable frequency PWM up to about 1.6 KHz
# Analog servos typically run at ~60 Hz updates
PWM_FREQUENCY = 60

# TowerPro SG-5010
# weight- 38g
# Dimension 40.2*20.2*43.2mm
# Stall torque 5.5kg/cm(4.8V);   6.5kg/cm(6V);
# Operating speed 0.2sec/60degree(4.8v);   0.16sec/60degree(6v)
# Operating voltage 4.8-6V
# Temperature range 0..55 deg C
# Dead band width 10us
# Position "0" / middle (1.5ms pulse), "90" / right (~2ms pulse), "-90" / left (~1ms pulse)

# Servo calibration values
SERVO_MIN = 230                         # Min pulse length out of 4096 (1ms pulse @ 60Hz = 245)
SERVO_MAX = 490                         # Max pulse length out of 4096 (2ms pulse @ 60Hz = 491)
SERVO_MID = (SERVO_MIN + SERVO_MAX) / 2 # (1.5ms pulse @ 60Hz = 368)
SERVO_RANGE = SERVO_MAX - SERVO_MID

# Initialise the PWM device using the default address
pwm = PWM(0x40, debug=True)

def signalHandler(signal, frame):
    print("You pressed Ctrl-C")
    cleanUpAndExit()

# Register a signal handler for safe clean-up
signal.signal(signal.SIGINT, signalHandler)

def setEngineSpeed(value):
    """Set engine motor speed
    Arguments:
    value -- range from -1 (forwards) to 1 (backwards)
    """
    speed = min(int(abs(value)*PWM_MAX), PWM_MAX)
    forward = value <= 0
    print("setEngineSpeed(%f), forward=%i, speed=%f)" % (value, forward, speed))
    if (forward):
        GPIO.output(BACKWARD_GPIO, False)
        GPIO.output(FORWARD_GPIO, True)
    else:
        GPIO.output(BACKWARD_GPIO, True)
        GPIO.output(FORWARD_GPIO, False)
    setPWM(MOTOR_PWM, 0, speed)

def setSteering(value):
    """Change the steering servo position
    Arguments:
    value -- range from -1 to 1 for -90 to 90 degrees movement
    """
    servo_pos = int(SERVO_MID + SERVO_RANGE*value)
    print("setSteering(%f), setting servo pos to %i" % (value, servo_pos))
    setPWM(STEERING_SERVO_PWM, 0, servo_pos)

def setServoPulse(channel, pulse):
    """Set the pulse length in seconds
    E.g. setServoPulse(0, 0.001) sets a ~1 millisecond pulse width. It's not precise
    Good site on PWM - http://ebldc.com/?p=48. Note T-OFF in that article is different to that used by the Adafruit library
    Arguments:
    channel -- the PWM channel (0..15)
    pulse -- pulse length in seconds
    """
    pulseLength = 1000000                   # 1,000,000 us per second
    pulseLength /= PWM_FREQUENCY            # e.g. 60 Hz
    print("%d us per period" % pulseLength)
    pulseLength /= PWM_MAX+1
    print("%d us per bit" % pulseLength)
    pulse *= 1000                           # Convert to micro-sec
    pulse /= pulseLength
    setPWM(channel, 0, pulse)

def setPWM(channel, on, off):
    """Sets the start (on) and end (off) of the high segment of the PWM pulse on a specific channel
    Note on + off must be < 4095
    Arguments:
    channel -- The PWM channel (0..15)
    on -- when the signal should transition from low to high (12bits hence 4096). Valid values are 0..4095
    off -- when the signal should transition from high to low (12bits hence 4096). Valid values are 0..4095
    """
    pwm.setPWM(channel, on, off)

def testServo():
    # Change speed of continuous servo on channel O
    print("Setting servo to mid")
    setPWM(STEERING_SERVO_PWM, 0, SERVO_MID)
    time.sleep(1)
    print("Setting servo to min")
    setPWM(STEERING_SERVO_PWM, 0, SERVO_MIN)
    time.sleep(1)
    print("Setting servo to max")
    setPWM(STEERING_SERVO_PWM, 0, SERVO_MAX)
    time.sleep(1)
    print("Setting servo to mid")
    setPWM(STEERING_SERVO_PWM, 0, SERVO_MID)

def testLEDs():
    gpio = LIGHT1_GPIO
    print("Testing GPIO", gpio)
    print("LED on for GPIO", gpio)
    GPIO.output(gpio, True)
    time.sleep(1)
    print("LED off for GPIO", gpio)
    GPIO.output(gpio, False)
    time.sleep(1)

    gpio = LIGHT2_GPIO
    print("Testing GPIO", gpio)
    print("LED on for GPIO", gpio)
    GPIO.output(gpio, True)
    time.sleep(1)
    print("LED off for GPIO", gpio)
    GPIO.output(gpio, False)
    time.sleep(1)

def init():
    # Set frequency in Hz (range is 40 to 1000)
    pwm.setPWMFreq(PWM_FREQUENCY)

    # Initialise the GPIO output channels
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(FORWARD_GPIO, GPIO.OUT)
    GPIO.setup(BACKWARD_GPIO, GPIO.OUT)
    GPIO.setup(LIGHT1_GPIO, GPIO.OUT)
    GPIO.setup(LIGHT2_GPIO, GPIO.OUT)

    setEngineSpeed(0)
    setSteering(0)

    pygame.init()
    pygame.joystick.init()
    j = pygame.joystick.Joystick(0)
    j.init()

def mainLoop():
    init()

    if (TEST_SERVO_ON_START): testServo()

    if (TEST_LEDS_ON_START): testLEDs()
    
    sd_button_down_time = None
    while (True):
        for event in pygame.event.get():
            if (event.type == pygame.QUIT):
                break
            elif (event.type == pygame.JOYBUTTONDOWN):
                if (event.button == JS_LIGHT1_BUTTON):
                    GPIO.output(LIGHT1_GPIO, True)
                elif (event.button == JS_LIGHT2_BUTTON):
                    GPIO.output(LIGHT2_GPIO, True)
                elif (event.button == JS_SHUTDOWN_BUTTON):
                    sd_button_down_time = time.time()
            elif (event.type == pygame.JOYBUTTONUP):
                if (event.button == JS_LIGHT1_BUTTON):
                    GPIO.output(LIGHT1_GPIO, False)
                elif (event.button == JS_LIGHT2_BUTTON):
                    GPIO.output(LIGHT2_GPIO, False)
                elif (event.button == JS_SHUTDOWN_BUTTON):
                    if (sd_button_down_time != None and time.time() - sd_button_down_time > SHUTDOWN_BUTTON_PRESS_TIME):
                        # Windows
                        #os.system("shutdown")
                        # Linux
                        os.system("poweroff")
                    else:
                        sd_button_down_time = None
            elif (event.type == pygame.JOYAXISMOTION):
                if (event.axis == JS_STEERING_AXIS):
                    setSteering(event.value)
                elif (event.axis == JS_ENGINE_AXIS):
                    setEngineSpeed(event.value)

    cleanUpAndExit()

def cleanUpAndExit():
    setEngineSpeed(0)
    setSteering(0)
    GPIO.cleanup()
    pygame.joystick.quit()
    sys.exit(1)

if __name__ == "__main__":
    mainLoop()