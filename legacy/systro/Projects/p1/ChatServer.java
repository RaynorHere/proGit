import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * ChatServer: The Chat Server class represents a Server in the Client-Server relationship.
 *
 * @author Jackson Morton
 * @version 1.0 CS455 Spring 2022
 */
public class ChatServer {
    private static Boolean debug = false;
    private ArrayList<ServerConnection> clients = new ArrayList<>();
    private ArrayList<ServerConnection> normies = new ArrayList<>();
    private ArrayList<ServerConnection> furries = new ArrayList<>();
    private ArrayList<ServerConnection> humanoids = new ArrayList<>();
    private LinkedList<String> availableClientNames = new LinkedList<>();
    private ServerSocket serverSocket;
    private final int MAX_THREADS = 4;
    private int currentThreads = 0;
    private Object lock = new Object();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
    private Timer timer;
    private TimerTask task;

    /**
     * Opens a ServerSocket for the specified port number parameter and listens for incoming connections to that socket.
     *
     * @param port The port that will be used for
     */
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            availableClientNames.add("Client 1");
            availableClientNames.add("Client 2");
            availableClientNames.add("Client 3");
            availableClientNames.add("Client 4");
            timer = new Timer();
            task = new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            };
            timer.schedule(task, 330000);

            System.out.println("Server started on port " + serverSocket.getLocalPort());

            while (true) {
                checkNumOfCurrentThreads();
                new Thread(new ServerConnection(serverSocket.accept())).start();
            }

        } catch (IOException e) {
            if (debug) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Resets and current idle timer for this ChatServer and creates a new timer with a task of system
     * shutdown with status code 0 after roughly five and a half minutes.
     */
    synchronized private void resetIdleTimer() {
        timer.cancel();
        timer.purge();
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(task, 330000);
    }

    /**
     * Calls wait() on this ChatServer's lock property until currentThreads is less than MAX_THREADS.
     */
    private void checkNumOfCurrentThreads() {
        while (currentThreads >= MAX_THREADS) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    if (debug) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Increments this ChatServer's currentThreads by 1.
     */
    synchronized private void incrementCurrentThreadCount() {
        currentThreads++;
    }

    /**
     * Decrements this ChatServer's currentThreads by 1.
     */
    synchronized private void decrementCurrentThreadCount() {
        currentThreads--;
    }

    /**
     * Gets a ServerList object containing a String of channel names and users present in those channels.
     *
     * @return ServerList
     */
    private ServerList getServerList() {
        String body = "----Server List------\n";
        body += " - Furries\n";
        body += "    " + furries + "  \n";
        body += " - Normies\n";
        body += "    " + normies + "  \n";
        body += " - Humanoids\n";
        body += "    " + humanoids + "\n";
        return new ServerList(body);
    }

    /**
     * Calls the sendToClient method for each ServerConnection object inside of a specified ArrayList property.
     * The ArrayList is determined by the given channelName property of the passed parameter packet.
     *
     * @param sender of the Broadcast
     * @param packet channel destination name and payload being sent to the clients
     */
    synchronized private void broadcastMessage(ServerConnection sender, Broadcast packet) {
        resetIdleTimer();
        String channelName = packet.getChannelName();
        ArrayList<ServerConnection> channel = getChannel(channelName);
        // The channelName inside of packet corresponds to an actual channel and the sender is in that channel
        if (channel != null && channel.contains(sender)) {
            for (ServerConnection client : channel) {
                if (client != sender) {
                    client.sendToClient(sender.nickname + " (" + channelName.substring(0, 1).toUpperCase() + channelName.substring(1) + "): " + packet);
                }
            }
        } else {
            sender.sendToClient("Server: You are not currently a member of channel " + channelName.substring(0, 1).toUpperCase() + channelName.substring(1));
            sender.sendToClient("Server: To join a channel use: /join followed by channel name.");
        }
    }

    /**
     * Calls the sendToClient method of each ServerConnection object in this ChatServer's clients property with the passed parameter.
     *
     * @param message being sent from the server
     */
    private void serverBroadcastMessage(String message) {
        System.out.println(message);
        for (ServerConnection client : clients) {
            client.sendToClient(message);
        }
    }

    /**
     * Gets the first available String username and removes it from this ChatServer's availableClientName list property.
     *
     * @return String username
     */
    synchronized private String assignAvailableUsername() {
        return availableClientNames.removeFirst();
    }

    /**
     * Adds the passed ServerConnection to this ChatServer's clients property.
     *
     * @param client to add to pool
     */
    synchronized private void addClient(ServerConnection client) {

        clients.add(client);
        System.out.println(clients.size() + " Clients connected.");
    }

    /**
     * Removes the passed ServerConnection from all ArrayList<ServerConnection> properties
     * of this ChatServer.
     *
     * @param client ServerConnection object to be removed
     */
    synchronized private void removeClient(ServerConnection client) {
        clients.remove(client);
        normies.remove(client);
        humanoids.remove(client);
        furries.remove(client);
        availableClientNames.addFirst(client.clientUsername);
    }

    /**
     * Determines the availability of a given nickname. If no other
     * ServerConnections have a nickname property matching the parameter then
     * returns true else returns false.
     *
     * @param nickname
     * @return true if nickname is not already in use else false.
     */
    synchronized private Boolean nickNameIsAvailable(String nickname) {
        for (ServerConnection client : clients) {
            if (client.nickname.equals(nickname)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the channel group associated with the given destination string.
     *
     * @param destination
     * @return ArrayList<ServerConnection> of ServerConnections
     */
    private ArrayList<ServerConnection> getChannel(String destination) {
        switch (destination) {
            case "normies":
                return this.normies;
            case "furries":
                return this.furries;
            case "humanoids":
                return this.humanoids;
        }
        return null;
    }

    /**
     * Gets a String representation of the Server's metadata containing IP number,
     * PORT, number of clients connected, number of channels present, and the time the
     * server came online.
     *
     * @return String metadata
     */
    private String getServerStats() {
        String stats = "\nServer:  STATS\n";
        stats += "         | IP - " + serverSocket.getInetAddress() + "\n";
        stats += "         | PORT - " + serverSocket.getLocalPort() + "\n";
        stats += "         | # CONNECTED - " + clients.size() + "\n";
        stats += "         | # CHANNELS - 3\n";
        stats += "         | ONLINE SINCE - " + dtf.format(now) + "\n";
        return stats;
    }

    /**
     * The ServerConnection class is used to represent and manage each client thread
     * currently connected to the server.
     */
    class ServerConnection implements Runnable {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private String clientUsername;
        private String nickname;

        ServerConnection(Socket socket) {
            this.socket = socket;
            try {
                resetIdleTimer();
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                this.clientUsername = assignAvailableUsername();
                this.nickname = clientUsername;
                incrementCurrentThreadCount();
            } catch (IOException e) {
                if (debug) {
                    e.printStackTrace();
                    System.out.println("Error in connecting to client.");
                }

            }
        }

        /**
         * Writes an object out using this ServerConnection's ObjectOutputStream member.
         *
         * @param payload to send to client
         */
        private void sendToClient(Object payload) {
            resetIdleTimer();
            try {
                out.writeObject(payload);
                out.flush();
            } catch (IOException e) {
                if (debug) {
                    System.out.println("Error Sending Payload To Client.");
                }
            }
        }

        /**
         * Adds this ServerConnection to the parent ArrayList<ServerConnection> property corresponding
         * to the given name.
         *
         * @param name of channel
         */
        private void addClientToChannel(String name) {
            String channelname = name.substring(0, 1).toUpperCase() + name.substring(1);
            ArrayList<ServerConnection> channel = getChannel(name);
            if (channel != null) {
                if (!channel.contains(this)) {
                    synchronized (channel) {
                        channel.add(this);
                    }
                    sendToClient("Server: You have joined the " + channelname + " channel.");
                } else {
                    sendToClient("Server: You are already in the " + channelname + " channel.");
                }
            } else {
                sendToClient("Server: \"" + channelname + "\" not found.");
            }
        }

        /**
         * Sets this ServerConnection's nickname to the given nickname if availble.
         *
         * @param nickname to be assigned to this ServerConnection
         */
        private void setClientNickname(String nickname) {
            if (nickNameIsAvailable(nickname)) {
                this.nickname = nickname;
                sendToClient("Server: Your nick name has been changed to " + this.nickname);
            } else {
                sendToClient("Server: " + nickname + " is already in use.");
            }
        }

        /**
         * Removes this ServerConnection from the parent class ArrayList<ServerConnection> property associated with
         * the passed String name parameter.
         *
         * @param name
         */
        private void removeClientFromChannel(String name) {
            String channelName = name.substring(0, 1).toUpperCase() + name.substring(1);
            ArrayList<ServerConnection> channel = getChannel(name);
            if (channel != null) {
                if (channel.contains(this)) {
                    synchronized (channel) {
                        channel.remove(this);
                        sendToClient("Server: You have been removed from the " + channelName + " channel.");
                    }
                }
            } else {
                sendToClient("Server: Channel \"" + channelName + "\" not found.");
            }
        }

        /**
         * ServerConnection begins listening and processing client requests while the
         * socket is connected.
         */
        public void run() {
            Object clientRequest;
            addClient(this);
            sendToClient("Server: Welcome " + clientUsername);
            addClientToChannel("normies");

            while (socket.isConnected()) {
                try {
                    clientRequest = in.readObject();
                    if (clientRequest instanceof QuitRequest) {
                        closeConnection();
                        break;
                    } else if (clientRequest instanceof Broadcast) {
                        broadcastMessage(this, (Broadcast) clientRequest);
                    } else if (clientRequest instanceof ListRequest) {
                        sendToClient(getServerList());
                    } else if (clientRequest instanceof NameRequest) {
                        setClientNickname(clientRequest.toString());
                    } else if (clientRequest instanceof LeaveRequest) {
                        removeClientFromChannel(clientRequest.toString());
                    } else if (clientRequest instanceof JoinRequest) {
                        addClientToChannel(clientRequest.toString());
                    } else if (clientRequest instanceof StatsRequest) {
                        sendToClient(getServerStats());
                    } else {

                    }

                } catch (ClassNotFoundException e) {
                    if (debug) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    if (debug) {
                        e.printStackTrace();
                        System.out.println("Error processing client request.");
                    }
                    closeConnection();
                    break;
                }
            }
        }

        /**
         * Returns this ServerConnection's nickname.
         *
         * @return String nickname
         */
        public String toString() {
            return this.nickname;
        }

        /**
         * Closes this ServerConnection's I/O streams and socket, updates current thread count, and removes this object from
         * all ArrayLists inside ChatServer.
         */
        private void closeConnection() {
            removeClient(this);
            try {
                out.close();
                in.close();
                socket.close();
                decrementCurrentThreadCount();
                synchronized (lock) {
                    lock.notify();
                }
                System.out.println(clientUsername + " Disconnected.");

            } catch (IOException e) {
                if (debug) {
                    e.printStackTrace();
                    System.out.println("Error when disconnecting from server.");
                }
            }
        }
    }
    

    public static void main(String[] args) {

        if(args.length != 4 && args.length != 2){
            System.out.println("Invalid program arguments. See usage below and README for more info.");
            System.out.println("java ChatServer -p <port#> -d <debug-level>");
            System.exit(1);
        }
       
      
        if(!args[0].equals("-p")){
            System.out.println("Invalid program arguments. See usage below and README for more info.");
            System.out.println("java ChatServer -p <port#> -d <debug-level>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[1]);

        if(args.length == 4){
            if(!args[2].equals("-d")){
                System.out.println("Invalid program arguments. See usage below and README for more info.");
                System.out.println("java ChatServer -p <port#> -d <debug-level>");
                System.exit(1);
            }
            debug = Integer.parseInt(args[3]) == 1;
        }
        
            
        
        ChatServer server = new ChatServer();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    server.serverBroadcastMessage(server.getServerStats());
                    Thread.sleep(700);
                    server.serverBroadcastMessage("Server: The server is shutting down in 5 seconds...");
                    Thread.sleep(1000);
                    server.serverBroadcastMessage("Server: 4");
                    Thread.sleep(1000);
                    server.serverBroadcastMessage("Server: 3");
                    Thread.sleep(1000);
                    server.serverBroadcastMessage("Server: 2");
                    Thread.sleep(1000);
                    server.serverBroadcastMessage("Server: 1");
                    Thread.sleep(1000);
                    server.serverBroadcastMessage("Server: The server is now offline.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    if (debug) {
                        e.printStackTrace();
                    }
                }
            }
        });
        server.start(port);
    }
}
