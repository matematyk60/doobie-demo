version := "0.5"
enablePlugins(SbtNativePackager)
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
val baseSettings = Seq(
  organization := "com.nm",
  name := "doobie-demo",
  scalaVersion := "2.12.8",
  resolvers ++= Dependencies.additionalResolvers,
  libraryDependencies ++= Dependencies.all,
  parallelExecution in Test := false,
  scalacOptions ++= CompilerOpts.scalacOptions,
  publishArtifact in (Compile, packageDoc) := false,
  publishArtifact in packageDoc := false,
)

val dockerSettings = Seq(
  dockerBaseImage := "java:openjdk-8",
  daemonUser in Docker := "root",
  dockerRepository := Some("matematyk60"),
  dockerExposedPorts := Seq(8080)
)

lazy val root = (project in file("."))
  .settings(baseSettings: _*)
  .settings(dockerSettings: _*)