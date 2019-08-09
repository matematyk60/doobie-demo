package com.nm.demo.domain.user

case class User(id: UserId, username: Username, email: Email)

case class UserId(value: Int)

case class Username(value: String)

case class Email(value: String)

case class UserWithTasks(user: User, tasks: Set[UserTask])

case class UserTask(id: TaskId, name: TaskName)

case class TaskId(value: String)

case class TaskName(value: String)
