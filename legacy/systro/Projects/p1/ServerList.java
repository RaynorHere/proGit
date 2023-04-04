import java.io.Serializable;


/**
 * The ServerList class is used to represent the response sent from the server when
 *  fulfilling a client ListRequest.
 * @author Jackson Morton
 * @version 1.0 CS455 Spring 2022
 */
public class ServerList extends Object implements Serializable  {
    private String body = "";
    public ServerList(String body){
        this.body = body;
    }

    /**
     * Returns a String representation of the server channels and clients who are currently
     * in those channels.
     */
    public String toString(){
        return body;
    }
}
