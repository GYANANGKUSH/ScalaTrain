//import java.lang.System.console

organization := "com.typesafe.training"

name := "train"

version := "0.1"

scalaVersion := "2.11.7"

// The Typesafe repository
//resolvers += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe.play" %% "play-json" % "2.3.10"
)
//"org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3"

initialCommands in console := "import com.typesafe.training.scalatrain._; import scala.util.control._"

initialCommands in (Test, console) := (initialCommands in console).value + ",TestData._"