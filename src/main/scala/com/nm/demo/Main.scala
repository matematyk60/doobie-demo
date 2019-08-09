package com.nm.demo

import cats.effect.{ExitCode, IO, IOApp, Resource}
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts

object Main extends IOApp {

  val transactorResource: Resource[IO, HikariTransactor[IO]] = for {
    connectionEc  <- ExecutionContexts.fixedThreadPool[IO](10)
    transactionEc <- ExecutionContexts.cachedThreadPool[IO]
    transactor <- HikariTransactor.newHikariTransactor[IO](
      "org.postgresql.Driver",
      "jdbc:postgresql://localhost:5432/doobie_demo",
      "username",
      "password",
      connectionEc,
      transactionEc
    )

  } yield transactor

  override def run(args: List[String]): IO[ExitCode] = {
    transactorResource.use { transactor =>
      val application = new Application(transactor)
      application.run.map(_ => ExitCode.Success)
    }
  }

}
