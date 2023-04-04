
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Date;
import java.util.LinkedList;

/**
 * Connect to a time server and get time from it as a Date object.
 */
public class TimeClient {
	public static void main(String args[]) {

		// Changing this so all arguments past the first are potential IPs
		// This way, you can pass functionally infinite hosts and it will make an LL of
		// IPs
		if (args.length < 2) {
			System.err.println("Usage: java TimeClient <port> <serverhost IP> [<additional serverhost IPs>...]");
			System.exit(1);
		}

		// Changing this so arg0 is the port numeber and ALL OTHERS are hosts
		int port = Integer.parseInt(args[0]);

		// Starting at args[1], which is where host IPs start
		int i = 1;

		// Need to change this scope so it applies to both loops
		Date date = null;

		// Now, we'll need to create a Linked List, which we'll add addresses to in the
		// event of timeouts (not refused connections), because those represent actual
		// addresses of servers that MIGHT reply
		LinkedList<String> hostList = new LinkedList<String>();

		// And then, loop once through the supplied IPs
		while (i < args.length && args[i] != null) {
			String host = args[i];

			try {
				// Remove arguments for purposes of including change to timeout
				Socket s = new Socket();

				// Our new connection function, with a timeout period of 2 sec
				s.connect(new InetSocketAddress(host, port), 2000);

				InputStream in = s.getInputStream();
				ObjectInput oin = new ObjectInputStream(in);

				date = (Date) oin.readObject();

				System.out.println("Time on host " + host + " is " + date);

				// If date successfully returns something, the connection was successful and we
				// can stop
				// This is intended to avoid subsequent calls to other hosts after a SUCCESSFUL
				// one
				if (date != null)
					s.close();
				break;
			}

			// If we get specifically a TIMEOUT exception, that suggests we have an actual
			// server, at least, and we can add it to the list to try again
			catch (SocketTimeoutException e3) {
				hostList.add(host);
				i++;
				System.out.println("Socket timeout.");
			} catch (IOException e1) {
				System.out.println(e1);

				// If we get a catch, something failed, so iterate i
				i++;
			} catch (ClassNotFoundException e2) {
				System.out.println(e2);
				i++;
			}
		}

		// Once we've reached the end of that loop, one of two things has happened:
		// either we've made a successful connection and shut down, OR we've walked
		// through every IP submitted in the program's arguments and got nothing, making
		// a Linked List of all the SocketTimeout-resulting ones. So, now we work
		// through the Linked List, and do so until we found something useful
		while (date == null) {

			// Pull a host off the top of the pile of 'legit' ones we made
			String host = hostList.removeFirst();

			try {
				// Remove arguments for purposes of including change to timeout
				Socket s = new Socket();

				// Our new connection function, with a timeout period of 2 sec
				s.connect(new InetSocketAddress(host, port), 2000);

				InputStream in = s.getInputStream();
				ObjectInput oin = new ObjectInputStream(in);

				date = (Date) oin.readObject();

				System.out.println("Time on host " + host + " is " + date);
				s.close();

			}

			catch (SocketTimeoutException e3) {
				System.out.println("Socket timeout.");
				
				// Add the host we tried back into the pile, and the cycle continues
				hostList.add(host);
				
			} catch (IOException e1) {
				System.out.println(e1);
			} catch (ClassNotFoundException e2) {
				System.out.println(e2);
			}

		}
	}
}
