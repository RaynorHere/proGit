# Homework 1: PingPong Server/Client
## Author: James "Crowley" Crowell
### CS-445 - Distributed Systems ("Systro")



**Description** 

This program is intended to allow a user to create either a server or a client, connect them, and watch them play synchronized games of Ping Pong, in parallel with each other in the case of multiple games.

**Usage**

Users will first need to create a server for clients to connect to. Once navigated to the folder containing PingPong.java in one's favorite X-Terminal Emulator (Terminator with Black background, Green foreground, ELSE cop), run command

        $ javac *.java

This will compile Ball.java and PingPong.java, which are the two files necessary for successful execution of the program. 
Once here, PingPong can be executed according to the following formula:

        $ java PingPong <server | client> <host name> <port number>

Please note that upon making the choice of server or client, in puts are case-sensitive. Additionally, host name must be a valid host name (this was discovered in testing). For a safe bet, proceed with the name "localhost". Finally, the port number can be almost anything in the range of 1 - 65535, ostensibly, but recommendations are such that users stay above port number 1023, as 0-1023 are classically reserved for various purposes. Our recommendations are ports 5005 or 4649 (for the curious, "4649", because of the first syllables of each of the numbers, forms an approximation of the Japanese phrase "yoroshiku", "nice to meet you").

Therefore, an example server creation statement would read:

        $ java PingPong server localhost 4649

Regardless of choice, the terminal should display a confirmation and proceed to sit at an "empty" line, with the input cursor blinking. The server is therefore created. 

In order to connect, run:

        $ java PingPong client localhost 4649

At which point, the two terminals should begin displaying updates of game progress. The above command can be run in additional terminals to create additional threads of independent games between the same server and a different client. There is no maximum encoded into the program, so users can, theoretically, create so many threads that the server grinds to a halt. For whatever bizarre reason.

## Note
Please also note, a game can be canceled by pressing CTRL+C in a terminal or issuing a KILL command. If executed on a client, the server will acknowledge the disconnection and continue its other games. Executed on a terminal, predictable results will follow.
=======
Please also note, a game can be canceled by pressing CTRL+C in a terminal or issuing a KILL command. If executed on a client, the server will acknowledge the disconnection and continue its other games. Executed on a terminal, predictable results will follow.

When clients connect to the server, the two programs will share a "coin toss". This is a generation of a random generator on each side, which is communicated to the reciprocal side and compared. Please note: the *lesser* of the two values is the "winner". This is intentional. In the *ridiculously* unlikely event of a tie, both server and client will continue generating and comparing numbers with each other until one has the lesser number. This is the initiator of the game, whose "callsign" will be "PING"; its counterpart, of course, being "PONG". The client is by default the beginner of the game, and will pass the ball to the server in the event that the client draws the greater number. The server will then, fittingly, serve the ball to begin the game.
