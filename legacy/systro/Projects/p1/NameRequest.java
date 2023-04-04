/*
*   NameRequest is the object sent by a client after running the /nick overloaded command
*
*   @param: String name, the nickname desired by the client to use
*
*   @author: Jim Crowell, Jackson Morton
*/
import java.io.Serializable;

public class NameRequest extends Object implements Serializable {
    String clientname;

    public NameRequest(String name){
        this.clientname = name;
    }

    public String toString(){
        return this.clientname;
    }
}
