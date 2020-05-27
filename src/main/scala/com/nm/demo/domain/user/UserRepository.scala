package com.nm.demo.domain.user

trait UserRepository[F[_]] {

  def saveUser(user: Person): F[Int]
  def saveUserTask(userId: UserId, task: UserTask): F[UserTask]

  def getUsers: F[List[Person]]
  def getUserWithTasks(userId: UserId): F[Option[UserWithTasks]]

}
