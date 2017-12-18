package com.github.corney.webcrawler.crawler

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentType, HttpRequest}
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}

import scala.concurrent.{ExecutionContext, Future}


class Downloader(fileService: FileService) {

  def saveToFile(uri: String)(implicit  system: ActorSystem, ec: ExecutionContext): Future[ContentType] = {
    val file = fileService.getFile(uri)
    implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(system))
    for {
      response <- Http().singleRequest(HttpRequest(uri = uri))
      _ <- fileService.save(fileService.getFile(uri), response.entity.dataBytes)
    } yield response.entity.contentType
  }
}
