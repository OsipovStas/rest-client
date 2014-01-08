name := "rest"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
  "com.sun.jersey" % "jersey-client" % "1.16",
  "com.sun.jersey" % "jersey-json" % "1.16")