import java.util.Objects;
/**
 * The Card class represents a card, as played in blackjack
 * @author Nick Stauffer
 * @author staunw01@pfw.edu
 */
public class Card {
	private int value;			//An integer representing the value of the card
	/**
	 * Constructor that sets the value of the card
	 * @param value
	 */
	public Card(int value) {
		this.value = value;
	}
	/**
	 * Getter for the value field
	 * @return an integer representing the card's value
	 */
	public int getValue() {
		return this.value;
	}
	/**
	 * Determines equality of a card object to this one
	 */
	public final boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(!(o instanceof Card)) {
			return false;
		}
		Card card = (Card) o;
		
		return Objects.equals(value, card.getValue());
	}
}
