package com.yuan.example.provider;

import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.RpcApplication;
import com.yuan.yuanrpc.bootstrap.ProviderBootstrap;
import com.yuan.yuanrpc.config.RegistryConfig;
import com.yuan.yuanrpc.config.RpcConfig;
import com.yuan.yuanrpc.model.ServiceMetaInfo;
import com.yuan.yuanrpc.model.ServiceRegisterInfo;
import com.yuan.yuanrpc.registry.EtcdRegistry;
import com.yuan.yuanrpc.registry.LocalRegistry;
import com.yuan.yuanrpc.registry.Registry;
import com.yuan.yuanrpc.registry.RegistryFactory;
import com.yuan.yuanrpc.server.HttpServer;
import com.yuan.yuanrpc.server.VertxHttpServer;
import com.yuan.yuanrpc.server.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务提供者示例
 */
public class ProviderExample {
    public static void main(String[] args){
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo<>(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
