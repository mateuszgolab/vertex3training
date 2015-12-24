package com.vertex;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by Danusia on 2015-12-22.
 */
public class MyFirstVerticle extends AbstractVerticle {

    public void start(Future<Void> future) {

        vertx.createHttpServer().
                requestHandler(r -> {
                    r.response().end("<h1>Hello World</h1>");
                }).
                listen(config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                future.complete();
                            } else {
                                future.fail(result.cause());
                            }
                        });
    }
}
