package gmines

class Game {
	String player1
	String player2
	Grid grid

    static constraints = {
        grid display: false
    }

    Game() {
    	grid = new Grid()
    }

}
