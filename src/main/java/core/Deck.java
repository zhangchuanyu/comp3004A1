package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<Card> cards;
	private int i=0;
	public Deck() {
		this.cards = new ArrayList<Card>();
		createFullDeck();
	}

	private void createFullDeck() {
		for (Suit cardcolors : Suit.values()) {
			for (Rank cardvalue : Rank.values()) {
				this.cards.add(new Card(cardcolors, cardvalue));
			}
		}
	}

	public int getSize() {
		return cards.size();
	}
	public void shuffle() {
		Collections.shuffle(cards);
	}

	public void removeCard(int i) {
		this.cards.remove(i);
	}

	public Card getCard(int i) {
		return this.cards.get(i);
	}

	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}

	public Card draw() {
		if (i > 51) {
			return null;
		}
		Card card = cards.get(i);
		i++;
		return card;
	}

	public String toString() {
		String cardListOutput = "";
		int i = 0;
		for (Card aCard : this.cards) {
			cardListOutput += "\n" + i + "-" + aCard.toString();
			i++;
		}
		return cardListOutput;
	}
}
