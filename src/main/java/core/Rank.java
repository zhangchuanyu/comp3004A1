package core;

public enum Rank {
	TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE("9",
			9), TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10), A("A", 1);

	private final int points;
	private final String name;

	private Rank(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public String getName() {
		return name;
	}

	public static Rank getRank(String rankName) {
		switch (rankName) {
		case "2":
			return Rank.TWO;
		case "3":
			return Rank.THREE;
		case "4":
			return Rank.FOUR;
		case "5":
			return Rank.FIVE;
		case "6":
			return Rank.SIX;
		case "7":
			return Rank.SEVEN;
		case "8":
			return Rank.EIGHT;
		case "9":
			return Rank.NINE;
		case "10":
			return Rank.TEN;
		case "J":
			return Rank.JACK;
		case "Q":
			return Rank.QUEEN;
		case "K":
			return Rank.KING;
		case "A":
			return Rank.A;
		default:
			return null;
		}
	}
}
