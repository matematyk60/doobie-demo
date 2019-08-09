package com.nm.demo

import cats.effect.IO
import com.nm.demo.domain.user.{Email, TaskId, TaskName, User, UserId, UserTask, Username}
import com.nm.demo.infrastructure.doobie.user.{DoobieUserRepository, DoobieUserSchema}
import doobie.util.transactor.Transactor

import scala.language.postfixOps

class Application(transactor: Transactor[IO]) {

  val userRepository = new DoobieUserRepository(transactor)

  def run: IO[Unit] =
    for {
      _ <- DoobieUserSchema.createUserSchemaIO(transactor)

      userToSave = User(UserId(6), Username("Krzysztof"), Email("krzys@gmail.com"))
      savedUser <- userRepository.saveUser(userToSave)
      _         <- IO(println(s"Successfully inserted user $savedUser in db"))

      taskToSave = UserTask(TaskId("1231234"), TaskName("elo"))
      savedTask <- userRepository.saveUserTask(userToSave.id, taskToSave)
      _         <- IO(println(s"Successfully inserted user task $savedTask in db"))

      fetchedUser <- userRepository.getUser(userToSave.id)
      _           <- IO(println(s"Successfully fetched user $fetchedUser from db"))

      fetchedUserWithTask <- userRepository.getUserWithTasks(userToSave.id)
      _                   <- IO(println(s"Successfully fetched user with tasks $fetchedUserWithTask"))

    } yield ()
}
