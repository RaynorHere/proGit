CS455 (Distributed Systems); Project 3 (Identity Server Phase 2 - Reliability Replication); James Crowell, Jackson Morton (Team 10); Spring (2022)

[Video Demo]()

## PROJECT STRUCTURE 
SRC/MAIN/JAVA: <br>
CLIENT/<br>
IdClient.Java . . . . Source file: Client <br> <br>

SERVER/<br>
IdServer.java . . . . Source file: Driver class of Server <br>
IdImpl.java . . . . Source file: Implementation of Server <br>
User.java . . . . Source file: Implementation of User object (to streamline manipulation of users in database) <br> <br>

SHARED/<br>
Id.java . . . . Source file: Interface for Server object/Client's RMI object access <br><br>

SRC/MAIN/RESOURCES/ <br>
Client_Truststore . . . . Resource file: Enables Client SSL communications with Server <br>
Server_Keystore . . . . Resource file: Enables Server SSL communications with Client <br>
Server.cer . . . . Resource file: Client authentication certification; facilitates Client/Server communications 



## BUILDING/RUNNING





## TESTING



## DESIGN NOTES

