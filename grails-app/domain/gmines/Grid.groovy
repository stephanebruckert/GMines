package gmines

import gmines.*

class Grid {
	int nbBomb = 51
	int edgeSize = 16

	List cells = []

	static belongsTo = [game:Game]

	static hasMany = [cells:Cell]

	static constraints = {
		
	}

	/**
	 * Initialization of all cells on the grid
	 */
	void init() {
		for(int x = 0; x < 16*16 ; x++) {
			addToCells(new Cell())
		}
    }

	/**
	 * Filling all cells with random data
	 */    
	 void fillGrid() {
		int nbBombToPut = 0
		int random
		while(nbBombToPut < nbBomb){

			// Take a random coordinate
			random = Math.abs(new Random().nextInt() % (16*16-1) + 1)

			if(!cells[random].isMine) {
				cells[random].isMine = true
				nbBombToPut++
			}
		}
    }

    /**
	 * Set count for adjacents mines 
	 */
	void findAdjacentCell() {
		int adjacentCellsCount ;
		Cell[][] cellz = cells.collate(edgeSize)

		for (int x = 0; x < edgeSize; x++) {
			for (int y = 0; y < edgeSize; y++) {
				adjacentCellsCount = 0;

				if (x<(edgeSize-1) && cellz[x+1][y].isMine) 					{ adjacentCellsCount++ }
				if (x<(edgeSize-1) && y<(edgeSize-1) && cellz[x+1][y+1].isMine) { adjacentCellsCount++ }
				if (y<(edgeSize-1) && cellz[x][y+1].isMine) 					{ adjacentCellsCount++ }
				if (y<(edgeSize-1) && x>0 && cellz[x-1][y+1].isMine) 			{ adjacentCellsCount++ }
				if (x>0 && cellz[x-1][y].isMine) 								{ adjacentCellsCount++ }
				if (x>0 && y>0 && cellz[x-1][y-1].isMine)						{ adjacentCellsCount++ }
				if (y>0 && cellz[x][y-1].isMine) 								{ adjacentCellsCount++ }
				if (x<(edgeSize-1) && y>0 && cellz[x+1][y-1].isMine) 			{ adjacentCellsCount++ }

				cellz[x][y].nbCellsAdjacent = adjacentCellsCount
			}
		}
	}

	public void discover(int x, int y) {
		Cell[][] cellz = cells.collate(edgeSize)
		if (!cellz[x][y].isMine && cellz[x][y].nbCellsAdjacent == 0) {
			unfoldGaps(x, y, cellz)
			unfoldBorder(cellz)
		} else {
			cellz[x][y].isDiscovered = true
		}
	}

	/**
	 * When player click on a cell which has not adjacent mine, we search recursivly all other
	 * adjacent cell which has not adjacents mines (see result in game, it's when cell picture has no number)
	 */
	public void unfoldGaps(int x, int y, Cell[][] cellz){
		cellz[x][y].isDiscovered = true

		if( x<(edgeSize-1) && !cellz[x+1][y].isMine && cellz[x+1][y].nbCellsAdjacent == 0 && !cellz[x+1][y].isDiscovered) {
			unfoldGaps(x+1, y, cellz)
		}
		if( y<(edgeSize-1) && !cellz[x][y+1].isMine && cellz[x][y+1].nbCellsAdjacent == 0 && !cellz[x][y+1].isDiscovered) {
			unfoldGaps(x, y+1, cellz)
		}
		if( x>0 && !cellz[x-1][y].isMine && cellz[x-1][y].nbCellsAdjacent == 0 && !cellz[x-1][y].isDiscovered) {
			unfoldGaps(x-1, y, cellz)
		}
		if( y>0 && !cellz[x][y-1].isMine && cellz[x][y-1].nbCellsAdjacent == 0 && !cellz[x][y-1].isDiscovered) {
			unfoldGaps(x, y-1, cellz)
		}
		if( x<(edgeSize-1) && y<(edgeSize-1) && !cellz[x+1][y+1].isMine && cellz[x+1][y+1].nbCellsAdjacent == 0 && !cellz[x+1][y+1].isDiscovered) {
			unfoldGaps(x+1, y+1, cellz)
		}
		if( x>0 && y<(edgeSize-1) && !cellz[x-1][y+1].isMine && cellz[x-1][y+1].nbCellsAdjacent == 0 && !cellz[x-1][y+1].isDiscovered) {
			unfoldGaps(x-1, y+1, cellz)
		}
		if( x>0 && y>0 && !cellz[x-1][y-1].isMine && cellz[x-1][y-1].nbCellsAdjacent == 0 && !cellz[x-1][y-1].isDiscovered) {
			unfoldGaps(x-1, y-1, cellz)
		}
		if( x<(edgeSize-1) && y>0 && !cellz[x+1][y-1].isMine && cellz[x+1][y-1].nbCellsAdjacent == 0 && !cellz[x+1][y-1].isDiscovered) {
			unfoldGaps(x+1, y-1, cellz)
		}
	}

	/**
	 * When we click in a cell which has no adjacents mines, we discover
	 * the cells border, so the eight adjacents cells. These cells contains
	 * perforce number.
	 */
	public void unfoldBorder(Cell[][] cellz){
		for (int x = 0; x < edgeSize ; x++) {
			for (int y = 0; y < edgeSize; y++) {
				if( cellz[x][y].nbCellsAdjacent == 0 && cellz[x][y].isDiscovered && !cellz[x][y].isMine ) {

					if( x<(edgeSize-1) 
						&& !cellz[x+1][y].isDiscovered) {
						cellz[x+1][y].isDiscovered = true
					}
					if( x<(edgeSize-1) && y<(edgeSize-1)  
						&& !cellz[x+1][y+1].isDiscovered) {
						cellz[x+1][y+1].isDiscovered = true
					}
					if( y<(edgeSize-1) 
						&& !cellz[x][y+1].isDiscovered) {
						cellz[x][y+1].isDiscovered = true
					}
					if( y<(edgeSize-1) && x>0 
						&& !cellz[x-1][y+1].isDiscovered) {
						cellz[x-1][y+1].isDiscovered = true
					}
					if( x>0 
						&& !cellz[x-1][y].isDiscovered) {
						cellz[x-1][y].isDiscovered = true
					}
					if( x>0 && y>0 
						&& !cellz[x-1][y-1].isDiscovered) {
						cellz[x-1][y-1].isDiscovered = true
					}
					if( y>0 
						&& !cellz[x][y-1].isDiscovered) {
						cellz[x][y-1].isDiscovered = true
					}
					if( x<(edgeSize-1) && y>0 
						&& !cellz[x+1][y-1].isDiscovered) {
						cellz[x+1][y-1].isDiscovered = true
					}

				}
			}
		}
	}

	public String[][] getDisplayableGrid() {
		String[][] icons = new String[edgeSize][edgeSize]
		Cell[][] cellz = cells.collate(edgeSize)
		Cell cell
		for (int x = 0; x < edgeSize ; x++) {
			for (int y = 0; y < edgeSize; y++) {
				cell = cellz[x][y]
				if (!cell.isDiscovered) {
					icons[x][y] = "game/bombHover.png"
				} else if (cell.isMine) {
					icons[x][y] = "game/hisMine.png"
				} else {
					icons[x][y] = "game/" + cell.nbCellsAdjacent + ".png"
				}
			}
		}
		return icons
	}
}
