// Versions

val commonsIoVersion = "2.6"
val logbackVersion = "1.2.3"
val scalaLoggingVersion = "3.9.2"
val scalaTestVersion = "3.0.8"

// Common settings

lazy val commonSettings = Seq(
  organization := "com.myodov",
  maintainer := "Alex Myodov <amyodov@gmail.com>",
  version := "0.1.0",
  scalaVersion := "2.13.0",
  libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
  )
  //  fork in run := true
)

// Launcher

lazy val launcher = (project in file("knightwalk"))
  .settings(commonSettings: _*)
  .settings(
    name := "knightwalk",
    resolvers += Resolver.bintrayRepo("serioussam", "oss"),
    libraryDependencies ++= Seq(
      // Default logging output
      //      "ch.qos.logback" % "logback-classic" % logbackVersion,
    ),
    // Define the launch option for sbt-native-packager
    mainClass in Compile := Some("com.myodov.knightwalk.Launcher"),
  ).enablePlugins(JavaAppPackaging)
