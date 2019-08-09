version := "0.5"


val baseSettings = Seq(
  organization := "com.nm",
  name := "doobie-demo",
  scalaVersion := "2.12.8",
  resolvers ++= Dependencies.additionalResolvers,
  libraryDependencies ++= Dependencies.all,
  parallelExecution in Test := false,
  scalacOptions ++= CompilerOpts.scalacOptions
)

val dockerSettings = Seq(
  dockerBaseImage := "java:openjdk-8",
  daemonUser in Docker := "root",
  dockerRepository := Some("docker.codeheroes.io"),
  dockerExposedPorts := Seq(8080)
)

val protoSettings = Seq(
  PB.targets in Compile := Seq(
    scalapb.gen(flatPackage = true) -> (sourceManaged in Compile).value
  ),
  PB.protoSources in Compile :=
    Seq(
      "transaction_service"
    ).map(baseDirectory.value / "nm-proto" / _)
)

lazy val root = (project in file("."))
  .settings(baseSettings: _*)
  .settings(dockerSettings: _*)
  .settings(protoSettings: _*)
