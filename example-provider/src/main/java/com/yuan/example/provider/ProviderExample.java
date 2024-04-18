package com.yuan.example.provider;

import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.RpcApplication;
import com.yuan.yuanrpc.config.RegistryConfig;
import com.yuan.yuanrpc.config.RpcConfig;
import com.yuan.yuanrpc.model.ServiceMetaInfo;
import com.yuan.yuanrpc.registry.LocalRegistry;
import com.yuan.yuanrpc.registry.Registry;
import com.yuan.yuanrpc.registry.RegistryFactory;
import com.yuan.yuanrpc.server.HttpServer;
import com.yuan.yuanrpc.server.VertxHttpServer;

/**
 * 服务提供者示例
 */
public class ProviderExample {
    public static void main(String[] args){
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        // LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserService.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceAddress(rpcConfig.getServerHost() + ":" + rpcConfig.getServerHost());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
