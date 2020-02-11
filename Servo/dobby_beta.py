import RPi.GPIO as GPIO
import time

#5V(red) -> pin 4
#GND(black) -> pin 6
#servo motor -> pin 11 (GPIO17)

servoPIN = 11

GPIO.setmode(GPIO.BOARD)

GPIO.setup(servoPIN, GPIO.OUT)

p = GPIO.PWM(servoPIN, 50)

#servo having 20ms cycles, dc=0.5/20 X100 = 2.5% ->90degree

p.start(5) #initialization, 0degree

try:
    while True:
        p.ChangeDutyCycle(7.5)  # turn towards 90 degree
        time.sleep(1) # sleep 1 second
        p.ChangeDutyCycle(5)  # turn towards 0 degree
        time.sleep(1) # sleep 1 second
        p.ChangeDutyCycle(10) # turn towards 180 degree
        time.sleep(1) # sleep 1 second 

except KeyboardInterrupt:
    p.stop()
    GPIO.cleanup()