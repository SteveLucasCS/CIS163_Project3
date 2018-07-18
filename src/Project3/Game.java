package Project3;

import java.util.GregorianCalendar;

public class Game extends DVD {

	/** Type of system the game is for **/
	// Xbox360, XBox1, PS4, WiiU, NintendoSwitch
	private PlayerType player;

	public Game() {
		super();
	}

	public Game(
		GregorianCalendar bought,
		GregorianCalendar dueBack,
		String title,
		String name,
		PlayerType system
	) {
		super();
		this.player = system;
	}

	public PlayerType getPlayer() {
		return this.player;
	}

	public void setPlayerType(PlayerType player) {
		// User input is parsed and checked in GUI
		this.player = player;
	}

}
