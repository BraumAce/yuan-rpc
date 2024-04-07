package com.yuan.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.yuan.example.common.model.User;
import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.model.RpcRequest;
import com.yuan.yuanrpc.model.RpcResponse;
import com.yuan.yuanrpc.serializer.JdkSerializer;

import java.io.IOException;

/**
 * 静态代理
 */
public class UserServiceProxy implements UserService {
    public User getUser(User user){
        // 指定序列化器
        JdkSerializer serializer = new JdkSerializer();

        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();

        // 构造 HTTP 请求调用服务提供者
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                        .body(bodyBytes)
                        .execute()){
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
