# TCP server example
import socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(("", 5000))
server_socket.listen(5)


def readAll(inp):


    line = ""
    section = ""

    while 1:

        data = inp.recv(5)
		
        print "data:", data
        index = data.find("@")

        if index>-1:
            section = section + data[0:index+2]
            #residual = data[index+3:]
            break

        else:
            section = section + data


    return section


def handShake(socket, msg):

    socket.send(msg)

    inData = readAll(socket)

    print "msg", msg
    print "inData", inData

    return inData==msg

#    out.flush()


def test():

    version = "v1.01"
    msg = "BEGIN VERSION\n"+version+"\n@\n"
    print "TCPServer Waiting for client on port 5000"

    while 1:

    	client_socket, address = server_socket.accept()
    	print "I got a connection from ", address


        if handShake(client_socket, msg):

            print "OK"


        else:

            print "doesn't match."








def main():
    test()

if __name__ == '__main__':
    main()