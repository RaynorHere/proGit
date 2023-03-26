CS455 (Distributed Systems); Project 2 (Identity Server); James Crowell, Jackson Morton (Team 10); Spring (2022)

[Video Demo](https://youtu.be/tHnv8tlA2TY)

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
For perhaps the first time, we have here a submitted program that requires no compilation effort on the part of the tester before running. 
Pulling from the repository should set the prospective user up with the requisite .jar file, P2Packaged.jar, located in the root/p2 folder, which is
where it must be in order to find its required files to function properly (the keystore, truststore, and certificate files), Because of this, "building" is 
taken care of entirely by that GIT PULL command. <br><br>

For the purposes of running, the base formula is: <br>
1) Navigate to base "p2" folder, where the P2Packaged.jar file is located
2) From this directory, all executions, regardless of modality, will begin with:<br>
  $ java -cp P2Packaged.jar [further commands]
<br>

From here, the user will specify which part of the package to execute. Example server execution: <br>

&emsp; &emsp; <code> $ java -cp P2Packaged.jar server.IdServer --numport 5005 <br> </code>

This will start the Id Server on port 5005. If the --numport option is omitted, the program will default to port 1099. <br>
Another option open to users is the --verbose tag, which will cause the server to output text on the terminal to indicate responses to client requests. <br>

To run the client: <br>

&emsp; &emsp; <code> $ java -cp P2Packaged.jar client.IdClient -s/--server [servername] <-n/--numport [port#]> [query] </code> <br>

With the client, the user has many more options available to them. If it needs be said, a user must start a Server instance before attempting a Client instance. 
Additionally, Client instances require the -s or --server option submitted with them, along with "localhost" in order to function. 
Beyond that, a user MAY specify a different port if the server is running on a non-default port, but this is not required. Valid queries are as follows: <br>
(NOTE: items enclosed in square brackets [ ] are mandatory arguments; items in angular brackets < > are optional) <br>

-c/--create  [login name] < real name > <--password [userpassword]> <br>
&emsp; This will create new login credentials on the server. Note that at minimum, users must include a login name.
 If the user omits their real name, &emsp; &emsp; the system will pull their computer's local user name and substitute it in. Additionally, passwords are not required, though are recommended <br>

-l/--lookup [login name] <br>
&emsp; Queries the database to look for a specific user registered under a login name <br>

-r/--reverse-lookup [UUID]  <br>
&emsp; Functions as lookup, but searches based on Universally Unique Identifier instead of login name<br>

-m/--modify [old login] [new login] < --password [userpassword] > <br>
&emsp; Changes a user's login information, if it exists in the database, to the value specified in place of |new login|. If the account has a password, it will
&emsp; be required to successfully complete the operation<br>

-d/--delete [login] < --password [userpassword] > <br>
&emsp; Removes a user from the database, if extent (and if submitted password is correct) <br>

-g/--get |users / uuids / all| <br>
&emsp; Returns all items in the server's database according to user query. Note that these options are mutually exclusive (i.e., users can submit a <br>
&emsp; request for the database to display Usernames OR UUIDs OR both by way of the "all" option)<br>

Culminating all of this, an example create-user command, with login name "Raynor", real name "JimmyC", and password "password1", submitted to a server running on port 5005 would look as such: <br>
&emsp; <code> $ java -cp P2Packaged.jar client.IdClient -s localhost -n 5005 -c Raynor JimmyC -p password1 </code> <br>




## TESTING
First and foremost, we did what we call "brute force testing", which is taking each individual option, submitting a correct version of the command, 
and then trying every possible permutation of the command, with each of its arguments incorrect, one at a time. That is, to test the create command, 
we would ensure both the long and short options, --create and -c respectively, function assuming the same values submitted for login,
real name, and password (also testing the -p/--password option "names"), and then we would omit those options, login name, real name, and password,
permutatively, to ensure that A) options that were not required were substituted correctly (i.e., omitting user real names led to the system
properly collecting local system username data and submitting that in its place) and B) options that _were_ required being omitted (in this case, 
login) led to the correct Exception being thrown and a clean Client exit. This was performed per argument, per option, until the entire list had been tested as 
exhaustively as we could reasonably manually test it. For commands that risk collisions, such as --create, we first tested asynchronous collisions
(trying to create a new user with the same username as already exists in the database) manually, and then to test synchronized collision handling (two
users trying to create two accounts with the same name at the same time), we wrote a Java class whose sole purpose was to submit the command at the 
exact same time in order to ensure the server would deny the request.


## DESIGN NOTES
P2 represents a considerable step up in terms of learning complexity for Java projects in the CS program. Not so much in terms of the coding itself;
 we're pretty used to adapting to that sort of requirement. Instead, we both felt compelled to change our general-use IDE for this project specifically 
because of package management. Neither of us has much experience with package management (as our previous submissions have shown), and this
project has pushed us to learn how to use Maven and JAR files, before which we made the change from VSCode to IntelliJ. What minor experience
we have had with IntelliJ before (the Agile summer CSHU course) left us wondering why we would need so many bits and pieces to our
dev environment. Suffice it to say, having to make use of more complicated Java programs, which are made up of multiple different packages and 
external libraries, we now understand just what developmental niche advanced IDEs like IntelliJ occupy. This was fully clinched when we integrated
the Apache Commons CLI, or attempted to, in VSCode; it proved maddeningly difficult. By switching to IntelliJ, we found we could offload 
much more of the work onto the computer, which made managing and maneuvering infinitely easier.<br><br>
On top of this, there was also the trial of separating the program into its Client, Server, and Shared packages, and then figuring out how to 
run the program from the command line. We both feel as though the days of running JAVAC *.JAVA; JAVA [program name] are well behind us, if this project
is any indication. It was a bit of a rude awakening, and led to some backloaded difficulty to deal with at the last minute, but what we learned
along the way allowed us to basically rewrite the entire project from the ground up, refactoring it considerably and making it much cleaner, as well 
as allowing us to include the external ApComCLI library quite easily, _and_ produce a single JAR file which contains the sum total of our program and
all its modalities, making it that much less intimidating for a prospective new user (i.e., not a professor, if we were actually trying to create a product for
NON-engineers/NON-gineers), as well as just easier on the eyes/to deal with on the back end. <br><br>
There's no denying that this was probably more stressful than we expected it to be going in, but we genuinely feel like our respective abilities to use
and maneuver around Java programming have risen to the next tier, and that really makes it all worthwhile. <br><br>
That's assuming, of course, our end product grades well and doesn't just turn out to be a complete runaway freight train of raw sewage. We look
forward to finding out!