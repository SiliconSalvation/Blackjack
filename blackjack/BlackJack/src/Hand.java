import java.util.ArrayList;
/**
 * The Hand class represents a hand in blackjack
 * @author kazuma
 * @author staunw01@pfw.edu
 */
public class Hand {
	
	private ArrayList<Card> cards;			//An ArrayList of Card objects
	private boolean isStanding;				//A boolean representing if the player is standing on the hand
	
	/**
	 * Constructor that instantiates the fields
	 */
	public Hand() {
		this.cards = new ArrayList<Card>();
		this.isStanding = false;
	}
	/**
	 * Determines if the the hand has two and only two cards that are the same
	 * @return a boolean value representing of the hand has a pair
	 */
	public boolean hasPair() {
		if(cards.get(0).equals(cards.get(1)) && cards.size() == 2) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Adds a card to this hand
	 * @param card a Card object to be added to this hand
	 */
	public void addCard(Card card) {
		cards.add(card);
	}
	/**
	 * determines the total value of the hand. If there is an ace and the value 
	 * is greater than 21, then subtracts 10 from the hand
	 * @return an integer value determining the value of the hand
	 */
	public int getHandTotal() {
		int total = 0;
		
		for(Card c : cards) {
			total += c.getValue();
		}
		
		if(total > 21 && hasAce() && !hasPair()) {
			total-= 10;
		}
		
		return total;
	}
	/**
	 * Determines if the hand has an ace
	 * @return a boolean representing if the hand has an ace
	 */
	public boolean hasAce() {
		boolean hasAce = false;
		
		for(Card c : cards) {
			if(c.getValue() == 11) {
				hasAce = true;
				break;
			}
		}
		
		return hasAce;
	}
	/**
	 * Getter if the player held on the hand
	 * @return
	 */
	public boolean isHolding() {
		return isStanding;
	}
	/**
	 * Sets the field isStanding
	 * @param standing
	 */
	public void setStandOnHand(boolean standing) {
		this.isStanding = standing;
		
	}
	/**
	 * Getter for cards field
	 * @return an ArrayList of card objects
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	/**
	 * sets the current hand with an ArrayList of card objects
	 * @param cards an ArrayList of card objects
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
}
