package com.nm.demo

import cats.effect.{IO, Timer}
import com.nm.demo.domain.user.{Email, Person, TaskId, TaskName, User, UserId, UserTask, Username}
import com.nm.demo.infrastructure.doobie.user.{DoobieUserRepository, DoobieUserSchema}
import doobie.util.transactor.Transactor

import scala.language.postfixOps
import scala.concurrent.duration._
class Application(transactor: Transactor[IO])(implicit timer: Timer[IO]) {

  val userRepository = new DoobieUserRepository(transactor)

  def run: IO[Unit] =
    for {
//      _ <- DoobieUserSchema.createUserSchemaIO(transactor)

//      userToSave = User(UserId(6), Username("Krzysztof"), Email("krzys@gmail.com"))
//      savedUser <- userRepository.saveUser(userToSave)
//      _         <- IO(println(s"Successfully inserted user $savedUser in db"))

//      taskToSave = UserTask(TaskId("1231234"), TaskName("elo"))
//      savedTask <- userRepository.saveUserTask(userToSave.id, taskToSave)
//      _         <- IO(println(s"Successfully inserted user task $savedTask in db"))

//      fetchedUser <- userRepository.getUser(userToSave.id)
//      _           <- IO(println(s"Successfully fetched user $fetchedUser from db"))
      _      <- userRepository.saveUser(Person(1, "Dawid", "Godek", "Stepija", "Stepinka"))
      users1 <- userRepository.getUsers
      _      <- IO(println(s"Successfully fetched users [$users1]"))
      _      <- IO.sleep(9 seconds)

      _      <- userRepository.saveUser(Person(2, "Dawid", "Godek", "Stepija", "Stepinka"))
      users2 <- userRepository.getUsers
      _      <- IO(println(s"Successfully fetched users [$users2]"))
      _      <- IO.sleep(9 seconds)

      _      <- userRepository.saveUser(Person(3, "Dawid", "Godek", "Stepija", "Stepinka"))
      users3 <- userRepository.getUsers
      _      <- IO(println(s"Successfully fetched users [$users3]"))
      _      <- IO.sleep(9 seconds)

      _      <- userRepository.saveUser(Person(4, "Dawid", "Godek", "Stepija", "Stepinka"))
      users4 <- userRepository.getUsers
      _      <- IO(println(s"Successfully fetched users [$users4]"))
      _      <- IO.sleep(9 seconds)

      _      <- userRepository.saveUser(Person(5, "Dawid", "Godek", "Stepija", "Stepinka"))
      users5 <- userRepository.getUsers
      _      <- IO(println(s"Successfully fetched users [$users5]"))
      _      <- IO.sleep(9 seconds)

      _      <- userRepository.saveUser(Person(6, "Dawid", "Godek", "Stepija", "Stepinka"))
      users6 <- userRepository.getUsers
      _      <- IO(println(s"Successfully fetched users [$users6]"))
      _      <- IO.sleep(9 seconds)

      _      <- userRepository.saveUser(Person(7, "Dawid", "Godek", "Stepija", "Stepinka"))
      users7 <- userRepository.getUsers
      _      <- IO(println(s"Successfully fetched users [$users7]"))
      _      <- IO.sleep(9 seconds)

    } yield ()
}
