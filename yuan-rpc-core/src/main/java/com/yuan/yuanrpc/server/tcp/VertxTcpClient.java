package com.yuan.yuanrpc.server.tcp;

import io.vertx.core.Vertx;

/**
 * TCP 客户端实现
 */
public class VertxTcpClient {

    public void start() {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        vertx.createNetClient().connect(8888, "localhost", result -> {
            if (result.succeeded()) {
                System.out.println("已连接 TCP 服务端");
                io.vertx.core.net.NetSocket socket = result.result();

                // 发送数据
                for (int i = 0; i < 1000; i++){
                    socket.write("Hello, server! Hello, server! hello, server!");
                }

                // 接收响应
                socket.handler(buffer -> {
                    System.out.println("Received response from server: " + buffer.toString());
                });
            } else {
                System.err.println("Failed to connect to TCP server");
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpClient().start();
    }
}
