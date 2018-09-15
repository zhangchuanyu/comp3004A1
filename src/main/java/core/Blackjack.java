package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	public void play() {
		Scanner input = new Scanner(System.in); // Reading from System.in
		System.out.println("welcome to blackjack!");
		System.out.println("you want to play in console(1) or file(2)");
		int gameMode = input.nextInt();
		if (gameMode == 1) {
			playconsole();
		} else if (gameMode == 2) {
			playfile();
		}
		input.close();
	}

	public void playfile() {
		//Scanner fn = new Scanner(System.in);
		String str;
		// private List<Card> cards = new ArrayList<>();
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		// System.out.println("please enter the file name:");
		// String FN = fn.nextLine();
		String FN = "/tmp/file1.txt";
		File file = new File(FN);
		Scanner scanner;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		while (scanner.hasNextLine()) {
			str = scanner.nextLine();
			System.out.println(str);
			String[] spStr = str.split("\\s+");
			System.out.println(spStr[2]);
			for (int i = 0; i < spStr.length; i++) {
				if (i < 2) {
					Card card1 = new Card(Suit.valueOf(spStr[0].substring(0, 1)),
							Rank.valueOf(spStr[0].substring(1, 2)));
					playerHand.addCard(card1);
					Card card2 = new Card(Suit.valueOf(spStr[1].substring(0, 1)),
							Rank.valueOf(spStr[1].substring(1, 2)));
					playerHand.addCard(card2);
					System.out.println("Player receives the "+ card1.getRank().getPoints()+" of the "+card1.getSuit().getName()
							+" and "+ card2.getRank().getPoints() + " of the "+ card2.getSuit().getName());
				} else if (i>=2 && i<4){
					Card card3 = new Card(Suit.valueOf(spStr[2].substring(0, 1)),
							Rank.valueOf(spStr[i].substring(1, 2)));
					Card card4 = new Card(Suit.valueOf(spStr[3].substring(0, 1)),
							Rank.valueOf(spStr[i].substring(1, 2)));
					dealerHand.addCard(card3);
					dealerHand.addCard(card4);
					System.out.println("Dealer receives the "+ card3.getRank().getPoints()+" of the "+card3.getSuit().getName()
							+" and "+ card4.getRank().getPoints() + " of the "+ card4.getSuit().getName());
				}
				else if (i==4)
				{
					if(spStr[i].equals('S')) {
						System.out.println("Player Stands");
						//dealer round.
						if(dealerHand.canDealerHit()) {
							System.out.println("dealer has "+ dealerHand.getValue() + " and  thus must hit it.");
						}
					}
					if(spStr[i].equals('H')) {}
					if(spStr[i].equals('D')){}
				}
			}
		}
		scanner.close();

	}

	public void playconsole() {
		Deck playingDeck = new Deck();
		playingDeck.shuffle();
		Scanner reader = new Scanner(System.in);
		while (true) {
			System.out.println("1 - Start a new Game.\n" + "2 - Quit.:");
			Hand playerHand = new Hand();
			Hand dealerHand = new Hand();
			int n = reader.nextInt();
			boolean gameover = false;
			if (n == 1) {
				playerHand.addCard(playingDeck.draw());
				playerHand.addCard(playingDeck.draw());
				dealerHand.addCard(playingDeck.draw());
				dealerHand.addCard(playingDeck.draw());
				System.out.println("Your Hand:");
				playerHand.PrintList();
				System.out.println("Your Hand value is:" + playerHand.getValue());
				System.out.println("the first card of dealer is :");
				dealerHand.printOne();
				if (isPlayerwin(dealerHand, playerHand)) {
					System.out.println("you win!");
					gameover = true;
				}

				while (!gameover) {
					System.out.println("Do you want to hit(1) or stand(2)? 1/2:?");
					// player choose to hit.
					if (reader.nextInt() == 1) {
						playerHand.addCard(playingDeck.draw());
						System.out.println("your hand are: ");
						playerHand.PrintList();
						System.out.println("your hand values are:" + playerHand.getValue());
						if (isPlayerwin(dealerHand, playerHand)) {
							System.out.println("you win!");
							gameover = true;
						} else if (isDealerwin(dealerHand, playerHand)) {
							System.out.println("the dealer's cards are:");
							dealerHand.PrintList();
							System.out.println("the dealer's cards values are:" + dealerHand.getValue());
							System.out.println("dealer win you lose.");
							gameover = true;
						}
					} else if (reader.nextInt() == 2) {
						// dealer round
						while (dealerHand.canDealerHit() && !gameover) {
							System.out.println("the dealer draw:" + playingDeck.getCard(0));
							dealerHand.addCard(playingDeck.draw());
							if (isDealerwin(dealerHand, playerHand)) {
								System.out.println("the dealer win!");
								dealerHand.PrintList();
								gameover = true;
							} else if (isPlayerwin(dealerHand, playerHand)) {
								System.out.println("the Player win!");
								gameover = true;
							}
							System.out.println("the dealer cards value is :" + dealerHand.getValue());
						}

						if (!gameover) {
							if (playerHand.getValue() == dealerHand.getValue()) {
								System.out.println("push");
							} else if (playerHand.getValue() > dealerHand.getValue()) {
								System.out.println("you win the hand.");
							} else {
								System.out.println("the dealer win!");
							}
						}
						gameover = true;
					}

				}

				// playerHand.PrintList();

			} else if (n == 2) {
				break;
			}
		}
		reader.close();
	}

	public boolean isDealerwin(Hand dealerHand, Hand playerHand) {
		if (dealerHand.isBackjack())
			return true;
		else if (playerHand.isBusted())
			return true;
		else
			return false;

	}

	public boolean isPlayerwin(Hand dealerHand, Hand playerHand) {
		if (playerHand.isBackjack())
			return true;
		else if (dealerHand.isBusted())
			return true;
		else
			return false;

	}

	public static void main(String[] args) {
		Blackjack bj = new Blackjack();
		bj.play();
		// System.out.println(playingDeck);
	}
}
