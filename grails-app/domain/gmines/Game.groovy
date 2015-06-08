package gmines

class Game {
	String player1
	String player2

	Grid grid

    static constraints = {
        grid display: false
        grid nullable: true
    }

    void stroke(int x, int y) {
    	grid.tryUnfold(x, y)
    }
}