class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" (controller:"user", action:"index")
        "/games" (controller:"game", action:"index")
        "/play/$id?(.$format)?" (controller:"game", action:"show")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
