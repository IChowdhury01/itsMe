from bluetooth import *
import time

 

while True:

        server_sock=BluetoothSocket( RFCOMM )
        server_sock.bind(("",PORT_ANY))
        server_sock.listen(1)
        print " listening "
        try:
                client_sock,address = server_sock.accept()
                print "Accepted connection from ",address

                while 1:
                        try:
                                data = client_sock.recv(1024)
#                               if len(data) == 0: break

                                print "received  %s" % data
                                x, y, z = [int(val) for val in data.split(',')]
                                print "received 1st %s" % x
                                print "received 2nd %s" % y
                                print "received 3rd %s" % z
                        except IOError:
                                print "connection disconnected"
                                break
                        except KeyboardInterrupt:
                                client_sock.close()
                                sys.exit()
        except KeyboardInterrupt:
                server_sock.close()
                sys.exit()

