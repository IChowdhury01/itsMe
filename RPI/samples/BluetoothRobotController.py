from bluetooth import *
import RPi.GPIO as GPIO
import time

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)


#*****************GPIO setup ***************************
GPIO.setup(3,GPIO.OUT) #Left wheel motor control
GPIO.setup(5,GPIO.OUT) #Left wheel motor control
GPIO.setup(7,GPIO.OUT) #right wheel motor control
GPIO.setup(11,GPIO.OUT) #right wheel motor control

#Motor stop/brake
GPIO.output(3,0)
GPIO.output(5,0)
GPIO.output(7,0)
GPIO.output(11,0)
 

while True:

        server_sock=BluetoothSocket( RFCOMM )
        server_sock.bind(("",PORT_ANY))

        server_sock.listen(1)
        print " listening "

 try:
                client_sock,address = server_sock.accept()
                print "Accepted connection from ",address
               

                 while True:

                        
                        try:
                                data = client_sock.recv(1024)
                                #print "received  %s" % data
                               

                                c,data1 = [str(val) for val in data.split(':')]
                                x, y, z = [int(val) for val in data1.split(',')]
                                
                                print "c,x,y,z",c,x,y,z
                                if c==0:
                                        if x>0 and y>0:    # Move front - Depends on your Motor assigned
                                                GPIO.output(3,0)
                                                GPIO.output(5,0)
                                                GPIO.output(7,1)
                                                GPIO.output(11,1)
                                        elif x<0 and y<0: # Move back- Depends on your Motor assigned
                                                GPIO.output(3,1)
                                                GPIO.output(5,1)
                                                GPIO.output(7,0)
                                                GPIO.output(11,0)
                                        elif x>0 and y<0: # Move right - Depends on your Motor assigned
                                                GPIO.output(3,0)
                                                GPIO.output(5,1)
                                                GPIO.output(7,1)
                                                GPIO.output(11,0)
                                        elif x<0 and y>0: # Move left - Depends on your Motor assigned
                                                GPIO.output(3,1)
                                                GPIO.output(5,0)

                                                GPIO.output(7,0)
                                                GPIO.output(11,1)
                                        else:                 # Stop 
                                                GPIO.output(3,0)
                                                GPIO.output(5,0)
                                                GPIO.output(7,0)
                                                GPIO.output(11,0)
                                                time.sleep(1)
                                elif c==1:

                        except IOError:
                                print "connection disconnected"
                                break
                        except KeyboardInterrupt:
                                client_sock.close()
                                sys.exit()
        except KeyboardInterrupt:
                server_sock.close()
                sys.exit()