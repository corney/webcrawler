package com.github.corney.webcrawler.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.github.corney.webcrawler.config.ServerConfig
import de.heikoseeberger.akkahttpcirce._
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.{ExecutionContext, Future}

case class RestResponse(a: String, b: Seq[String])

class Server(config: ServerConfig)  extends FailFastCirceSupport {
  val route: Route =
    get {
      val r = RestResponse("Say", Seq("Hello", "To", "AkkaHttp"))
      complete(r.asJson)
    }

  def serve(implicit system: ActorSystem, ec: ExecutionContext): Future[Http.ServerBinding] = {
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    Http().bindAndHandle(route, config.host, config.port)
  }
}
