import java.util.Random;
/**
 * The Shoe class represents a blackjack shoe
 * @author Nick Stauffer
 * @author staunw01@pfw.edu
 */
public class Shoe {
	private Card[] cards;		//Array of card objects
	
	/**
	 * Constructor that instantiates the cards field using the amount of decks being played
	 * @param numberOfDecks the amount of decks in the shoe
	 */
	public Shoe(int numberOfDecks) {
		this.cards = new Card[numberOfDecks * 52];
		final int[] cardValues = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
		
		for(int i = 0; i < cards.length; i++) {
			cards[i] = new Card(cardValues[i % 13]);
		}
		
	}
	/**
	 * Randomizes the cards in the deck
	 */
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
	/**
	 * Returns the card in the Array at param count
	 * @param count the card to be drawn from the array
	 * @return a Card object representing the card being drawn
	 */
	public Card drawCard(int count) {
		return cards[count];
	}
	/**
	 * gets the amount of cards in the shoe
	 * @return an integer representing the amount of cards in the shoe
	 */
	public int getNumberOfCardsInShoe() {
		return cards.length;
	}
}
