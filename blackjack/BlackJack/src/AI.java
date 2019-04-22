import java.util.ArrayList;


/*
 * The AI class represents an artificial intelligence that plays blackjack
 * @author Nick Stauffer
 * @author staunw01@pfw.edu
 */
public class AI {
	private final int HIT = 1;			//Represents AI Hitting
	private final int STAND = 2;		//Represents AI Standing
	private final int DOUBLE_DOWN = 3;	//Represents AI Doubling Down
	private final int SPLIT = 4;		//Represents AI Splitting
	private final int MIN_BET = 5;		//Represents The minimum betting amount
	
	private ArrayList<Hand> hand;		//An ArrayList of hand objects
	private int cardCount;				//An integer representing the current count
	private int chips;					//An integer representing the amount in chips the AI has
	/**
	 * Constructor, sets the cardCount based on the number of decks there are.
	 * Sets the number of chips the AI has,
	 * Creates the hand of the AI
	 * @param numOfDecks
	 */
	public AI(int numOfDecks) {
		if(numOfDecks == 2) {
			cardCount = -4;
		}else if(numOfDecks == 6) {
			cardCount = -20;
		}else if(numOfDecks == 8) {
			cardCount = -28;
		}else {
			cardCount = 0;
		}
		
		this.chips = 500;
		
		this.hand = new ArrayList<Hand>();
	}
	
	/**
	 * Splits the AI's hand based on the index
	 * Creates two hands and adds them to the ArrayList
	 * @param index an integer representing the index of the hand to be split in the ArrayList
	 */
	public void splitHand(int index) {
		Hand newHand1 = new Hand();
		Hand newHand2 = new Hand();
		
		newHand1.addCard(this.hand.get(index).getCards().get(0));
		newHand2.addCard(this.getHand().get(index).getCards().get(1));
		
		this.hand.remove(index);
		this.hand.add(newHand1);
		this.hand.add(newHand2);
	}
	/**
	 * Adds a card to the hand
	 * @param hand the hand object to be added to
	 * @param card a card to add to the hand
	 */
	public void addCardToHand(int hand, Card card) {
		this.hand.get(hand).addCard(card);
	}
	
	/**
	 * The AI portion that decides how to play the hand.
	 * takes an integer value that represents which hand in the ArrayList is being played.
	 * @param handBeingPlayed an integer representing which hand in the ArrayList is being played
	 * @param dealerCard an integer value representing the Card the dealer is showing.
	 * @return an integer that represents the strategy the AI is playing, 1 = Hit, 2 = Stand, 3 = Double Down, 4 = Split
	 */
	public int playHand(int handBeingPlayed, int dealerCard) {
		int strategy;
		/*
		 * This if branch determines what to play when the AI has an ace
		 */
		if(hand.get(handBeingPlayed).hasAce() && !hand.get(handBeingPlayed).hasPair()) {
			if(hand.get(handBeingPlayed).getHandTotal() > 8) {
				strategy = STAND;
			}else if(hand.get(handBeingPlayed).getHandTotal() == 8){
				if(dealerCard == 2 || dealerCard == 7 || dealerCard ==8) {
					strategy = STAND;
				}else if(dealerCard >= 3 && dealerCard <= 6) {
					strategy = DOUBLE_DOWN;
				}else {
					strategy = HIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 7) {
				if(dealerCard < 3 || dealerCard > 6) {
					strategy = HIT;
				}else {
					strategy = DOUBLE_DOWN;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 6 || hand.get(handBeingPlayed).getHandTotal() == 5) {
				if(dealerCard < 4 || dealerCard > 6) {
					strategy = HIT;
				}else {
					strategy = DOUBLE_DOWN;
				}
			}else {
				if(dealerCard < 5 || dealerCard > 6) {
					strategy = HIT;
				}else {
					strategy = DOUBLE_DOWN;
				}
			}
				
		/*
		 * This branch determines how to play when the AI has a pair
		 */
		}else if(hand.get(handBeingPlayed).hasPair() && hand.size() <= 4) {
			if(hand.get(handBeingPlayed).getHandTotal() == 22 || hand.get(handBeingPlayed).getHandTotal() == 16) {
				strategy = SPLIT;
			}else if(hand.get(handBeingPlayed).getHandTotal() == 20) {
				strategy = STAND;
			}else if(hand.get(handBeingPlayed).getHandTotal() == 18) {
				if(dealerCard < 7 || (dealerCard > 7 && dealerCard < 10)) {
					strategy = SPLIT;
				}else {
					strategy = STAND;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 14) {
				if(dealerCard < 8) {
					strategy = SPLIT;
				}else {
					strategy = HIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 12) {
				if(dealerCard < 7) {
					strategy = SPLIT;
				}else {
					strategy = HIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 10) {
				if(dealerCard < 10) {
					strategy = DOUBLE_DOWN;
				}else {
					strategy = HIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 8) {
				if(dealerCard < 5 || dealerCard > 6) {
					strategy = HIT;
				}else {
					strategy = SPLIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 6) {
				if(dealerCard < 8) {
					strategy = SPLIT;
				}else {
					strategy = HIT;
				}
			}else {
				if(dealerCard < 8) {
					strategy = SPLIT;
				}else {
					strategy = HIT;
				}
			}
		/*
		 * This branch plays if the AI neither has an ace or a pair. 
		 */
		}else {
			if(hand.get(handBeingPlayed).getHandTotal() >= 17) {
				strategy = STAND;
			}else if(hand.get(handBeingPlayed).getHandTotal() >= 13 && hand.get(handBeingPlayed).getHandTotal() <= 16) {
				if(dealerCard < 7) {
					strategy = STAND;
				}else {
					strategy = HIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 12) {
				if(dealerCard < 4 || dealerCard > 6) {
					strategy = HIT;
				}else {
					strategy = STAND;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 11) {
				if(dealerCard != 1) {
					strategy = DOUBLE_DOWN;
				}else {
					strategy = HIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 10) {
				if(dealerCard < 10) {
					strategy = DOUBLE_DOWN;
				}else {
					strategy = HIT;
				}
			}else if(hand.get(handBeingPlayed).getHandTotal() == 9) {
				if(dealerCard < 3 || dealerCard > 6) {
					strategy = HIT;
				}else {
					strategy = DOUBLE_DOWN;
				}
			}else {
				strategy = HIT;
			}
		}
		
		
		return strategy;
	}
	/**
	 * Uses the card count to determine betting strategy
	 * @return an integer representing the bet the AI wants to play
	 */
	public int placeBet() {
		int bet;
		
		if(cardCount <= 1) {
			bet = MIN_BET;
		}else if(cardCount >= 2 && cardCount < 4 ) {
			bet = MIN_BET * 5;
		}else if(cardCount >= 4 && cardCount < 8 ) {
			bet = MIN_BET * 20;
		}else if(cardCount >= 8 && cardCount < 12) {
			bet = MIN_BET * 100;
		}else {
			bet = MIN_BET * 200;
		}
		
		if(bet > chips) {
			bet = chips;
		}
		
		return bet;
	}
	/**
	 * Prints out the hand the AI has to the console
	 */
	public void printHand() {
		
		for(Hand h : hand) {
			System.out.println();
			System.out.print("Player has: ");
			for(Card c : h.getCards()) {
				System.out.print(c.getValue() + " ");
			}
		}
		System.out.println("\n");
		
	}
	/**
	 * Getter for hand field
	 * @return ArrayList<Hand> object
	 */
	public ArrayList<Hand> getHand() {
		return hand;
	}
	/**
	 * Adds a hand to the hand field
	 * @param hand a Hand object
	 */
	public void setHand(Hand hand) {
		this.hand = null;
		this.hand = new ArrayList<Hand>();
		this.hand.add(hand);
	}
	/**
	 * Getter for the cardCount field
	 * @return an integer representing the current card count
	 */
	public int getCardCount() {
		return cardCount;
	}
	/**
	 * Setter for the card count
	 * @param cardCount an integer representing the card count
	 */
	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}

	/**
	 * getter for chip field
	 * @return an integer representing the current chips the AI has
	 */
	public int getChips() {
		return chips;
	}
	/**
	 * Setter for the chip field
	 * @param chips an integer representing the chips the AI has
	 */
	public void setChips(int chips) {
		this.chips = chips;
	}
}
