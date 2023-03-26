/*
*   LeaveRequest is the object submitted by a client running the /leave overloaded command
*
*   @param: String chosenChannel, the name of the channel being left
*
*   @author: Jim Crowell, Jackson Morton
*/
import java.io.Serializable;

public class LeaveRequest extends Object implements Serializable {
    private String leaveName = "";

    public LeaveRequest(String chosenChannel) {

        leaveName = chosenChannel;
    }

    public String toString() {
        return leaveName;
    }
    
}
