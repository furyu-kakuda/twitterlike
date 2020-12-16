name := """app"""
organization := "com.example"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  "com.github.tototoshi" %% "play-scalate" % "0.2.0",
  "org.scalatra.scalate" %% "scalate-core" % "1.8.0",
  "com.google.inject" % "guice" % "4.0",
  "com.googlecode.mapperdao" %% "mapperdao" % "1.0.2",
  "mysql" % "mysql-connector-java" % "8.0.19",
  "org.apache.commons" % "commons-dbcp2" % "2.7.0"
)

unmanagedResourceDirectories in Compile += baseDirectory.value / "public" / "conf" / "build"

val frontBaseDir = settingKey[File]("frontEnd base directory")

lazy val root = (project in file(".")).
  settings(
    frontBaseDir := new File(baseDirectory.value.getAbsolutePath + "/front")
  )
  .enablePlugins(PlayScala)

val frontBuild = taskKey[Int]("build frontend")
val frontLintAndCodeFormat = taskKey[Int]("front lint and code format")

def npmRun(command: String, frontBaseDir: File): Int = {
  Process(Seq("npm", "run", command), frontBaseDir) !
}

frontBuild := {
  val s: TaskStreams = streams.value
  s.log.info("=======start frontend build=====")
  npmRun("build", frontBaseDir.value)
}

frontLintAndCodeFormat := {
  val s: TaskStreams = streams.value
  s.log.info("=======start frontend lint and code format=====")
  npmRun("lint", frontBaseDir.value)
}

commands += Command.command("runWithFrontBuild") { state =>
  "clean" ::
    "frontBuild" ::
    "run" ::
    state
}


