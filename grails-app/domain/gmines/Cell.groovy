package gmines

class Cell {
	boolean isMine = false
	boolean isDiscovered = false
	int nbCellsAdjacent = 0

    static belongsTo = [grid:Grid]

    static constraints = {
    }

    void setMine() {
    	isMine = true
    }
}