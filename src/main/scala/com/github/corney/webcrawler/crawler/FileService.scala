package com.github.corney.webcrawler.crawler

import java.io.File
import java.net.URLEncoder

import akka.stream.Materializer
import akka.stream.scaladsl.Source
import akka.util.ByteString

import scala.concurrent.{ExecutionContext, Future}

trait FileService {

  def getFile(uri: String): File

  def save(file: File, source: Source[ByteString, Any])
          (implicit materializer: Materializer, ec: ExecutionContext): Future[_]
}
