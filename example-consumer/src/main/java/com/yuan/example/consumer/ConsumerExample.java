package com.yuan.example.consumer;

import com.yuan.example.common.model.User;
import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.proxy.ServiceProxyFactory;

/**
 * 服务消费者示例
 */
public class ConsumerExample {
//    public static void main(String[] args) {
//        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
//        System.out.println(rpc);
//    }

    // 调用公共类测试 Mock 模拟服务代理
    public static void main(String[] args) {

        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("xiaoyuan");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null){
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }

        long number = userService.getNumber();
        System.out.println(number);
    }
}
