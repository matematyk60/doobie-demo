import sbt._

object Dependencies {

  val codeheroesCommonsVersion = "0.96"

  val scalaTestVersion  = "3.0.8"
  val scalaMockVersion  = "4.3.0"
  val simulacrumVersion = "0.19.0"

  val doobieVersion = "0.7.0"
  val http4sVersion = "0.20.0"

  private val http4sDependencies = Seq(
    "org.http4s" %% "http4s-blaze-server" % http4sVersion,
    "org.http4s" %% "http4s-dsl"          % http4sVersion
  )

  private val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "org.scalamock" %% "scalamock" % scalaMockVersion % Test
  )

  private val miscDependencies = Seq(
    "com.github.mpilquist" %% "simulacrum" % simulacrumVersion
  )

  private val doobieDependencies = Seq(
    "org.tpolecat" %% "doobie-core" % doobieVersion,
    "org.tpolecat" %% "doobie-hikari"    % doobieVersion,
    "org.tpolecat" %% "doobie-postgres"  % doobieVersion,
    "org.tpolecat" %% "doobie-scalatest" % doobieVersion % Test
  )

  val all: Seq[ModuleID] = Seq(
    testDependencies,
    miscDependencies,
    http4sDependencies,
    doobieDependencies
  ).flatten

  val additionalResolvers: Seq[Resolver] = Seq(
    Resolver.jcenterRepo,
    Resolver.mavenCentral,
    Resolver.bintrayRepo("codeheroes", "maven"),
    "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
  )

}
