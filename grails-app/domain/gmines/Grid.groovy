package gmines

import gmines.*

class Grid {
	int nbBomb = 51
	int edgeSize = 16 * 16

	List cells = []

    static belongsTo = [game:Game]

	static hasMany = [cells:Cell]

    static constraints = {
    	
    }

    void init() {
    	println "should init cells now"

		// Initialization of all cells on the grid
		for(int x = 0; x < edgeSize ; x++) {
			addToCells(new Cell())
		}
    }

    void fillGrid() {
		// Fill
		int nbBombToPut = 0
		int random
		while ( nbBombToPut < nbBomb ){
			// Take a random coordinates
			random = Math.abs(new Random().nextInt() % edgeSize + 1)

			if( !cells[random].isMine ) {
				cells[random].isMine = true
				nbBombToPut++
			}
		}
    }
}
