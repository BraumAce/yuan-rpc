package com.yuan.example.consumer;

import com.yuan.example.common.model.User;
import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.proxy.ServiceProxyFactory;

/**
 * 简易服务消费者示例
 */
public class EasyConsumerExample {
    public static void main(String[] args){
        // 通过静态代理获取 UserService 实例对象
        //UserService userService = new UserServiceProxy();

        // 通过动态代理获取 UserService 实例对象
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yuan");
        //调用
        User newUser = userService.getUser(user);
        if (newUser != null){
            System.out.println(newUser.getName());
        }
        else {
            System.out.println("user == null");
        }
    }
}
