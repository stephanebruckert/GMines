package gmines

import gmines.*
import java.awt.Point

class Grid {
	int nbBomb = 51
	int edgeSize = 16

	List cells = []

	Point lastPlayer1
	Point lastPlayer2

	static belongsTo = [game:Game]

	static hasMany = [cells:Cell]

	static constraints = {
		lastPlayer1 nullable: true
		lastPlayer2 nullable: true
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

	public boolean discover(int x, int y, String nickname) {
		Cell[][] cellz = cells.collate(edgeSize)
		if (cellz[x][y].isDiscovered >= 0) {
			// action is not relevant
			return false
		} else {
			// action is relevant
			if (!cellz[x][y].isMine && cellz[x][y].nbCellsAdjacent == 0) {
				// cell 0
				game.player2shouldPlay = !game.player2shouldPlay
				unfoldGaps(x, y, cellz)
				unfoldBorder(cellz)
			} else if (cellz[x][y].isMine) {
				// mine
				if (nickname.equals(game.player1)) {
					cellz[x][y].isDiscovered = 1
					game.player1minesFound++
				} else {
					cellz[x][y].isDiscovered = 0
					game.player2minesFound++
				}
			} else {
				// cell 1 2 3 4 5 6 7 or 8
				game.player2shouldPlay = !game.player2shouldPlay
				cellz[x][y].isDiscovered = 1
			}
			if (nickname.equals(game.player1)) {
				lastPlayer1 = new Point(x, y)
			} else {
				lastPlayer2 = new Point(x, y)
			}
			return true
		}
	}

	/**
	 * When player click on a cell which has not adjacent mine, we search recursivly all other
	 * adjacent cell which has not adjacents mines (see result in game, it's when cell picture has no number)
	 */
	public void unfoldGaps(int x, int y, Cell[][] cellz){
		cellz[x][y].isDiscovered = 1

		if( x<(edgeSize-1) && !cellz[x+1][y].isMine && cellz[x+1][y].nbCellsAdjacent == 0 && cellz[x+1][y].isDiscovered == -1) {
			unfoldGaps(x+1, y, cellz)
		}
		if( y<(edgeSize-1) && !cellz[x][y+1].isMine && cellz[x][y+1].nbCellsAdjacent == 0 && cellz[x][y+1].isDiscovered == -1) {
			unfoldGaps(x, y+1, cellz)
		}
		if( x>0 && !cellz[x-1][y].isMine && cellz[x-1][y].nbCellsAdjacent == 0 && cellz[x-1][y].isDiscovered == -1) {
			unfoldGaps(x-1, y, cellz)
		}
		if( y>0 && !cellz[x][y-1].isMine && cellz[x][y-1].nbCellsAdjacent == 0 && cellz[x][y-1].isDiscovered == -1) {
			unfoldGaps(x, y-1, cellz)
		}
		if( x<(edgeSize-1) && y<(edgeSize-1) && !cellz[x+1][y+1].isMine && cellz[x+1][y+1].nbCellsAdjacent == 0 && cellz[x+1][y+1].isDiscovered == -1) {
			unfoldGaps(x+1, y+1, cellz)
		}
		if( x>0 && y<(edgeSize-1) && !cellz[x-1][y+1].isMine && cellz[x-1][y+1].nbCellsAdjacent == 0 && cellz[x-1][y+1].isDiscovered == -1) {
			unfoldGaps(x-1, y+1, cellz)
		}
		if( x>0 && y>0 && !cellz[x-1][y-1].isMine && cellz[x-1][y-1].nbCellsAdjacent == 0 && cellz[x-1][y-1].isDiscovered == -1) {
			unfoldGaps(x-1, y-1, cellz)
		}
		if( x<(edgeSize-1) && y>0 && !cellz[x+1][y-1].isMine && cellz[x+1][y-1].nbCellsAdjacent == 0 && cellz[x+1][y-1].isDiscovered == -1) {
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
				if( cellz[x][y].nbCellsAdjacent == 0 && cellz[x][y].isDiscovered >= 0 && !cellz[x][y].isMine ) {

					if( x<(edgeSize-1) 
						&& cellz[x+1][y].isDiscovered == -1) {
						cellz[x+1][y].isDiscovered = 1
					}
					if( x<(edgeSize-1) && y<(edgeSize-1)  
						&& cellz[x+1][y+1].isDiscovered == -1) {
						cellz[x+1][y+1].isDiscovered = 1
					}
					if( y<(edgeSize-1) 
						&& cellz[x][y+1].isDiscovered == -1) {
						cellz[x][y+1].isDiscovered = 1
					}
					if( y<(edgeSize-1) && x>0 
						&& cellz[x-1][y+1].isDiscovered == -1) {
						cellz[x-1][y+1].isDiscovered = 1
					}
					if( x>0 
						&& cellz[x-1][y].isDiscovered == -1) {
						cellz[x-1][y].isDiscovered = 1
					}
					if( x>0 && y>0 
						&& cellz[x-1][y-1].isDiscovered == -1) {
						cellz[x-1][y-1].isDiscovered = 1
					}
					if( y>0 
						&& cellz[x][y-1].isDiscovered == -1) {
						cellz[x][y-1].isDiscovered = 1
					}
					if( x<(edgeSize-1) && y>0 
						&& cellz[x+1][y-1].isDiscovered == -1) {
						cellz[x+1][y-1].isDiscovered = 1
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
				if (cell.isDiscovered == -1) {
					// bomb hover
					icons[x][y] = "o"
				} else if (cell.isMine) {
					if (cell.isDiscovered == 1) {
						// his mine
						icons[x][y] = "h"
					} else {
						// my mine
						icons[x][y] = "m"
					}
				} else {
					icons[x][y] = "n" + cell.nbCellsAdjacent
				}
			}
		}

		return applyLastCoordinates(icons)
	}

	private String[][] applyLastCoordinates(String[][] icons) {
		// set last mine played
		String str;
		if (lastPlayer1 != null) {
			str = icons[(int)lastPlayer1.x][(int)lastPlayer1.y]
			icons[(int)lastPlayer1.x][(int)lastPlayer1.y] += "b"
		}
		if (lastPlayer2 != null) {
			str = icons[(int)lastPlayer2.x][(int)lastPlayer2.y]
			icons[(int)lastPlayer2.x][(int)lastPlayer2.y] += "r"
		}
		return icons
	}
}
