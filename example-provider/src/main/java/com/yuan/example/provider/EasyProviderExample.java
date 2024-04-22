package com.yuan.example.provider;

import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.RpcApplication;
import com.yuan.yuanrpc.registry.LocalRegistry;
import com.yuan.yuanrpc.server.HttpServer;
import com.yuan.yuanrpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args){
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
