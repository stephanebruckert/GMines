class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" (controller:"user", action:"index")
        "/game" (controller:"game", action:"index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
