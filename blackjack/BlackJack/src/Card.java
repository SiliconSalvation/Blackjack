import java.util.Objects;

public class Card {
	private int value;
	
	public Card(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
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
