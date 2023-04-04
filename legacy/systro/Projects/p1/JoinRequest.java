import java.io.Serializable;

/**
 * The JoinRequest class is used to represent a request to join a channel in the server-client communication
 * protocol.
 * @author Jackson Morton
 * @version 1.0 CS455 Spring 2022
 */
public class JoinRequest extends Object implements Serializable {

    private String channelName = "";

    public JoinRequest(String chosenName) {

        this.channelName = chosenName;

    }

    /**
     * Returns the String containing the channel name of this Join Request.
     * @return String channelName
     */
    public String toString() {
        
        return channelName;
    }
    
}
