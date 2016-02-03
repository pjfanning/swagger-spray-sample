package com.example.spray

import spray.http.HttpHeaders
import spray.routing.SimpleRoutingApp

/**
 * Provides the web server (spray-can) for the REST api in ``Api``, using the actor system
 * defined in ``Core``.
 *
 * You may sometimes wish to construct separate ``ActorSystem`` for the web server machinery.
 * However, for this simple application, we shall use the same ``ActorSystem`` for the
 * entire application.
 *
 * Benefits of separate ``ActorSystem`` include the ability to use completely different
 * configuration, especially when it comes to the threading model.
 */
trait Web extends SimpleRoutingApp {
  this: Api with CoreActors with Core =>

  val AccessControlAllowAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Origin", "*"
  )
  val AccessControlAllowHeadersAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"
  )
  
  //this is not ideal because this disables CORS checking (and is required by the Swagger UI)
  startServer(interface = "0.0.0.0", port = 12345) {
    respondWithHeaders(AccessControlAllowAll, AccessControlAllowHeadersAll) {
      routes
    }
  }
}