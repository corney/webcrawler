package com.github.corney.webcrawer.file

import java.io.File
import java.nio.file.Files

import com.github.corney.webcrawler.crawler.{FileService, FileServiceImpl}
import org.scalatest._

class FileServiceSpec extends FlatSpec with Matchers {

  import FileServiceSpec._

  private val dir = Files.createTempDirectory("spec").toFile
  dir.deleteOnExit()

  it should "Throws exception on non-existed directory" in {
    val emptyFile = new File(getClass.getClassLoader.getResource("empty.file").getFile)
    an [AssertionError] should be thrownBy new FileServiceImpl(new File("/non/existent/path"))
    an [AssertionError] should be thrownBy new FileServiceImpl(emptyFile)

    new FileServiceImpl(dir) shouldBe an[FileService]

  }

  it should "Get correct name" in {
    val fileService = new FileServiceImpl(dir)

    fileService.getFileName(uri) shouldBe "search%3Fq%3Dweb%2Bcrawler%26sourceid%3Dchrome"
  }

}
object FileServiceSpec {
  val uri = "https://www.google.ru/search?q=web+crawler&sourceid=chrome"
}