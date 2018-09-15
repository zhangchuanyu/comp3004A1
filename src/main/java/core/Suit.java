package core;

public enum Suit {
	C ("Club"),D ("Diamond"),S("Spade"),H("Heart");
	
	private final String name;
	
	private Suit(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
