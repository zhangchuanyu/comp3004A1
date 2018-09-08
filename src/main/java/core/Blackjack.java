package core;
import java.util.Scanner;


public class Blackjack {
	public void play() {
		while(true) {
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("welcome to blackjack!");
			System.out.println("1 - Start a new Game.\n2 - Quit.:");
			Hand playerHand = new Hand();
			Hand dealerHand = new Hand();
			int n = reader.nextInt();
			if (n==1) {
				Deck playingDeck= new Deck();
				playingDeck.shuffle();
				
				playerHand.addCard(playingDeck.draw());
				playerHand.addCard(playingDeck.draw());
				dealerHand.addCard(playingDeck.draw());
				dealerHand.addCard(playingDeck.draw());
				playerHand.PrintList();
				dealerHand.PrintList();
				System.out.println("Do you want to hit(1) or stand(2)? 1/2:?");
				
				while (reader.nextInt() == 1)
				{
					playerHand.addCard(playingDeck.draw());
					playerHand.PrintList();
				}

				while (dealerHand.canDealerHit()) {
					dealerHand.addCard(playingDeck.draw());
				}
				playerHand.PrintList();
				dealerHand.PrintList();
				
			}
			if(n==2) {
				
				break;
			}
		
		}
	}
	public boolean isDealerwin(Hand dealerHand, Hand playerHand) {
		if(dealerHand.isBackjack())
			return true;
		else if (playerHand.isBusted())
			return true;
		else
			return false;
		
		
	}
	public boolean isPlayerwin(Hand dealerHand, Hand playerHand) {
		if(playerHand.isBackjack())
			return true;
		else if (dealerHand.isBusted())
			return true;
		else
			return false;
		
		
	}
	public static void main(String[] args) {
		Blackjack bj = new Blackjack();
		bj.play();
		//System.out.println(playingDeck);
	}
}
