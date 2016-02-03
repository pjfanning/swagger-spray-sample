package com.example.spray

import scala.util.control.NonFatal
import akka.actor.{Actor, ActorLogging, Props}
import spray.routing.{ExceptionHandler, HttpService, RejectionHandler, Route, RouteConcatenation, RoutingSettings}
import spray.util.LoggingContext
import spray.http.StatusCodes.InternalServerError
import com.example.spray.add.AddService
import com.example.spray.hello.HelloService
import com.example.spray.swagger.SwaggerDocService

/**
 * The REST API layer. It exposes the REST services, but does not provide any
 * web server interface.<br/>
 * Notice that it requires to be mixed in with ``core.CoreActors``, which provides access
 * to the top-level actors that make up the system.
 */
trait Api extends RouteConcatenation {
  this: CoreActors with Core =>

  private implicit val _ = system.dispatcher

  val routes =
    new AddService(add).route ~
    new HelloService(hello).route ~
    new SwaggerDocService(system).routes

  val rootService = system.actorOf(Props(new RoutedHttpService(routes)))

}

/**
 * Allows you to construct Spray ``HttpService`` from a concatenation of routes; and wires in the error handler.
 * It also logs all internal server errors using ``SprayActorLogging``.
 *
 * @param route the (concatenated) route
 */
class RoutedHttpService(route: Route) extends Actor with HttpService with ActorLogging {

  implicit def actorRefFactory = context

  implicit val handler = ExceptionHandler {
    case NonFatal(ErrorResponseException(statusCode, entity)) => ctx =>
      ctx.complete((statusCode, entity))

    case NonFatal(e) => ctx => {
      log.error(e, InternalServerError.defaultMessage)
      ctx.complete(InternalServerError)
    }
  }


  def receive: Receive =
    runRoute(route)(handler, RejectionHandler.Default, context, RoutingSettings.default, LoggingContext.fromActorRefFactory)

}