# Homework Number

* Author: James "Crowley" Crowell
* Class: CS455 [Distributed Systems] Section #001


## Overview

Modify a server that provides the time to a client on request to only accept one client at most every 5 seconds.

## Building the code

Users need only have TimeServer.java and TimeClient.java in the same directory and run 

    $ javac *.java

Compilation successful, first spin up TimeServer with

    $ java TimeServer

TS is programmed to automatically create a local server. Now, in a second (or split) terminal, run

    $ java TimeClient localhost 5005

At this point, the server will connect to the client, dispense the time, sever the connection, and lock for the rest of its five-second allotment duration. If one rapidly schedules client connections, one may initially connect more rapidly, but successive attempts will space out to exactly five seconds apiece. This is most easily tested (assuming lack of script) by simply pressing the UP arrow key on the client terminal and queueing Clients as quickly as one can.


## Testing 

I basically outlined my testing strat in the usage instructions: I literally spun up a server and then clients as quickly as I could with keyboard shortcuts to PROVE it was spacing out to five seconds. And I had to get more obsessive about that with this one than I expected, which I will explain further in the next section.


## Reflection

I had initially thought I had this program solved by containing the NOTIFY inside the task that the lock locking spawns - effectively creating an entire new task and lock every time a connection was made. This perfectly spaced successives to five seconds each, but Sena brought to my attention while we were discussing it that the assignment shows the connection of two clients in quick succession and THEN spacing out to five seconds. Not only that, it also shows the Server stating the timer has expired twice in succession (i.e., without discrete connection per statement). That was when I realized that the Server is on its OWN rhythm, which is made UP of five second slices. That was when I realized we needed to use the scheduleAtFixedRate() method from Timer, and we had to do a little re-wiring. 
It still proved to be a simple fix, which was a relief. Always hate to have the feeling of victory stolen away. Obviously, I'm not going to be COMPLETELY confident I did it right until I see a grade TELLING me so, but I feel good about this one. I think I'm going to write up a script that spawns clients across multiple terminals and see if that makes a difference, but I think the theory is sound enough.
Additionally, I don't know if we were MEANT to have our Server say "connection available" every five seconds, but I hated the way it looked and how it filled up the server screen, so while my Server IS cycling through time slices every five seconds, it only states that it has reached the end of one if it received a connection during said slice. It's cleaner this way.

