import sbt._

object Dependencies {
  object V {
    val Akka = "2.5.6"
    val AkkaHttp = "10.0.11"
    val Circe = "0.8.0"
  }

  val akkaActor      = "com.typesafe.akka"     %% "akka-actor"        % V.Akka
  val akkaStream     = "com.typesafe.akka"     %% "akka-stream"       % V.Akka
  val akkaSlf4j      = "com.typesafe.akka"     %% "akka-slf4j"        % V.Akka
  val akkaHttp       = "com.typesafe.akka"     %% "akka-http"         % V.AkkaHttp
  val circeCore      = "io.circe"              %% "circe-core"        % V.Circe
  val circeGeneric   = "io.circe"              %% "circe-generic"     % V.Circe
  val circeParser    = "io.circe"              %% "circe-parser"      % V.Circe
  val akkaJson       = "de.heikoseeberger"     %% "akka-http-circe"   % "1.18.0"
  val scalaTic       = "org.scalactic"         %% "scalactic"         % "3.0.4"
  val scalaTest      = "org.scalatest"         %% "scalatest"         % "3.0.4" % "test"


  val dependencies = Seq(
    akkaActor, akkaHttp, akkaSlf4j, akkaStream, akkaJson,
    circeCore, circeGeneric, circeParser,
    scalaTic, scalaTest
  )
}
