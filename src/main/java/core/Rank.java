package core;

public enum Rank {
	TWO ("2", 2),
	THREE ("3",3),
	FOUR ("4",4) ,
	FIVE ("5",5),
	SIX ("6",6),
	SEVEN ("7",7), 
	EIGHT ("8",8),
	NINE ("9",9),
	TEN ("10",10),
	JACK ("JACK",10),
	QUEEN ("QUEEN",10),
	KING ("KING",10),
	A ("ACE",1);
	
	private final int points;
	private final String name;
	private Rank(String name,int points) {
		this.name= name;
		this.points = points;
	}
	public int getPoints() {
		return points;
	}
	public String getName() {
		return name;
	}
}
