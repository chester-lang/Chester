package com.example.myModule

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}
import cps.*                  // async, await
import cps.monads.{*, given}  // support for built-in monads (i.e. Future)

object Example:

  def fetchGreeting(): Future[String] =  // dummy async function
    Future successful "Hi"

  def greet() = async[Future] {
    val greeting = await(fetchGreeting()) + ", Bobby!"
    println(greeting)
  }

  def main(args: Array[String]): Unit =
    val f = Await.ready(greet(), 1.seconds)
    f.failed.map { ex => println(ex.getMessage) }