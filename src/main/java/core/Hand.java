package core;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> cards = new ArrayList<>();
	
	public void addCard(Card card) {
		cards.add(card);
	}

	public void PrintList() {
		for(int i=0;i<cards.size();i++) {
			System.out.println(cards.get(i));
		}
	}
	public void printOne() {
		System.out.println(cards.get(0));
	}
	private int numOfAs() {
		int count = 0;
		for(int i=0;i<cards.size();i++) {
			if (cards.get(i).getRank() == Rank.A) {
				count++;
			}
		}
		
		return count;
	}
	public int getValue() {
		int total=0;
		for(int i=0;i<cards.size();i++) {
			total +=cards.get(i).getRank().getPoints();
		}
		
		if (numOfAs() > 0 && total + 10 <= 21) {
			total = total + 10;
		}
		return total;
	}

	public boolean isBusted() {
		if (getValue() > 21) {
			return true;
		}
		return false;
		
	}
	public boolean isBackjack() {
		if (getValue() == 21 && cards.size() == 2) {
			return true;
		}
		
		return false;
	}
	
	public boolean canDealerHit() {
		if (getValue() <= 16) {
			return true;
		}
		
		if (getValue() == 17 && numOfAs() > 0 ) {
			return true;
		}
		return false;
	}
}
