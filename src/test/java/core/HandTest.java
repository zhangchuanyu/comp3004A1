package core;

import org.junit.Assert;

import org.junit.Test;

public class HandTest {

	@Test
	public void testBlackJack() {
		Hand hand = new Hand();
		Card card = new Card(Suit.C, Rank.A);
		hand.addCard(card);
		card = new Card(Suit.C, Rank.J);
		hand.addCard(card);
		
		Assert.assertTrue("Should be a blackjack", hand.isBackjack());	
		Assert.assertFalse("Should not be busted", hand.isBusted());
	}
	
	@Test
	public void testGetValue() {
		Hand hand = new Hand();
		Card card = new Card(Suit.C, Rank.Q);
		hand.addCard(card);
		card = new Card(Suit.C, Rank.J);
		hand.addCard(card);
		
		Assert.assertEquals("Incorrect hand value", 20, hand.getValue());
		Assert.assertFalse("Should not be a blackjack", hand.isBackjack());
		
		hand = new Hand();
		card = new Card(Suit.C, Rank.A);
		hand.addCard(card);
		card = new Card(Suit.C, Rank.FOUR);
		hand.addCard(card);
		card = new Card(Suit.C, Rank.SIX);
		hand.addCard(card);
		
		Assert.assertEquals("Incorrect hand value", 21, hand.getValue());
		Assert.assertFalse("Should not be a blackjack", hand.isBackjack());
	}
	
	@Test
	public void testGetBusted() {
		Hand hand = new Hand();
		Card card = new Card(Suit.C, Rank.Q);
		hand.addCard(card);
		card = new Card(Suit.C, Rank.J);
		hand.addCard(card);
		card = new Card(Suit.C, Rank.K);
		hand.addCard(card);
		
		Assert.assertTrue("Should be a Busted", hand.isBusted());	
		Assert.assertFalse("Should not be a blackjack", hand.isBackjack());
	}
	

}
