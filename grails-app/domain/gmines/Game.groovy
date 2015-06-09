package gmines

class Game {

	Grid grid
	String player1
	String player2
	Date player1lastActivity = new Date()
	Date player2lastActivity
	boolean player2shouldPlay = true
    int actionCount = -1

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

    void stroke(int x, int y, String nickname) {
        if (sessionShouldPlay(nickname)) {
        	if (grid.discover(x, y)) {
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
