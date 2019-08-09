package com.nm.demo.infrastructure.doobie.user

import cats.effect.IO
import com.nm.demo.domain.user.{User, UserId, UserRepository, UserTask, UserWithTasks}
import DoobieUserRepository._
import doobie.util.transactor.Transactor
import doobie.implicits._
import doobie._
class DoobieUserRepository(transactor: Transactor[IO]) extends UserRepository[IO] {

  override def saveUser(user: User): IO[User] =
    saveUserQuery(user).withGeneratedKeys[User]("id", "name", "email").compile.lastOrError.transact(transactor)

  override def saveUserTask(userId: UserId, task: UserTask): IO[UserTask] =
    saveUserTaskQuery(userId, task)
      .withGeneratedKeys[UserTask]("id", "name")
      .compile
      .lastOrError
      .transact(transactor)

  override def getUser(userId: UserId): IO[Option[User]] =
    getUserQuery(userId).option.transact(transactor)

  override def getUserWithTasks(userId: UserId): IO[Option[UserWithTasks]] =
    getUsersWithTasksQuery(userId)
      .to[List]
      .map(_.groupBy {
        case (user, _) => user
      })
      .map(_.map {
        case (user, userWithTasks) =>
          UserWithTasks(user, userWithTasks.map {
            case (_, task) => task
          }.toSet)
      }.headOption)
      .transact(transactor)
}

object DoobieUserRepository {
  def saveUserQuery(user: User): Update0 =
    sql"insert into users (id, name, email) values (${user.id}, ${user.email}, ${user.username})".update

  def saveUserTaskQuery(userId: UserId, task: UserTask): Update0 =
    sql"insert into tasks (id, userId, name) values (${task.id}, $userId, ${task.name})".update

  def getUserQuery(userId: UserId): Query0[User] =
    sql"select id, name, email from users where id = $userId".query[User]

  def getUsersWithTasksQuery(userId: UserId): Query0[(User, UserTask)] =
    sql"select u.id, u.name, u.email, t.id, t.name from users u join tasks t on u.id = t.userId".query[(User, UserTask)]
}
