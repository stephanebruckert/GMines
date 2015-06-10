package gmines

class UserController {

    def index() {

    }

    def join(String nickname) {
        if ( nickname.trim() == '' ) {
            redirect(action:'index')
        } else {
            session.nickname = nickname
            println session.id
 			redirect(controller:"game", action:"index")
        }
    }

}
