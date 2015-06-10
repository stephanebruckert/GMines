package gmines

class Game {

	Grid grid
	String player1
	String player2
	Date player1lastActivity = new Date()
	Date player2lastActivity
	boolean player2shouldPlay = true
    int actionCount = -1
    int player1minesFound = 0
    int player2minesFound = 0

    static constraints = {
        grid display: false, nullable: true
        actionCount display: false
        player1 nullable: true, display: false
        player2 nullable: true, display: false
        player1lastActivity display: false
        player2lastActivity display: false, nullable: true
        player2shouldPlay display: false
        player1minesFound display: false
        player2minesFound display: false
    }

    void stroke(int x, int y, String nickname) {
        if (sessionShouldPlay(nickname)) {
        	if (grid.discover(x, y, nickname)) {
                actionCount++;
            }
        }
    }

    // Lets us know whether the session who played
    // should have played
    boolean sessionShouldPlay(String nickname) {
        if (nickname.equals(player1) && !player2shouldPlay) {
            return true
        } else if (nickname.equals(player2) && player2shouldPlay) {
            return true
        } else {
            return false
        }
    }
}
