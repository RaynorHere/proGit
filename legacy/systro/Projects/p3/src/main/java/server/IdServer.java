package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import shared.Id;

/**
 * The IdServer class is used as a driver class for the server.
 * @author JMORTON
 * @author JCROWELL
 * @version 1.0 CS455 Spring 2022
 */
public class IdServer {
    /**
     * Returns an IdImpl object stored on the disk in the id.ser file or
     *  creates a new IdImpl and returns that object.
     * @param verbosity
     * @return IdImpl server
     */
    public static IdImpl readServerFromDisk(boolean verbosity) {

        // Case 1: A file with an identical name as our save file for the server object exists.
        if(new File("id.ser").exists()){
            try {
                // Attempt to retrieve the file containing the previous server object.
                FileInputStream fileIn = new FileInputStream("id.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);

                // Attempt to read in the object from the file and return it.
                Object obj = in.readObject();
                in.close();
                System.out.println("Restoring server from previous state.");
                IdImpl server = (IdImpl)obj;

                // Reconfigure the server disk save timer and export server to RMI for client use.
                server.setPrintStatements(verbosity);
                server.startTimerForDiskSave();
                server.exportToRMI();
                return server;
            } catch (IOException e) {
                System.err.println(e);
            } catch (ClassNotFoundException e) {
                System.err.println(e);
            }
        }
        // Case: Server save file cannot be found.
        return new IdImpl();
    }

    /**
     * Setups the RMI registry on the specified port and lists the passed
     *  Id server in that registry.
     * @param server
     * @param port
     */
    public static void startRegistry(Id server, int port){
        try {
            // Create a registry for the port 1099 and bind our server to the reference "IdServer"
            Registry registry =  LocateRegistry.createRegistry(port);
            registry.bind("IdServer", server);
            System.out.println("Server has started on port " + port +".");
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("remote exception thrown");
        } catch (AlreadyBoundException e) {

            System.out.println("already bound exception");
        }
    }

    /**
     * Prints the CLI usage of the IdServer program.
     */
    public static void printUsage(){
        System.err.println("java IdServer [--numport <port#>] [--verbose]");
    }
    public static void main(String[] args){
        // SSL Setup
        System.setProperty("javax.net.ssl.keyStore", "src/main/resources/Server_Keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "test123");
        System.setProperty("java.security.policy", "mysecurity.policy");


        boolean verboseFlag = false;
        int port = 1099;
        if(args.length > 0){
            if(args.length == 1){
                if(args[0].toLowerCase().equals("--verbose") || args[0].toLowerCase().equals("--v")) verboseFlag = true;
                else printUsage();
            }
            if(args.length == 2){
                if(args[0].toLowerCase().equals("--numport") || args[0].toLowerCase().equals("-n")){
                    port = Integer.parseInt(args[1]);
                }
                else printUsage();
            }
            if(args.length == 3){
                if(args[0].toLowerCase().equals("--numport") && args[2].toLowerCase().equals("--verbose")){
                    verboseFlag = true;
                    port = Integer.parseInt(args[1]);
                }
                else printUsage();
            }
        }
        // Get the server from disk or create a new one.
        final IdImpl implServer = readServerFromDisk(verboseFlag);
        implServer.setPrintStatements(verboseFlag);
        // Assign our server to an interface object to be bound.
        Id server = implServer;

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("\nShutting down...");
                System.out.println("Saving server to disk.");
                implServer.saveServerToDisk();
            }
        });

        startRegistry(server, port);
    }
}
