package com.yuan.yuanrpc.registry.server;

import io.vertx.core.Vertx;

/**
 * 基于 Vert.x 的 web 服务器
 */
public class VertxHttpServer implements HttpServer{
    /**
     * 启动服务器
     * @param port
     */
    public void doStart(int port){
        // 1.创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 2.创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 3.监听端口并处理请求
        // 绑定请求处理器
        server.requestHandler(new HttpServerHandler());
//        server.requestHandler(request -> {
//            // 处理 HTTP 请求
//            System.out.println("Received request: " + request.method() + " " + request.uri());
//
//            // 发送 HTTP 响应
//            request.response()
//                    .putHeader("content-type", "text/plain")
//                    .end("hello from Vert.x HTTP server!");
//        });
        
        // 4.启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
           if (result.succeeded()) {
               System.out.println("Server is now listening on port: " + port);
           } else {
               System.out.println("Failed to start server: " + result.cause());
           }
        });
    }
}
