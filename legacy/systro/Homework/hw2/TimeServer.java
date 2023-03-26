
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A single-threaded time server that sends Date objects to clients. You may
 * need to open port 5005 in the firewall on the host machine (unless you are
 * locally).
 * 
 * @author amit
 */

public class TimeServer
{
    private OutputStream out;
    private int port = 5005;
    private ServerSocket s;
    private Object lock;
    private boolean notifyAlert = false;

    public static void main(String args[]) {
        TimeServer server = new TimeServer();
        server.serviceClients();
    }


    public TimeServer() {
        try {
            s = new ServerSocket(port);
            System.out.println("TimeServer: up and running on port " + port + " " + InetAddress.getLocalHost());

            lock = new Object();
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    /**
     * The method that handles the clients, one at a time.
     */
    public void serviceClients() {
        Socket sock;

        // Here's basically all the change we need to make
        Timer timeCop = new Timer("Timer");
                
                // Create the task which unlocks the lock
                TimerTask task = new TimerTask() {

                    public void run() {

                        // Reference the lock that we'll be latching whenever a connection is made
                        synchronized(lock) {

                            // Unlock the lock
                            lock.notify();

                            // Print 'unlocked' message (I only want to do this when the lock's been locked
                            // because otherwise it just yells at you every 5 seconds
                            if (notifyAlert) {
                                System.out.println("Connection available.\n");
                                notifyAlert = false;
                            }
                        }
                    }
            };

            timeCop.scheduleAtFixedRate(task, 5000, 5000);

        while (true) {
            try {
                sock = s.accept();
                out = sock.getOutputStream();               
               

                // Note that client gets a temporary/transient port on it's side
                // to talk to the server on its well known port
                System.out.println("TimeServer: Received connect from " + sock.getInetAddress().getHostAddress() + ": "
                        + sock.getPort());

                ObjectOutputStream oout = new ObjectOutputStream(out);
                oout.writeObject(new java.util.Date());
                oout.flush();

                // Trigger notification statement
                notifyAlert = true;

                // Lock server until time expiration
                synchronized(lock) {
                    lock.wait();                   
            }
                
                
                sock.close();
            } catch (InterruptedException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
