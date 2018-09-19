package core;

import static org.junit.Assert.*;

import org.junit.Test;


public class DeckTest {

	@Test
	public void testNewDeck() {
		Deck deck = new Deck();
		Hand hand = new Hand();
		int size = deck.getSize();
		assertEquals("Incorrect deck size", 52, size);
		
		System.out.println("before shuffle");
		
		System.out.println(deck.toString());
		
		deck.shuffle();
		assertEquals("Incorrect deck size", 52, size);
		
		System.out.println("after shuffle");
		
		System.out.println(deck.toString());
		hand.addCard(deck.draw());
		System.out.println("the card draw is :");
		hand.PrintList();
		
		
	}
	


}
