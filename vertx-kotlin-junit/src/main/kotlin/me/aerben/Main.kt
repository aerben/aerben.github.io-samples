package me.aerben

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.core.logging.LoggerFactory.getLogger
import io.vertx.kotlin.core.json.JsonObject


fun main(args: Array<String>) {
    Vertx.vertx().deployVerticle(MainVerticle(), {
        getLogger("main").info("Started!")
    })
}


class MainVerticle : AbstractVerticle() {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun start() {
        vertx.eventBus().consumer<JsonObject>("MainVerticle", {
            logger.info(it.body())
            it.reply(JsonObject("message" to "Hello World"))
        })
    }

}
