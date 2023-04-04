import java.util.ArrayList;

public class TwitterAccount {
	
	private String accountID;
	private String accountEMail;
	private ArrayList<String> accountHashTags;
	
	
	//Constructor
	public void CreateAccount(String inID, String inEMail) {
		accountID = inID;
		accountEMail = inEMail;
		accountHashTags = new ArrayList<String>();
	}
	
	//Accessors (2)	
	public String GetID() {
		return accountID;
	}
	
	public String GetEMail() {
		return accountEMail;
	}
	
	//Mutators (2)
	public void SetID(String newID) {
		accountID = newID;
	}
	
	public void SetEMail(String newEMail) {
		accountEMail = newEMail;
	}
	
	//Add to Hashtag ArrayList
	public void addHashtag(String tag) {
		accountHashTags.add(tag);
	}
	
	public boolean checkHashtag(String tag) {
		if (accountHashTags.contains(tag))
			return true;
		else
			return false;
	}
	
	//Acing a test with output like this would be icing on the cake, but the very fact that I get
	//to build a final around a StarCraft reference is exactly the sort of thing that tells me
	//I can make a go of computer science as a career. My only regret is taking as long to get to it
	//as I did
	public static void main(String[] args) {
		
		TwitterAccount fictitiousAccount = new TwitterAccount();
		
		fictitiousAccount.CreateAccount("Marshal Raynor", "MarSaraMarshall@Korhal.com");
		
		fictitiousAccount.accountHashTags.add("#NotMyOvermind");
		fictitiousAccount.accountHashTags.add("#PaciFIST");
		fictitiousAccount.accountHashTags.add("#UEDGoHome");
		fictitiousAccount.accountHashTags.add("#Don'tNeedNoStinkin'Zerg");
		
		if(fictitiousAccount.checkHashtag("#ProDuke"))
			System.out.println("Right On");
		else
			System.out.println("No Dice");
		
		System.out.println("\n\n");
		
		if(fictitiousAccount.checkHashtag("#PaciFIST"))
			System.out.println("Right On");
		else
			System.out.println("No Dice");
	}
	

	
	

}
