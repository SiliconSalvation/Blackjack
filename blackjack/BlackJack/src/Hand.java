import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> cards;
	private boolean standOnHand;
	
	public Hand() {
		this.cards = new ArrayList<Card>();
		this.standOnHand = false;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public boolean hasPair() {
		if(cards.get(0).equals(cards.get(1)) && cards.size() == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
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
	
	public boolean isHolding() {
		return standOnHand;
	}

	public void setStandOnHand(boolean decision) {
		this.standOnHand = decision;
		
	}
}
