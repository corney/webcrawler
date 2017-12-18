package com.github.corney.webcrawler.crawler

import java.io.{File, FileWriter}
import java.net.URLEncoder

import akka.Done
import akka.stream.Materializer
import akka.stream.scaladsl.Source
import akka.util.ByteString

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

class FileServiceImpl(baseDir: File) extends FileService {
  assert(baseDir.exists() && baseDir.isDirectory && baseDir.canWrite)

  def getFileName(uri: String): String = {
    URLEncoder.encode(uri.split("/").last, "utf-8")
  }

  override def getFile(uri: String): File = new File(baseDir, getFileName(uri))

  override def save(file: File, source: Source[ByteString, Any])
                   (implicit materializer: Materializer, ec: ExecutionContext): Future[Done] =
  {
    val writer = new FileWriter(file)

    {
      for {
        done <- source.runForeach(f => writer.write(f.utf8String))
      } yield done
    }.recoverWith {
      case NonFatal(e) =>
        writer.close()
        Future.failed(e)
    }
  }
}
