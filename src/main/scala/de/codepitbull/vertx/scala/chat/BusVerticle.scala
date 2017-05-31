package de.codepitbull.vertx.scala.chat

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.DeliveryOptions

import scala.concurrent.Future

class BusVerticle extends ScalaVerticle {

  override def startFuture(): Future[_] = {

    val opts = DeliveryOptions().addHeader("identity", hashCode().toString)

    vertx
      .eventBus()
      .consumer[String]("testAddress")
      .handler(msg => {
        msg.reply(s"Hello ${msg.body()}", opts)
      })
      .completionFuture()
  }
}
