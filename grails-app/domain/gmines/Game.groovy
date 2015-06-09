package gmines

class Game {

	Grid grid
	String player1
	String player2
	Date player1lastActivity = new Date()
	Date player2lastActivity
	boolean player2shouldPlay = true

    static constraints = {
        grid display: false
        grid nullable: true
        player1 nullable: true
        player2 nullable: true
        player1lastActivity display: false
        player2lastActivity display: false
        player2lastActivity nullable: true
        player2shouldPlay display: false
    }

    void stroke(int x, int y) {
    	grid.discover(x, y)
    }
}
