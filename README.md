To run the program:    

Ensure the code is on rrpc01 and rrpc02 servers  
Ensure rmiregistry is running on port 4444
In rrpc02, go to the A3 directory (which has the code) and type:
```shell
make server
``` 
The server should be running
In rrpc01, go to the A3 directory (which has the code) and type:
```shell
make client
```
Start using the program
The pdf file has pictures containing the program running

Note:  
When running rmiregistry command, if you get port already in use error, open another terminal and ssh to rrpc02 and continue running the program by writing `make server`