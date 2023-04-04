CS455 (Distributed Systems); Project 1 (Chat Server); James Crowell, Jackson Morton (Team 10); Spring (2022)

[Video Demo](https://youtu.be/0EP68hPlZjk)

## PROJECT STRUCTURE 
 - Broadcast.java      source file
 - ChatClient.java     source file
 - ChatServer.java     source file
 - JoinRequest.java    source file
 - LeaveRequest.java   source file
 - ListRequest.java    source file
 - NameRequest.java    source file
 - QuitRequest.java    source file
 - Request.java		   source file
 - ServerList.java     source file
 - StatsRequest.java   source file
 - README			   this file

## BUILDING/RUNNING
We considered submitting the scripts we used as developers to spin up a server on a given port, as well as 2-3 clients for testing purposes, but we realized that would require foreknowledge of what an evaluator was running as an OS and the terminals they have installed at time of evaluation. Therefore, we elected to include full instructions.

To start, after downloading the repository, one should be looking at a directory containing the folders p1, p2, p3, and p4. Change directory into p1 and then 
From that directory:
	$ make
This will compile all the java code in the p1 folder. 
Now, 

	$ java ChatServer -p <port#> -d <debug-level>

		
With the server now running, start up to four other terminals for clients. 4 is currently the threadpool limit coded into the server in order to prevent denial of service and other overload-based attacks. In each of however many client terminals you have spun up, navigate to that same p1 directory and run:
	$ java ChatClient
This will initiate an instance of the ChatClient program in that terminal. Now, you can connect to the server, the command for which is overloaded. 
	$ /connect <port number>
Will connect the client to the server on the port specified when the server was run. If the user does not supply a port number with the /connect command, the client will request one. The port number must be an integer value, and it must match an AVAILABLE port (in this case, that means the port the server was run on).

With connection established, the commands open to users are
(Asterisks indicates an overloaded command, meaning the command can either be submitted on its own [causing the client to ask for the next part of 		the command], or the user can submit the command with more information and complete the action in a single stroke
	EX: "/nick jimmy c is crazy see" will submit a request to change the user's handle to JimmySeeIsCrazySee, while "/nick"
	by itself will prompt the user for a new name, whereupon they can enter "jimmy c is crazy see", which will also result in the above):
	
/quit : Disconnects from server, shuts down client

/nick** : Changes the handle both the user and other users on the server see. 

/list	: Shows the current channels available and the users located in those channels

/join** : Joins a channel, allowing a user to receive messages from users on that channel, as well as broadcast to it

/leave** : Leaves a channel, meaning users will no longer receive messages from it, and can also no longer broadcast to it

/help : Displays the basic commands, as this README presents them

/stats : Requests statistics from server; IP Address, Port #, # users currently connected, # channels currently available, Time online

/<channel name> : Sets user's current channel for broadcast purposes. That is, a user can belong to (and receive messages from) any number of channels,
	but can broadcast to only one. "/<channel name>" sets the client to broadcast to that channel. Note that users can set their broadcast 
	frequency to whatever, but the server will ensure they've picked a valid channel name and alert them otherwise. Additionally, users can
	attempt to broadcast to channels they are not members of, but the server will block them until they /join the channel properly
	
Anything else entered by the user (including improperly-typed commands!) will be assumed to be chat messages and relayed by the server to other clients (if they are part of the channel the sender is broadcasting to).
				
				
If a user is running a client and has not connected to a server yet, their options consist of:

/connect** : Connects a user to a server, pending the user providing a proper, viable port number

/help : As above, but reflects the reduced commands a disconnected client is able to run

/exit : Shut down the client



## TESTING
This being a chat server program, we weren't availed to a testing script, nor were we really able to make any. Something else, like a program that's designed to accept input, process it, and output something like a calculation is easier to test through scripting, because there is a binary state of correct/incorrect. Because of how chat programs work, and because it's basically just forwarding whatever you give it to other people (outside of prescribed command functions), we as developers had to actively predict ways users could misuse or make mistakes with our program. To that end, we tested each individual function on its own as we wrote it. That is, upon settling on the /join command, we discussed the order of operations from client to server, where we would delineate them (that is, "user runs /join on client, enter. The client asks for more information; the channel name to join. The client makes that into a JoinRequest object once the user hits enter again, and sends it to the server, who will respond with the following". That was our general build pattern for new features.

Past that point, as we changed features, we tested them in vacuums until we were satsified we'd covered all the likely outcomes. To keep with the above example, when adding the overloaded function to the /join command, it was important to ensure that the program can take in any permutation of valid letters making up a viable channel name (which is mercifully easy in Java with the .toLowerCase and .trim functions) and, more importantly, that the server can handle erroneous input without crashing. Once we are satisfied that the client can accept proper input correctly, and the server can gracefully respond to _improper_ input, we move on to the next function.

This was the pattern we developed each of the commands with in order to test both their functionality and their presentation to the user (much time was spent on formatting and reporting-to-user presentation, on both ends. Front-end polish is an absolute must in any software design worth its salt), and we simply went down the list of required commands by the assignment. This approach, combined with the roughly two years we've been working together in the CS program made for efficient, pleasant, creative dev process. While we will never be satisfied that the program is "perfect" or "done", we accept that there must be a time when we stop working on it and submit it for review. I would not be surprised if we at some point opened it up again to fine-tune it farther, just for the love of the game.



## DESIGN NOTES
The class design is pretty simple. The two main classes are ChatClient and ChatServer. They are essentially designed for two different end users (a client and a server). The goal of this project is to simulate an IRC chat program (the primary inspiration I (Jackson) took was the chat system used in World of Warcraft (Hey it used to be a good game) [Proofreader Jim here, popping in just to say that EverQuest ate World of WarCraft's lunch every day for ten years before WoW even showed up to the party]). The ChatClient takes input from the user through the terminal and parses it. The ChatClient program then looks for specific patterns or keywords that are associated with known commands. From the user input, it derives the appropriate request object to send to the server. The ChatServer will then receive the object and determine what kind of request the that object represents and fulfills that request if its knows what it is. If the start of the user input does not match any particular keywords or patterns then it will assume that the input is intended to be a message. This will result in a Broadcast object being created. The fields for a Broadcast object are a name and body: the name is essentially a channel designation that will be matched with an ArrayList of client sockets that represent that channel on the server. When the ChatServer receives this Broadcast object it will parse the name of the channel and send the Broadcast object to each ServerConnection (an inner class of the server that is made for each client thread) inside ArrayList corresponding to that channel name. The ChatClient is also multithreaded and uses a thread for sending requests and a thread for listening to the server. This allows are ChatClient to achieve full-duplex communication with the ChatServer.

Developing this project was a lot of fun, as well as genuinely satisfying. We began developing this project using a step by step methodology of integrating small changes at a time. This allowed us to be able to have a better understanding of what was happening at each section of the program and what changes needed to be made before continuing on to the next feature. We split the project directly in half and had one person implement the ChatClient class while the other person implemented the ChatServer class (as well as each developing half of the disparate Objects that represent the different requests users can make). For each feature we added, we implemented the requirements for the client and server at the same time. This made debugging a lot easier since we could quickly adjust a piece of code as needed. Working face-to-face was also something that we found worked really well as opposed to previous projects that had us working over zoom.
