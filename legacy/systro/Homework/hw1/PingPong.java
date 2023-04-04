import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

/*
*   This class allows users modal accessibility to server/client
*   functionality. Mode is chosen at runtime. Servers establish
*   a port of connection, clients connect to that same port, 
*   whereupon the two programs will simulate a game of pingpong
*   in the terminal.
*
*   @author Jimmy "Crowley" Crowell
*/

public class PingPong {
    

    public static void main(String args[]) {

        if (args.length != 3) {
            System.out.println(
                    "Error: intended usage is \"java PingPong <server | client> <machine name> <port number>\"");
            System.out.println(
                    "Example: \"java PingPong server localhost 4649\" establishes a pingpong server on port 4649");
            System.out.println(
                    "\t\"java PingPong client localhost 4649\" would then instantiate a client which connects on that port and begin the game.\n");
            System.exit(1);
        }

        if (args[0].equals("server")) {
            MTServer server = new MTServer(Integer.parseInt(args[2]));
            server.handleClient();
        }

        else if (args[0].equals("client")) {

            // If I'm right, since I put the entire functionality in the constructor, this should be all I need to do
            PingPongClient client = new PingPongClient(args[1], Integer.parseInt(args[2]));

        }

    }
}

class MTServer {

    private ServerSocket serveOut;

    // Track which game is which. Playing around with the idea of decrementing the
    // number
    // back to the next unoccupied value after a client disconnects, but that would
    // be a
    // great deal of work, I think, and beyond the scope to the point of
    // wastefulness
    private int gameNumber = 1;

    // Constructor to create a server, which watches the port supplied for
    // connections
    public MTServer(int port) {

        try {
            serveOut = new ServerSocket(port);
            System.out.println("Instanced new server. It is on port " + port + " at address "
                    + InetAddress.getLocalHost() + "\n");
        } catch (IOException e) {
            System.out.println("Error instantiating new server: IOException.\n");
            return;
        }
    }

    // "Thread" constructor; the function which allows the program to delegate a
    // process off to handle a new client
    public void handleClient() {
        Socket clientSocket;

        try {

            while (true) {

                // Anchor to client
                clientSocket = serveOut.accept();

                // I'm actually gonna save these to memory so as to be able to reference them in
                // other areas
                String theOtherGuysName = clientSocket.getInetAddress().getHostName();
                String theOtherGuysAddress = clientSocket.getInetAddress().getHostAddress();

                // It's a little counterintuitive at first, but I guess the "HOST" in
                // "GETHOST[x]" is relative
                // Since I'm running it on a client socket, it gets me the client's information,
                // which is the HOST
                // information from the client's point of view
                System.out.println("Connection established from client name: " + theOtherGuysName +
                        " and IP: " + theOtherGuysAddress + "\n");

                // Still working on figuring out how to assign individual numbers to games
                new EstablishedConnection(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Something went manky when connecting to client.\n");
            return;
        }
    }

    // Class to handle and start new threads. I think
    class EstablishedConnection extends Thread {

        private Socket clientSocketConnection;

        EstablishedConnection(Socket clientIn) {

            this.clientSocketConnection = clientIn;

            // Not sure what this is, and that annoys me, but I'm sure we'll come back
            // around to it
            setPriority(NORM_PRIORITY - 1);
            System.out.println("New thread created: " + this.getName() + "\n");
        }

        // I mean, the function's all in the name
        public void run() {

            // If we're a *decent* fraction as good as we think we are, I should be able to
            // just
            // drop in the functionality of the "server" side of our PingPongST and it
            // should run
            // More or less
            OutputStream serveRoadOut;
            InputStream serveRoadIn;
            int serveRank;
            int rankFromClient;

            int thisGameID = gameNumber++;

            // Coin toss facsimile
            Random enGarde = new Random();

            // Ordinarily, client sends the ball first, with PING, and server responds with
            // PONG
            // This is so we can change the message in the event of a coin toss update
            String serverMessage = "PONG";

            // Service-providing subroutine

            try {

                // Set up connection
                // This was crashing the unholy Hell out of my server upon client disconnect, so
                // I'm going to try moving the "WHILE (TRUE)" statement below this block
                serveRoadOut = clientSocketConnection.getOutputStream();
                serveRoadIn = clientSocketConnection.getInputStream();
                ObjectOutputStream serveObjectOut = new ObjectOutputStream(serveRoadOut);
                ObjectInputStream serveObjectIn = new ObjectInputStream(serveRoadIn);

                while (true) {

                    Ball allRounder;

                    // Cluttered output; initial connection
                    System.out.println("Connection received from " +
                            clientSocketConnection.getInetAddress().getHostAddress() + ": "
                            + clientSocketConnection.getPort() + "\n");

                    // Relocating number generator down here to see if I can get
                    // unique numbers for the server every connection
                    // Solved it
                    serveRank = enGarde.nextInt();

                    // Send the number to compare
                    serveObjectOut.writeObject(serveRank);
                    serveObjectOut.flush();
                    rankFromClient = (int) serveObjectIn.readObject();

                    System.out.println("Rank from server: " + serveRank);
                    System.out.println("Rank from client: " + rankFromClient);

                    // Tiebreaker
                    while (serveRank == rankFromClient) {

                        System.out.println("Order numbers are tied. Retrying...");

                        serveRank = enGarde.nextInt();
                        serveObjectOut.writeObject(serveRank);
                        serveObjectOut.flush();
                        rankFromClient = (int) serveObjectIn.readObject();

                    }

                    // Server starts
                    if (serveRank < rankFromClient) {

                        System.out.println("Server wins the toss. Transferring ball.\n");
                        serverMessage = "PING";

                        // Receive ball
                        allRounder = (Ball) serveObjectIn.readObject();
                        System.out.println("Client has handed ball to server.\n");

                        // Then, send it out, which will allow the client to send it back, and default
                        // code takes over
                        serveObjectOut.writeObject(allRounder);
                        serveObjectOut.flush();
                        System.out.println("Game " + thisGameID + " begin!\n");
                        System.out.println("Game " + thisGameID + ": " + serverMessage + "\n");

                        // Change server rank for future connections
                        // serveRank = enGarde.nextInt();
                    }

                    // Default start, confirmed by client winning toss
                    else {

                        // In this case, this should be all we need, because the client will send it and
                        // the block below, the defeault,
                        // successfully works by beginning with "read"
                        System.out.println("Server loses the toss. Ball begins with client.");

                        // serveRank = enGarde.nextInt();

                    }

                    // I can't think of a simpler way to do this
                    while (true) {
                        allRounder = (Ball) serveObjectIn.readObject();
                        System.out.println("Game " + thisGameID + ": Ball received.\n");

                        Thread.sleep(1500);

                        serveObjectOut.writeObject(allRounder);
                        serveObjectOut.flush();
                        System.out.println("Game " + thisGameID + ": " + serverMessage + "\n");

                        Thread.sleep(1500);

                        // serveObjectIn.readObject();
                        // System.out.println("Ball received\n");
                    }
                }

            }
            // I won't lie, these exceptions don't seem to come up too often, so I
            // haven't (and won't) written bespoke reactions to them. The other two,
            // on the other hand....
            catch (InterruptedException e) {
                System.out.println("Interrupted during service!");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // I'm having some issues with identifying when *A* client
            // disconnects and when *THE* client disconnects. If there
            // are more, games can continue and I want to reflect that
            // If there are not, I want to signify service ending and
            // returning to wait mode. I thought EOF was when there
            // were more clients and SocketException was when there
            // weren't, but it reversed on me one time, so I have to
            // stick with this generalized error, for now
            catch (IOException e) {
                System.out.println("Game " + thisGameID + ": client has disconnected\n");
            }

        }
    }

}


//  I'm not going to lie to you, this has been through a number of phases, and sorting through the legacy code is... treacherous
//  MTServer started out as its own thing when we started devleoping Phase 3, and had most its functionality ripped out of Phase
//  1 and 2, which were initially combined, because it was a single-threaded server/client program. I apologize for any difficulty
//  in reading or following this (beyond my usual stupid referential/punny nicknames)
class PingPongClient {

    public PingPongClient(String hostName, int port) {

        Random enGarde = new Random();


            OutputStream clientRoadOut;
            InputStream clientRoadIn;

            ObjectInputStream clientObjectIn;
            ObjectOutputStream clientObjectOut;

            // Client starts with ball by default, so
            Ball allRounder = new Ball();

            // Same as above
            String clientMessage = "PING";

            String hostWay = hostName;
            int portLandCalling = port;

            // Client always starts with ball, guy who starts with ball is the one who says
            // PING
            int clientRank = enGarde.nextInt();
            int rankFromServer;

            while (true) {
                try {

                    Socket conduit = new Socket(hostWay, portLandCalling);

                    clientRoadIn = conduit.getInputStream();
                    clientObjectIn = new ObjectInputStream(clientRoadIn);

                    clientRoadOut = conduit.getOutputStream();
                    clientObjectOut = new ObjectOutputStream(clientRoadOut);

                    rankFromServer = (int) clientObjectIn.readObject();
                    clientObjectOut.writeObject(clientRank);
                    clientObjectOut.flush();

                    System.out.println("Rank from server: " + rankFromServer);
                    System.out.println("Rank from client: " + clientRank);

                    // If we settle a tie BEFORE deciding, I think it's simpler. That is, have the
                    // server and client shout numbers at
                    // each other until they're unique numbers, then compare them
                    while (rankFromServer == clientRank) {

                        System.out.println("Order numbers are tied. Retrying...");
                        clientRank = enGarde.nextInt();
                        rankFromServer = (int) clientObjectIn.readObject();
                        clientObjectOut.writeObject(clientRank);
                        clientObjectOut.flush();

                    }

                    // Server wins toss
                    if (rankFromServer < clientRank) {

                        System.out.println("Client loses toss. Transferring ball.\n");
                        clientMessage = "PONG";

                        clientObjectOut.writeObject(allRounder);
                        clientObjectOut.flush();
                        System.out.println("Client has handed ball to server.\n");

                    }

                    // Client wins toss (default)
                    else {
                        System.out.println("Client wins toss. Ball begins with client.\n");
                        System.out.println("Game begin!\n");

                    }

                    while (true) {
                    clientObjectOut.writeObject(allRounder);
                    clientObjectOut.flush();
                    System.out.println("From Client: " + clientMessage + "\n");

                    Thread.sleep(1500);

                    clientObjectIn.readObject();
                    System.out.println("Ball received.\n");

                    // Screwing with formatting to see if I can make the terminals update more sequentially and less simultaneously
                    Thread.sleep(1500);
                    }
                    
                    // System.out.println("Ball received.");

                    // clientObjectOut.writeObject(ballIn);
                    // clientObjectOut.flush();

                    // System.out.println("From Client: PING");

                    // Thread.sleep(2500);
                }

                catch (IOException e) {
                    System.out.println("Client side IO Exception");
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    System.out.println("Client side Class Not Found");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    System.out.println("Client side interruption!");
                    e.printStackTrace();
                }
            }

        

    }

        

}
