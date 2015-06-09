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
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*' { respond game, model:[cells:game.grid.getDisplayableGrid()] }
        }
    }

    def refresh(Game game) {
        render game.actionCount
    }

    def create() {
        respond new Game(params)
    }

    @Transactional
    def stroke(Game game) {
         if (game.player1 == session.nickname) {
            game.player1lastActivity = new Date()
        } else if (game.player2 == null) {
            game.player2 = session.nickname
            game.player2lastActivity = new Date()
            game.actionCount++
        } else if (game.player2 == session.nickname) {
            game.player2lastActivity = new Date()
        }
        game.stroke(params.int('x'), params.int('y'), session.nickname)
        game.save(flush:true)
        redirect action:"show", method:"GET", id:game.id
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

        game.player1 = session.nickname
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
