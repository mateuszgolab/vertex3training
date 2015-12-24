package com.vertex;

import io.vertx.core.Vertx;

/**
 * Created by Danusia on 2015-12-22.
 */
public class VertexMain {

    public static void main(String args[]) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyFirstVerticle());
    }
}
