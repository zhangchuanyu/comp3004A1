package core;

import static org.junit.Assert.*;

import org.junit.Test;


public class DeckTest {

	@Test
	public void testNewDeck() {
		Deck deck = new Deck();
		int size = deck.getSize();
		assertEquals("Incorrect deck size", 52, size);
		
		System.out.println("before shuffle");
		
		System.out.println(deck.toString());
		
		deck.shuffle();
		
		System.out.println("after shuffle");
		
		System.out.println(deck.toString());
		
		
	}

}
