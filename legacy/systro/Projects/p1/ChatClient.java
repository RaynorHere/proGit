import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.sql.rowset.JoinRowSet;


class ChatClient {

    // BIOS Variables
    private Socket clientSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ObjectInputStream cObjectIn;
    private ObjectOutputStream cObjectOut;

    // Booleans (Descending order of "depth")
    private boolean talking; // For actual connection
    private boolean onScreen; // Chat Client instantiated

    // Identifier
    private String clientName;
    private String currentChannel;

    // Port variable
    private int port;

    public static void main(String[] args) {

        // This is our go-between; the user will assign a value to this that the program
        // will make sense of
        String orders = "";

        // Grab an input, from which we can fork our different functions
        Scanner keyboard = new Scanner(System.in);

        // Infinite loop trigger, as ever
        boolean alive = true;

        while (alive) {

            // Read this in a Scottish accent; should help you get the reference
            System.out.println("Chat client is live; disconnected.");
            orders = keyboard.nextLine();

            Scanner commandScan = new Scanner(orders);
            String firstWord = commandScan.next();

            // Switchboard lol
            switch (firstWord) {

               
                case "/connect":

                try {
                    int wantPort;
                    // User supply port with /connect
                    if (commandScan.hasNextInt()) {
                        wantPort = commandScan.nextInt();
                        ChatClient chatClient = new ChatClient(wantPort);
                        chatClient.clientRun();
                        alive = false;
                    }

                    else {
                        System.out.println(
                                "Connect! Please provide port number (note: port numbers must be integer values):");

                        // Do not exit this loop until user supplies integer value
                        if (keyboard.hasNextInt()) {
                            wantPort = keyboard.nextInt();                            
                            ChatClient chatClient = new ChatClient(wantPort);
                            chatClient.clientRun();
                        } else {
                            System.out.println(
                                    "Error: port numbers must be integer values greater than 1000 and less than 65537.");
                            break;
                        }

                    }

                    // orders = "";
                    break;
                }
                
                // Remit disconnect to shutdown client
                catch (NoSuchElementException e) {
                    System.exit(0);
                }

                case "/help":
                    System.out.println("Client is currently disconnected, so viable commands are:");
                    System.out.println("/connect <port number>, /connect, /exit, /help");
                    break;

                case "/exit":
                    System.out.println("Client program closing");
                    keyboard.close();
                    alive = false;
                    commandScan.close();
                    break;

                default:
                    System.out.println("Error; unrecognized commands.");
                    System.out.println("Recognizable commands are:");
                    System.out.println("/connect <port number>, /connect, /exit, /help");
                    break;
            }
        }

    }

    // Necessary? I know constructors shouldn't do a WHOLE lot, but this feels
    // barren
    public ChatClient(int port) {
        this.port = port;
    }

    // Where the magic happens
    public void clientRun() {

        // Spit this if we connect
        String confirmationString = "Client Connected";
        // Receive this
        String confirmationResponse;

        // Trigger, entire Client
        onScreen = true;

        // Trigger, connection activity between client/server
        talking = true;

        currentChannel = "normies";

        // Same as above
        Scanner keyboard = new Scanner(System.in);
        String orders = "";

        // Infinite connection loop
        while (onScreen) {

            try {
                // Form connection
                clientSocket = new Socket("localhost", port);
                inputStream = clientSocket.getInputStream();
                cObjectIn = new ObjectInputStream(inputStream);
                outputStream = clientSocket.getOutputStream();
                cObjectOut = new ObjectOutputStream(outputStream);

                // Spin thread to receive messages
                listen();

                // From here, client and server are talking submitting to each other
                while (talking) {

                    // Receive whatever user types in, normalize it to parse
                    orders = keyboard.nextLine().toLowerCase().trim();
                    Scanner orderScanner = new Scanner(orders);
                    String firstOrder = orderScanner.next();

                    switch (firstOrder) {

                        // Send a ListRequest for the channel names as well as connected clients
                        case "/list":
                            ListRequest listRequest = new ListRequest();
                            cObjectOut.writeObject(listRequest);
                            cObjectOut.flush();
                            break;

                        // Handle change function
                        case "/nick":
                            String newNick = "";

                            // Nickname supplied with command
                            if (orderScanner.hasNext()) {

                                // Formatting: Allows users to submit more than one word as a new
                                // nickname, and also capitalizes each first letter
                                while (orderScanner.hasNext()) {
                                    String nextWord = orderScanner.next();
                                    nextWord = nextWord.substring(0, 1).toUpperCase() + nextWord.substring(1);
                                    newNick += nextWord;

                                }
                                // Send request
                                System.out.println("Submitting nickname change request:");
                                System.out.println(newNick);
                                NameRequest nameRequest = new NameRequest(newNick);
                                cObjectOut.writeObject(nameRequest);
                                cObjectOut.flush();
                            }

                            // Command run on its own
                            else {
                                System.out.println("Please enter your desired new nickname");

                                // I actually really like the formatting I did above
                                String rawName = keyboard.nextLine().trim();
                                Scanner nickFormatter = new Scanner(rawName);
                                while (nickFormatter.hasNext()) {
                                    String nextWord = nickFormatter.next();
                                    nextWord = nextWord.substring(0, 1).toUpperCase() + nextWord.substring(1);
                                    newNick += nextWord;
                                }
                                nickFormatter.close();
                                NameRequest nameRequest = new NameRequest(newNick);
                                cObjectOut.writeObject(nameRequest);
                                cObjectOut.flush();
                            }
                            break;

                        // Channel join function
                        case "/join":
                            String channelChoice;

                            // Channel name supplied with command
                            if (orderScanner.hasNext()) {
                                channelChoice = orderScanner.next().toLowerCase().trim();
                                currentChannel = channelChoice;
                                System.out.println("Submitting channel join request:");
                                System.out.println(channelChoice);
                                JoinRequest joinRequest = new JoinRequest(channelChoice);
                                cObjectOut.writeObject(joinRequest);
                                cObjectOut.flush();
                            }

                            // Command run on its own
                            else {
                                System.out.println("Please enter the channel to join.");
                                System.out.println("Viable choices are: Normies, Furries, Humanoids");
                                channelChoice = keyboard.nextLine().toLowerCase().trim();
                                JoinRequest joinRequest = new JoinRequest(channelChoice);
                                currentChannel = channelChoice;
                                cObjectOut.writeObject(joinRequest);
                                cObjectOut.flush();
                            }
                            System.out.println("To change which channel you are broadcasting to,");
                            System.out.println("run \"/<channel name>\".");
                            break;

                        // Channel leave function
                        case "/leave":
                            String leaveChoice;
                            if (orderScanner.hasNext()) {
                                leaveChoice = orderScanner.next().toLowerCase().trim();
                                System.out.println("Submitting request to leave channel: " + leaveChoice);
                                LeaveRequest leaveRequest = new LeaveRequest(leaveChoice);
                                cObjectOut.writeObject(leaveRequest);
                                cObjectOut.flush();
                            } else {
                                System.out.println("Please enter channel to leave.");
                                leaveChoice = keyboard.nextLine().toLowerCase().trim();
                                LeaveRequest leaveRequest = new LeaveRequest(leaveChoice);
                                cObjectOut.writeObject(leaveRequest);
                                cObjectOut.flush();
                            }
                            break;

                        // Server stats request
                        case "/stats":
                            System.out.println("Requesting server statistics.");
                            StatsRequest statRequest = new StatsRequest();
                            cObjectOut.writeObject(statRequest);
                            cObjectOut.flush();
                            break;

                        // Broadcast channel change
                        // Users can listen to any number of channels, but broadcast only to one at a
                        // time. The server WILL NOT allow them to actually broadcast if they have
                        // not /join'd it
                        case "/furries":
                            currentChannel = "furries";
                            System.out.println("You are now broadcasting to Channel \"Furries\"");
                            break;

                        case "/humanoids":
                            currentChannel = "humanoids";
                            System.out.println("You are now broadcasting to Channel \"Humanoids\"");
                            break;

                        case "/normies":
                            currentChannel = "normies";
                            System.out.println("You are now broadcasting to Channel \"Normies\"");
                            break;

                        // Command rundown
                        case "/help":

                            System.out.println("\nNote that asterisks indicate overloaded commands, meaning they can be submitted with or without their requisite argument.\n");
                            System.out.println("/quit : Disconnects from server\n");

                            System.out.println(
                                    "/nick** : Changes the handle both the user and other users on the server see.\n");

                            System.out.println(
                                    "/list	: Shows the current channels available and the users located in those channels\n");

                            System.out.println(
                                    "/join** : Joins a channel, allowing a user to receive messages from users on that channel, as well as broadcast to it\n");

                            System.out.println(
                                    "/leave** : Leaves a channel, meaning users will no longer receive messages from it, and can also no longer broadcast to it\n");

                            System.out.println(
                                    "/stats : Requests statistics from server; IP Address, Port #, # users currently connected, # channels currently available, Time online\n");

                            System.out.println(
                                    "/<channel name> : Sets user's current channel for broadcast purposes. That is, a user can belong to (and receive messages from) any number of"
                                            +
                                            "channels, but can broadcast to only one. \"/<channel name>\" sets the client to broadcast to that channel. Note that users can set their braodcast frequency "
                                            +
                                            "to whatever, but the server will ensure they've picked a valid channel name and alert them otherwise. Additionally, users can attempt to broadcast to channels"
                                            +
                                            "they are not members of, but the server will block them until they /join the channel properly");

                            System.out.println("/help : Displays this message");
                            break;

                            // Send quit request and set infinite loops to false
                        case "/quit":
                            QuitRequest quitRequest = new QuitRequest();
                            cObjectOut.writeObject(quitRequest);
                            cObjectOut.flush();
                            keyboard.close();
                            orderScanner.close();
                            burnItDown();
                            break;

                        // Rather than our usual fallback of SWITCH DEFAULT being a catch-all for "you
                        // screwed up, and here are your options", default is going to be the basic.e
                        // communication. That is, anything that's NOT a reserved command like /quit
                        // or /help is going to be assumed to be a chat message, which we will
                        // send as a broadcast
                        default:

                            // Since we've already accepted a String object to tell the computer what to do,
                            // it should be as simple as relaying that object to a new Broadcast and sending
                            // it
                            orders = orders.substring(0, 1).toUpperCase() + orders.substring(1);
                            Broadcast newMessage = new Broadcast(currentChannel, orders);
                            System.out.println("");

                            // Clean ORDERS out before I forget
                            orders = "";

                            // Write, send
                            cObjectOut.writeObject(newMessage);
                            cObjectOut.flush();

                            // Is that it?
                            break;
                    }

                }

            } catch (IOException e) {
                System.out.println("Client side IO Exception");
                e.printStackTrace();
            }
        }

    }

    // Condensed "shut everything down" method
    public void burnItDown() {

        // I have to "try" my own "light it all on fire" method? Ugh.
        try {
            cObjectOut.close();
            cObjectIn.close();
            outputStream.close();
            inputStream.close();
            clientSocket.close();
            talking = false;
            onScreen = false;
        } catch (IOException e) {
            System.out.println(
                    "Uh. The \"Destroy everything\" function failed. Apparently. Wait, did it? If you try to fail and fail...");
        }

    }

    // Reception thread: allows the client to receive messages from clients or the
    // server independent of its current action
    public void listen() {
        new Thread(new Runnable() {

            public void run() {
                Object receivedMessage;

                // Protocol for immediately after connection: get assigned name, inform user how
                // to change
                try {

                    // Get name
                    clientName = cObjectIn.readObject().toString();
                    System.out.println(clientName);
                    // System.out.println("You can change this by submitting the command
                    // \"/nick\"");
                    System.out.println("You can change your handle by using the /nick command, and your channel with " +
                            "the /join and /leave commands.");

                } catch (Exception e) {
                    System.out.println(
                            "If you see this, your client failed to receive its own dang-ass name from the server");
                }

                // Infinite listen loop
                while (clientSocket.isConnected()) {

                    try {
                        receivedMessage = cObjectIn.readObject();
                        System.out.println(receivedMessage);
                    } catch (IOException e) {

                        // This only popped up when we were trying to run QUIT commands on a client. So
                        // we'll assume that's the only time this happens, and just leave this break as
                        // "working as intended"
                        // As it stands, this has proved the only fix to the exception upon
                        // disconnecting, so we'll leave it like this
                        System.out.println("You have disconnected from the server");
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(
                                "You should literally never see this, but if you are, ClassNotFound error on what you" +
                                        "tried to send");
                    }
                }
            }
        }).start();
    }

}
