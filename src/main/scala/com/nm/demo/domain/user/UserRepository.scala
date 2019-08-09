package com.nm.demo.domain.user

trait UserRepository[F[_]] {

  def saveUser(user: User): F[User]
  def saveUserTask(userId: UserId, task: UserTask): F[UserTask]

  def getUser(userId: UserId): F[Option[User]]
  def getUserWithTasks(userId: UserId): F[Option[UserWithTasks]]

}
