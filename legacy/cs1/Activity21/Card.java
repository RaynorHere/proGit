
public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;
	
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		
	}
	
	@Override
	public int compareTo(Card o) {

		//Spades Comparison
		if (this.suit == Suit.Spades)
			if (o.suit == Suit.Spades)
				if (this.getFaceValue() < o.getFaceValue())
					return -1;
				else if (this.getFaceValue() > o.getFaceValue())
					return 1;
				else 
					return 0;
			else if (!(o.suit == Suit.Spades))
				return 1;

		//Hearts
		if (this.suit == Suit.Hearts)
			if (o.suit == Suit.Spades)
				return -1;
			else if (o.suit == Suit.Hearts)
				if (this.getFaceValue() < o.getFaceValue())
					return -1;
				else if (this.getFaceValue() > o.getFaceValue())
					return 1;
				else 
					return 0;
			else 
				return 1;
		
		//Diamonds
		if (this.suit == Suit.Diamonds)
			if (o.suit == Suit.Spades || o.suit == Suit.Hearts)
				return -1;
			else if (o.suit == Suit.Diamonds)
				if (this.getFaceValue() < o.getFaceValue())
					return -1;
				else if (this.getFaceValue() > o.getFaceValue())
					return 1;
				else 
					return 0;
			else 
				return 1;
		
		//Clubs
		else
			if (!(o.suit == Suit.Clubs))
				return -1;
			else
				if (this.getFaceValue() < o.getFaceValue())
					return -1;
				else if (this.getFaceValue() > o.getFaceValue())
					return 1;
				else 
					return 0;
	}
	
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	public int getFaceValue() {
		return rank.getFaceValue();
	}
	
	public String toString() {
		return rank + " of " + suit.toString();
	}
	

}
