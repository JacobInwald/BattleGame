package battlegame;

public class Main {
	
	private static Game game = new Game();
	
	public Main() {
		
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		game.start();
	}
	
	public static Game getGame() {
		return game;
	}

}
