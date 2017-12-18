package com.github.corney.webcrawler

import akka.actor.ActorSystem
import com.github.corney.webcrawler.config.ServerConfig
import com.github.corney.webcrawler.http.Server

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main extends App {

  val config = ServerConfig("localhost", 8080)
  val server = new Server(config)

  implicit val system: ActorSystem = ActorSystem("webcrawler")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val bindingFuture = server.serve

  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate())



}
