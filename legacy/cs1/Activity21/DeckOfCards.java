import java.util.Random;

public class DeckOfCards implements DeckOfCardsInterface {

	
	private int deckSize = 52;
	private Card[] cards = new Card[deckSize];
	private int nextCardIndex = 0;
	
	
	//Constructor; iterates through every possible permut. of numbers in every suit
	public DeckOfCards() {
		int buildCount = 0;
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				if (buildCount < cards.length)
				cards[buildCount++] = new Card(s, r);
			}
		}
	}

	
	//ToString method
	public String toString() {
		String output = "";
		
		for (Card member : cards) {
			output += member.toString() + "\n";
		}
		return output;
	}
	
	
    //Shuffle function
	public void shuffle() {
		Random melbourneShuffler = new Random();
		int i = 0;
		int j = 0;
		
		//Iterates through entire deck, picks a random card, clones it into the "joker" placeholder,
		//then puts the original card in the random-picked, puts the joker in the original's place,
		//and moves on
		for (i = 0; i < cards.length; i++) {
			j = melbourneShuffler.nextInt(cards.length);
			Card joker = cards[j];
			cards[j] = cards[i];
			cards[i] = joker;
		}
		
		
	}

	//Draw function. Should draw top card, return it to the system, and advance the index marker
	public Card draw() {
		if (nextCardIndex < 52) {
			Card drawnCard = cards[nextCardIndex];
			nextCardIndex += 1;
			return drawnCard;
		}
		else
			return null;
		
	}

	@Override
	public int numCardsRemaining() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numCardsDealt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Card[] dealtCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card[] remainingCards() {
		// TODO Auto-generated method stub
		return null;
	}

}
