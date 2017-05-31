package de.codepitbull.vertx.scala.chat

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router

import scala.concurrent.Future
import scala.util.{Failure, Success}

class HttpVerticle extends ScalaVerticle {


  override def startFuture(): Future[_] = {

    val sender = vertx.eventBus().sender[String]("testAddress")

    val router = Router.router(vertx)
    router
      .get("/hello")
        .handler(_.response().end("world"))

    router.get("/hello_again")
        .handler(req => {
          sender
            .sendFuture[String]("World")
            .onComplete{
              case Success(reply) => req.response().end(s"${reply.body()} ${reply.headers().get("identity")}")
              case Failure(t) => req.response().end("No one is there :(")
            }
        })

    vertx
      .createHttpServer()
      .requestHandler(router.accept _)
      .listenFuture(8666, "0.0.0.0")
  }
}
