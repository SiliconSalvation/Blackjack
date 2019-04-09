import java.util.Random;

public class Shoe {
	private Card[] cards = new Card[312];
	
	public Shoe() {
		int[] cardValues = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
		
//		for(int i = 0; i < cards.length; i++) {
//			cards[i] = new Card(cardValues[i % 13]);
//		}
		
		for(int i = 0; i < cards.length; i++) {
			cards[i] = new Card(11);
		}
	}
	
	public void shuffle() {
		Random rand = new Random(); 
        
        for (int i = 0; i < cards.length; i++) { 
            // Random for remaining positions. 
            int r = i + rand.nextInt(312 - i); 
              
             //swapping the elements 
             Card temp = cards[r]; 
             this.cards[r] = this.cards[i]; 
             this.cards[i] = temp; 
               
        } 
	}
	
	public Card drawCard(int count) {
		return cards[count];
	}
	
	public int getNumberOfCardsInShoe() {
		return cards.length;
	}
}
