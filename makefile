compile:
	javac common/*.java 
	javac client/*.java
	javac server/*.java

server: compile
	java server/Server

client: compile
	java client/Client

clean:
	rm -f common/*.class
	rm -f client/*.class
	rm -f server/*.class

cleanAll:
	rm -f common/*.class
	rm -f client/*.class
	rm -f server/*.class
	rm -f *.txt
