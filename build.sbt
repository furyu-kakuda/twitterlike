name := """app"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
//  "org.scalatra.scalate" %% "play-scalate" % "0.4.0",
  "com.github.tototoshi" %% "play-scalate" % "0.2.0",
  "org.scalatra.scalate" %% "scalate-core" % "1.8.0",
  "com.google.inject" % "guice" % "4.0",
  "com.googlecode.mapperdao" %% "mapperdao" % "1.0.2",
  "mysql" % "mysql-connector-java" % "8.0.19",
  "org.apache.commons" % "commons-dbcp2" % "2.7.0"
)

unmanagedResourceDirectories in Compile += baseDirectory.value / "target" / "web" / "public" / "main" / "conf" / "build"
