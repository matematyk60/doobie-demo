package com.nm.demo.infrastructure.doobie.user

import cats.effect.IO
import doobie.implicits._
import doobie.util.fragment
import doobie.util.transactor.Transactor

object DoobieUserSchema {

//  val createUserSchema: doobie.Update0 = sql"""
//       CREATE TABLE IF NOT EXISTS users (
//         id INTEGER PRIMARY KEY,
//         name VARCHAR NOT NULL,
//         email VARCHAR NOT NULL
//       )
//         """.update
//
//  val createTasksSchema: doobie.Update0 = sql"""
//       CREATE TABLE IF NOT EXISTS tasks (
//         id VARCHAR PRIMARY KEY,
//         userId INTEGER NOT NULL,
//         name VARCHAR NOT NULL,
//         FOREIGN KEY (userId) REFERENCES users (id)
//       )
//    """.update
//
//  def createUserSchemaIO(transactor: Transactor[IO]): IO[Unit] =
//    (for {
//      _ <- createUserSchema.run
//      _ <- createTasksSchema.run
//    } yield ()).transact(transactor)
}
