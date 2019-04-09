import java.util.ArrayList;

public class Dealer {
	
	private ArrayList<Hand> dealerHand;
	
	public Dealer() {
		 this.dealerHand = new ArrayList<Hand>();
	}
	
	public static void dealerShuffleShoe(Shoe shoe) {
		shoe.shuffle();
	}
	
	public static int dealerCountHand(Hand hand) {
		return hand.getHandTotal();
	}
	
	public void setHand(Hand hand) {
		this.dealerHand = null;
		this.dealerHand = new ArrayList<Hand>();
		dealerHand.add(hand);
	}
	
	public Hand getHand(){
		return this.dealerHand.get(0);
	}
	
	public void printHandShort() {
		System.out.println("Dealer Has: " + dealerHand.get(0).getCards().get(0).getValue());
	}
	
	public void printHandLong() {
		
		for(Hand h : dealerHand) {
			System.out.print("Dealer has: ");
			for(Card c : h.getCards()) {
				System.out.print(c.getValue() + " ");
			}
		}
		System.out.println();
	}
	
	public void addCardToHand(Card card) {
		this.dealerHand.get(0).addCard(card);
	}
//	public static Card dealerDrawsCard(Shoe shoe) {
//		return shoe.drawCard();
//	}
}
