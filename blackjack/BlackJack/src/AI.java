import java.util.ArrayList;

public class AI {
	private final int HIT = 1;
	private final int STAND = 2;
	private final int DOUBLE_DOWN = 3;
	private final int SPLIT = 4;
	private final int MIN_BET = 5;
	
	private ArrayList<Hand> hand;
	private int cardCount;
	private int chips;
	
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
	
	public ArrayList<Hand> getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = null;
		this.hand = new ArrayList<Hand>();
		this.hand.add(hand);
	}
	
	public void splitHand(int index) {
		Hand newHand1 = new Hand();
		Hand newHand2 = new Hand();
		
		newHand1.addCard(this.hand.get(index).getCards().get(0));
		newHand2.addCard(this.getHand().get(index).getCards().get(1));
		
		this.hand.remove(index);
		this.hand.add(newHand1);
		this.hand.add(newHand2);
	}
	
	public void addCardToHand(int hand, Card card) {
		this.hand.get(hand).addCard(card);
	}
	
	public int getCardCount() {
		return cardCount;
	}

	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}

//	public boolean isHolding() {
//		return isHolding;
//	}
//
//	public void setHolding(boolean isHolding) {
//		this.isHolding = isHolding;
//	}

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
	}

	public int playHand(int handBeingPlayed, int dealerCard) {
		int strategy;
		
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
//			}else if(hand.get(handBeingPlayed).getHandTotal() == 3 || hand.get(handBeingPlayed).getHandTotal() == 4) {
//				if(dealerCard < 5 || dealerCard > 6) {
//					strategy = 1;
//				}else {
//					strategy = 3;
//				}
//			}
				
			
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
		
		
//		if(strategy == STAND) {
//			this.isHolding = true;
//		}else {
//			this.isHolding = false;
//		}
		return strategy;
	}
	
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
}
