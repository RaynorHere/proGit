import java.util.ArrayList;

/*
 * This class allows a user to create an object representative of a user account on the Slack
 * social network. It receives String values of the would-be user's legal name and screen name,
 * and initiates a "friends" list in the form of the "teamMembers" arraylist.
 * 
 * @Author: James "JCIII" Crowell
 * 
 * @Version: 1.0 (at best)
 * 
 * @Established: 12/15/2020
 */
public class SlackUser {

	//Attributes
	private String fullName = "";
	private String displayName = "";
	private ArrayList<SlackUser> teamMembers;
	
	
	//Constructor
	public SlackUser(String inLegal, String inDisplay) {
		this.fullName = inLegal;
		this.displayName = inDisplay;
		this.teamMembers = new ArrayList<SlackUser>();
	}
	
	
	//Accessors (2)
	public String getFullName() {
		return this.fullName;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	
	//Mutators (2)
	public void setFullName(String newName) {
		this.fullName = newName;
	}
	
	public void setDisplayName(String newHandle) {
		this.displayName = newHandle;
	}
	
	
	//Team Member Add
	public void addTeamMember(SlackUser newMate) {
		this.teamMembers.add(newMate);
	}
	
	
	
	
	public static void main(String[] args) {
		SlackUser realDeal = new SlackUser("James Crowell", "Raynor's Raider");
		
		SlackUser otherGuy = new SlackUser("Linus Torvalds", "ThePenguin");
		
		realDeal.addTeamMember(otherGuy);

	}

}
