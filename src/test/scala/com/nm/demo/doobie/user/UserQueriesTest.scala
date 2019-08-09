package com.nm.demo.doobie.user

import cats.effect.{ContextShift, IO}
import com.nm.demo.domain.user._
import com.nm.demo.infrastructure.doobie.user.{DoobieUserRepository, DoobieUserSchema}
import doobie.util.transactor.Transactor
import org.scalatest.{Matchers, _}

import scala.concurrent.ExecutionContext.Implicits.global

class UserQueriesTest extends FunSuite with Matchers with doobie.scalatest.IOChecker {

  implicit val cs: ContextShift[IO] = IO.contextShift(global)

  val transactor = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost:5432/doobie_demo",
    "username",
    "password"
  )

  val user = User(
    UserId(1),
    Username("john"),
    Email("john@gmail.com")
  )

  val userTask = UserTask(
    TaskId("123213"),
    TaskName("Kolacja")
  )

  test("createTasksSchema") { check(DoobieUserSchema.createTasksSchema) }
  test("createUsersSchema") { check(DoobieUserSchema.createTasksSchema) }

  test("saveUserQuery") { check(DoobieUserRepository.saveUserQuery(user)) }
  test("saveUserTaskQuery") { check(DoobieUserRepository.saveUserTaskQuery(user.id, userTask)) }
  test("getUsersWithTasksQuery") { check(DoobieUserRepository.getUsersWithTasksQuery(user.id)) }
}
