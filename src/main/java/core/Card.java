package core;

public class Card {

	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Card(String cardName) {
		if (cardName == null || cardName.length() < 1 || cardName.length() > 3) {
			throw new IllegalArgumentException("Invalid cardName: " + cardName);
		}
		String suitName = cardName.substring(0, 1);
		String rankName = cardName.substring(1, cardName.length());
		this.rank = Rank.getRank(rankName);
		this.suit = Suit.valueOf(suitName);
	}
	
	public String toString() {
		return this.suit.toString() + "-" + this.rank.toString();
	}
	
	public String getLongName() {
		return rank.getName() + " of the "+ suit.getName() + "s";
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
}
