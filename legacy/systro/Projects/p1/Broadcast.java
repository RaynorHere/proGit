import java.io.Serializable;

/**
 * The Broadcast class represents the data that a client sends to the server so that the 
 * server can send forward it to other clients.
 */
public class Broadcast extends Object implements Serializable {

    private String channelName = ""; // The destination channel name 
    private String body = ""; // The message being sent to the other clients.
    

    public Broadcast(String destination, String body) {
        this.channelName = destination;
        this.body = body;
    }

    /**
     * Returns this object's channelName.
     * @return String channelName
     */
    public String getChannelName(){
        return this.channelName;
    }

    /**
     * Returns a String containing the body of this Broadcast.
     * @return String body
     */
    public String toString() {
        return this.body;
    }
    
}
