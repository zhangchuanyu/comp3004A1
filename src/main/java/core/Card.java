package core;

public class Card {

	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.rank = rank;
		this.suit = suit;
	}

	public String toString() {
		return this.suit.toString() + "-" + this.rank.toString();
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
}
