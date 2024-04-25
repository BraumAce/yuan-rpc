package com.yuan.examplespringbootconsumer;

import com.yuan.example.common.model.User;
import com.yuan.example.common.service.UserService;
import com.yuan.yuanrpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * 示例服务实现类
 */
@Service
public class ExampleServiceImpl {

    /**
     * 使用 RPC 框架注入
     */
    @RpcReference
    private UserService userService;

    /**
     * 测试方法
     */
    public void test() {
        User user = new User();
        user.setName("Yuan");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}
