import Dependencies._

val circeVersion = "0.9.1"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),

    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      "Medidata-Release" at "https://mdsol.jfrog.io/mdsol/all-repos-release",
    ),

    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    name := "archon_scala_client",
    libraryDependencies += scalaTest % Test,
    //libraryDependencies += "com.mdsol.clients" % "eureka-client" % "3.0.2",
    libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.0.0-M1",
    libraryDependencies += "com.google.inject" % "guice" % "4.2.0",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser",
      "io.circe" %% "circe-generic-extras",
      "io.circe" %% "circe-java8"
    ).map(_ % circeVersion)
  )
