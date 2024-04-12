package com.yuan.yuanrpc.utils.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.yuan.yuanrpc.RpcApplication;
import com.yuan.yuanrpc.model.RpcRequest;
import com.yuan.yuanrpc.model.RpcResponse;
import com.yuan.yuanrpc.serializer.JdkSerializer;
import com.yuan.yuanrpc.serializer.Serializer;
import com.yuan.yuanrpc.serializer.SerializerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务代理 （JDK 动态代理）
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        // 指定序列化器
        //Serializer serializer = new JdkSerializer();
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        // 构造 HTTP 请求调用服务提供者
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发请求
            // todo 此处地址被硬解码了，需要使用注册中心和服务发现机制解决
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();

                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
