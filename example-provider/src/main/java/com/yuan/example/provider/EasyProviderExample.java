package com.yuan.example.provider;

import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.registry.LocalRegistry;
import com.yuan.yuanrpc.server.HttpServer;
import com.yuan.yuanrpc.server.VertxHttpServer;
import io.vertx.core.Vertx;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args){
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}