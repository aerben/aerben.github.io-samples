package me.aerben

import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.junit.VertxUnitRunner
import io.vertx.kotlin.core.json.JsonObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(VertxUnitRunner::class)
class MainTest {
    private lateinit var vertx: Vertx

    @Before
    fun setup(context: TestContext) {
        vertx = Vertx.vertx()
        vertx.deployVerticle(MainVerticle(), context.asyncAssertSuccess())
    }

    @Test
    fun shouldReply(context: TestContext) {
        val async = context.async()
        vertx.eventBus().send<JsonObject>("MainVerticle", JsonObject("message" to "hi"), {
            context.assertTrue(it.succeeded())
            context.assertEquals(it.result().body().getString("message"), "Hello World")
            async.complete()
        })
    }
}