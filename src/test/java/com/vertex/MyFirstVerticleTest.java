package com.vertex;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Danusia on 2015-12-22.
 */
@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {

    private Vertx vertx;
    private int port;

    @Before
    public void setUp(TestContext context) throws IOException {
        vertx = Vertx.vertx();

        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();

        DeploymentOptions deploymentOptions = new DeploymentOptions().
                setConfig(new JsonObject().
                        put("http.port", port));

        vertx.deployVerticle(MyFirstVerticle.class.getName(),deploymentOptions, context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testMyFirstVerticle(TestContext context) {

        final Async async = context.async();

        vertx.createHttpClient().getNow(port, "localhost","/", response -> {
            response.handler(body -> {
                context.assertTrue(body.toString().contains("Hello World"));
                async.complete();
            });
        });
    }
}