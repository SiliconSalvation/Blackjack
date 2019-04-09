
public class Game {
	private final static int NUMBER_OF_DECKS = 6;
	
	
	
	public static void main(String[] args) {
		
		
		
		AI aiPlayer = new AI(NUMBER_OF_DECKS);
		Dealer dealer = new Dealer();
		Shoe shoe = new Shoe();
		
		int numberOfCardsLeft = shoe.getNumberOfCardsInShoe();
		int count = 0;
		int playerBet;
		
		shoe.shuffle();
		shoe.drawCard(count);
		count++;
		numberOfCardsLeft--;
		
		do {
			Hand AIHand = new Hand();
			Hand DealerHand = new Hand();
			
			playerBet = aiPlayer.placeBet();
			aiPlayer.setChips(aiPlayer.getChips() - playerBet);
			//Deal Cards
			for(int i = 0; i < 2; i++) {
				AIHand.addCard(shoe.drawCard(count));
				
				count++;
				numberOfCardsLeft--;
				DealerHand.addCard(shoe.drawCard(count));
				
				count++;
				numberOfCardsLeft--;
			}
			aiPlayer.setHand(AIHand);
			dealer.setHand(DealerHand);
			
			aiPlayer.printHand();
			dealer.printHandShort();
			//Dealer Checks Dealer Hand for BlackJack
			if(dealer.getHand().getHandTotal() == 21) {
				System.out.println("Dealer has a blackjack");
				if(aiPlayer.getHand().get(0).getHandTotal() == 21) {
					aiPlayer.setChips(playerBet + aiPlayer.getChips());
				}
				AIHand = null;
				DealerHand = null;
				dealer.printHandLong();
				continue;
			}
			
			//Dealer Checks Player's hands for BlackJack
			if(Dealer.dealerCountHand(aiPlayer.getHand().get(0)) == 21) {
				System.out.println("Player has BlackJack");
				aiPlayer.setChips(aiPlayer.getChips() + (2 * playerBet));
			}
			//Dealer Deals Cards to Players
			int playerDecision;
			for(int i = 0; i < aiPlayer.getHand().size(); i++) {
				while(aiPlayer.getHand().get(i).isHolding() == false) {
					playerDecision = aiPlayer.playHand(i, dealer.getHand().getCards().get(0).getValue());
					
					if(playerDecision == 1) {
						System.out.println("Player Hits");
					}else if(playerDecision == 2) {
						System.out.println("Player Stands");
					}else if(playerDecision == 3) {
						System.out.println("Player Doubles Down");
					}else {
						System.out.println("Player Splits");
					}
					
					if(playerDecision == 1) {
						aiPlayer.addCardToHand(i, shoe.drawCard(count));
						count++;
						numberOfCardsLeft--;
						aiPlayer.printHand();
					}else if(playerDecision == 2) {
						aiPlayer.getHand().get(i).setStandOnHand(true);
					}else if(playerDecision == 3) {
						playerBet = playerBet * 2;
						aiPlayer.setChips(aiPlayer.getChips() - playerBet);
						aiPlayer.addCardToHand(i, shoe.drawCard(count));
						count++;
						numberOfCardsLeft--;
						aiPlayer.getHand().get(i).setStandOnHand(true);
						aiPlayer.printHand();
					}else if(playerDecision == 4) {
						aiPlayer.splitHand(i);
						
						for(int j = 0; j < aiPlayer.getHand().size(); j++) {
							if(aiPlayer.getHand().get(j).getCards().size() < 2) {
								aiPlayer.addCardToHand(j, shoe.drawCard(count));
								count++;
								numberOfCardsLeft--;
								
								if(Dealer.dealerCountHand(aiPlayer.getHand().get(j)) == 22){
									aiPlayer.getHand().get(j).setStandOnHand(true);
								}
							}
						}
					}
					aiPlayer.printHand();
				}
			}
			//Dealer Draws Cards to themself
			dealer.printHandLong();
			while(Dealer.dealerCountHand(dealer.getHand()) < 17) {
				dealer.addCardToHand(shoe.drawCard(count));
				count++;
				numberOfCardsLeft--;
				dealer.printHandLong();
			}
			
			//Dealer Pays Winners
			
			//Dealer Discards Cards
			
			AIHand = null;
			DealerHand = null;
			
//			aiPlayer.setHand(null);
//			dealer.setHand(null);
		}while(numberOfCardsLeft > 30);
		
	}

}
