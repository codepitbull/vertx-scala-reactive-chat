package de.codepitbull.vertx.scala.chat

import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.lang.scala.json.Json
import io.vertx.scala.core.{DeploymentOptions, Vertx, VertxOptions}


object ChatVerticleRunner {
  def main(args: Array[String]): Unit = {
    Vertx.clusteredVertx(VertxOptions(), vertxResult => {
      val vertx = vertxResult.result()
      vertx.deployVerticle(nameForVerticle[ChatVerticle], DeploymentOptions().setConfig(Json.obj(("port", 8084))))

      vertx.deployVerticle("service:io.vertx.ext.shell", DeploymentOptions()
        .setConfig(new io.vertx.core.json.JsonObject().put("telnetOptions", new io.vertx.core.json.JsonObject().put("host", "localhost").put("port", 4000))))
    })
  }
}
