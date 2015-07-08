package gmines
import grails.rest.*

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
    int winner = -1

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
        winner display: false
    }

    void stroke(int num, String nickname) {
        int x = num / 16
        int y = num % 16
        // if it's the player's turn and the game is not over yet
        if (sessionShouldPlay(nickname) && winner < 0) {
        	if (grid.discover(x, y, nickname)) {
                actionCount++;
                winner = isThereWinnerNow()
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

    int isThereWinnerNow() {
        if (player1minesFound >= 26) {
            // player 1 won
            return 0
        } else if (player2minesFound >= 26) {
            // player 2 won
            return 1
        } else {
            // no winner yet
            return -1
        }
    }
}
