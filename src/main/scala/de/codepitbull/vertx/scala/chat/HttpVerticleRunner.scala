package de.codepitbull.vertx.scala.chat

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.scala.core.{Vertx, VertxOptions}


object HttpVerticleRunner {
  def main(args: Array[String]): Unit = {
    Vertx.clusteredVertx(VertxOptions(), vertxResult => {
      val vertx = vertxResult.result()
      vertx.deployVerticle(nameForVerticle[HttpVerticle])
    })
  }
}
