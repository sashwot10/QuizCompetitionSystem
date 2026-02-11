package coursework.test;

import coursework.*;

public class DBtest {
	public static void main(String[] args) {
	    Player p1 = new Player(300, new Name("David", "Black"), Level.ADVANCED, 25);
	    p1.saveToDB(); // already exists, should print warning

	    Player p2 = new Player(301, new Name("Carol", "White"), Level.ADVANCED, 26);
	    p2.saveToDB(); // new player, should insert successfully
	
    }
}