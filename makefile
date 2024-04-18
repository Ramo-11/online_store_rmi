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
	rm -f *.jar
	rm -f *.txt

jars: compile
	jar cfm Server.jar serverManifest.txt server/Server.class server/*.class common/*.class
	jar cfm Client.jar clientManifest.txt client/Client.class client/*.class common/*.class