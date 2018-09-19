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
		// Scanner fn = new Scanner(System.in);
		String str;
		int hitagain = 0;
		// private List<Card> cards = new ArrayList<>();
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		// System.out.println("please enter the file name:");
		// String FN = fn.nextLine();
		String FN = "/Users/xuyijie/Desktop/a1.txt";
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

			if (spStr.length < 4) {
				System.out.println("Invalid string: " + str);
				continue;
			}

			Card card1 = new Card(spStr[0]);
			Card card2 = new Card(spStr[1]);
			Card card3 = new Card(spStr[2]);
			Card card4 = new Card(spStr[3]);
			playerHand.addCard(card1);
			playerHand.addCard(card2);
			System.out.println("Player receives the " + card1.getLongName() + " and " + card2.getLongName());

			dealerHand.addCard(card3);
			dealerHand.addCard(card4);
			System.out.println("dealer receives the " + card3.getLongName() + " and " + card4.getLongName());

			for (int i = 3; i < spStr.length; i++) {

				// if there are any stands.
				if (spStr[i].equals("S")) {
					System.out.println("Player Stands");
					for (int j = i + 1; j < spStr.length; j++) {
						Card card = new Card(spStr[j]);
						if (!dealerHand.canDealerHit()) {
							System.out.println("Dealer cannot hit. Invalid input card " + card.getLongName());
							break;
						}
						System.out.println("dealer has " + dealerHand.getValue()
								+ " and  thus must hit it, and get card " + card.getLongName());
						dealerHand.addCard(card);
					}
					break;

				} else if (spStr[i].equals("H")) {
					Card card7 = new Card(spStr[i + 1]);
					playerHand.addCard(card7);
					i++;
					String again = "";
					if (hitagain > 0) {
						again = " again ";
					}
					System.out.println("Player hit " + again + " and gets " + card7.getLongName() + ":Hand value is "
							+ playerHand.getValue());
					hitagain++;// hit again.
				}

			}

			checkIfGameOver(dealerHand, playerHand, true);
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
				gameover = checkIfGameOver(dealerHand, playerHand, false);

				while (!gameover) {
					System.out.println("Do you want to hit(1) or stand(2)? 1/2:?");
					// player choose to hit.
					int input = reader.nextInt();
					switch (input) {
					case 1:
						playerHand.addCard(playingDeck.draw());
						System.out.println("your hand are: ");
						playerHand.PrintList();
						System.out.println("your hand values are:" + playerHand.getValue());
						gameover = checkIfGameOver(dealerHand, playerHand, false);
						break;
					case 2:
						// dealer round
						while (dealerHand.canDealerHit() && !gameover) {
							Card card = playingDeck.draw();
							System.out.println("the dealer draw:" + card);
							dealerHand.addCard(card);
							gameover = checkIfGameOver(dealerHand, playerHand, false);
							System.out.println("the dealer cards value is :" + dealerHand.getValue());
						}

						if (!gameover) {
							gameover = checkIfGameOver(dealerHand, playerHand, true);
						}
						break;
					default:
						System.out.println("Invalid input: " + input);
					}

				}

				// playerHand.PrintList();

			} else if (n == 2) {
				break;
			}
		}
		reader.close();
	}

	public boolean checkIfGameOver(Hand dealerHand, Hand playerHand, boolean dealerStand) {
		if (dealerHand.isBackjack()) {
			System.out.println("dealer blackjack wins.");
			System.out.println("dealerHand are:");
			dealerHand.PrintList();
			return true;
		}

		if (playerHand.isBusted()) {
			System.out.println("dealer wins as player busted");
			System.out.println("dealerHand are:");
			dealerHand.PrintList();
			return true;
		}

		if (playerHand.isBackjack()) {
			System.out.println("player blackjack wins");
			System.out.println("dealerHand are:");
			dealerHand.PrintList();
			return true;
		}

		if (dealerHand.isBusted()) {
			System.out.println("player wins as dealer busted.");
			System.out.println("dealerHand are:");
			dealerHand.PrintList();
			
			return true;
		}

		if (dealerStand) {
			if (dealerHand.getValue() > playerHand.getValue()) {
				System.out.println("dealer wins " + dealerHand.getValue() + " : " + playerHand.getValue());
				System.out.println("dealerHand are:");
				dealerHand.PrintList();
			} else if (dealerHand.getValue() < playerHand.getValue()) {
				System.out.println("player wins " + playerHand.getValue() + " : " + dealerHand.getValue());
				System.out.println("dealerHand are:");
				dealerHand.PrintList();
			} else {
				System.out.println("push " + dealerHand.getValue() + " : " + playerHand.getValue());
				System.out.println("dealerHand are:");
				dealerHand.PrintList();
			}
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		Blackjack bj = new Blackjack();
		bj.play();
		// System.out.println(playingDeck);
	}
}
