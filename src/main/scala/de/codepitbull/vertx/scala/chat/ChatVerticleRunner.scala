package de.codepitbull.vertx.scala.chat

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.lang.scala.json.Json
import io.vertx.scala.core.{DeploymentOptions, Vertx, VertxOptions}


object ChatVerticleRunner {
  def main(args: Array[String]): Unit = {
    Vertx.clusteredVertx(VertxOptions(), vertxResult => {
      val vertx = vertxResult.result()
      vertx.deployVerticle(nameForVerticle[ChatVerticle], DeploymentOptions().setConfig(Json.obj(("port", 8084))))
    })
  }
}
