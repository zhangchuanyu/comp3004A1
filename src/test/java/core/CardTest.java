package core;

import org.junit.Assert;

import org.junit.Test;

public class CardTest {

	@Test
	public void testStringConstructor() {
		Card card = new Card("SK");
		Assert.assertEquals(Suit.S, card.getSuit());
		Assert.assertEquals(Rank.KING, card.getRank());
		
		card = new Card("D10");
		Assert.assertEquals(Suit.D, card.getSuit());
		Assert.assertEquals(Rank.TEN, card.getRank());
		
		card = new Card("CA");
		Assert.assertEquals(Suit.C, card.getSuit());
		Assert.assertEquals(Rank.A, card.getRank());
	}

}
