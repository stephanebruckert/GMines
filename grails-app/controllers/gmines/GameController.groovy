package gmines

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GameController {

    static allowedMethods = [save: "POST", stroke: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Game.list(params).reverse(), model:[gameCount: Game.count()]
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

    def stroke() {
        Game game = Game.get(request.JSON.gameId)
        if (request.JSON.user == null) {
            request.JSON.user = (session.id).substring(0, 5)
        }
        if (game.player1 == request.JSON.user) {
            game.player1lastActivity = new Date()
        } else if (game.player2 == null) {
            game.player2 = request.JSON.user
            game.player2lastActivity = new Date()
            game.actionCount++
        } else if (game.player2 == request.JSON.user) {
            game.player2lastActivity = new Date()
        }
        game.stroke(request.JSON.num, request.JSON.user)
        game.save(flush:true)
        
        render(contentType: "application/json") {
            p1 = game.player1
            p2 = game.player2
            who = game.player2shouldPlay
            p1c = game.player1minesFound
            p2c = game.player2minesFound
            winner = game.winner
            grid = game.grid.getDisplayableGrid()
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

    def list() {
        render(contentType: "application/json") {
            games = Game.list()
        }
    }

    def info(Game game) {
        render(contentType: "application/json") {
            p1 = game.player1
            p2 = game.player2
            who = game.player2shouldPlay
            p1c = game.player1minesFound
            p2c = game.player2minesFound
            winner = game.winner
            grid = game.grid.getDisplayableGrid()
        }
    }

    def save() {
        Game game = new Game()
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

        game.player1 = request.JSON.user
        game.grid = new Grid()
        game.grid.init()
        game.grid.fillGrid()
        game.grid.findAdjacentCell()
        game.save flush:true

        render(contentType: "application/json") {
            game = game
        }
    }
}