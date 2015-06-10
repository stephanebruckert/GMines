package gmines

class Cell {
	boolean isMine = false
	int isDiscovered = -1
	int nbCellsAdjacent = 0

    static belongsTo = [grid:Grid]

}