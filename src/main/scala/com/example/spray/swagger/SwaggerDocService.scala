package com.example.spray.swagger

import scala.reflect.runtime.{universe=>ru}
import akka.actor.ActorRefFactory
import com.github.swagger.spray.SwaggerHttpService
import com.github.swagger.spray.model.`package`.Info
import com.example.spray.add.AddService
import com.example.spray.hello.HelloService
import io.swagger.models.ExternalDocs
import io.swagger.models.auth.BasicAuthDefinition

class SwaggerDocService(context: ActorRefFactory) extends SwaggerHttpService {
  implicit def actorRefFactory = context
  override val apiTypes = Seq(ru.typeOf[AddService], ru.typeOf[HelloService])
  override val host = "localhost:12345"
  override val info = Info(version = "1.0")
  override val externalDocs = Some(new ExternalDocs("Core Docs", "http://acme.com/docs"))
  override val securitySchemeDefinitions = Map("basicAuth" -> new BasicAuthDefinition())
}