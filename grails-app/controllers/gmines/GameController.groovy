package gmines

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GameController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Game.list(params), model:[gameCount: Game.count()]
    }

    def show(Game game) {
        respond game
    }

    def create() {
        respond new Game(params)
    }

    def edit(Game game) {
        respond game
    }

    def play(Game game) { 
        respond game, model:[cells:game.grid.cells.collate(16)]
    }

    @Transactional
    def stroke(Game game) {
        game.stroke(params.int('x'), params.int('y'))
        redirect action:"play", method:"GET", id:game.id
    }

    @Transactional
    def save(Game game) {
        if (game == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (game.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond game.errors, view:'create'
            return
        }

        game.grid = new Grid()
        game.grid.init()
        game.grid.fillGrid()
        game.grid.findAdjacentCell()
        game.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*' { respond game, [status: CREATED] }
        }
    }

    @Transactional
    def update(Game game) {
        if (game == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (game.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond game.errors, view:'edit'
            return
        }

        game.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*'{ respond game, [status: OK] }
        }
    }

    @Transactional
    def delete(Game game) {
        if (game == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        game.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
