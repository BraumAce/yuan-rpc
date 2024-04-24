package com.yuan.yuanrpc.server.tcp;

import com.yuan.yuanrpc.server.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;
import org.checkerframework.checker.units.qual.Length;

/**
 * TCP 服务端实现
 */
public class VertxTcpServer implements HttpServer {

    private byte[] handleRequest(byte[] requestData) {
        // 此处编写处理请求的逻辑，根据 requestData 构造响应参数并返回
        // 示例
        return "Hello, client".getBytes();
    }

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(socket -> {
           // 处理连接
           socket.handler(buffer -> {
               String testMessage = "Hello, server! Hello, server! Hello, server! Hello, server!";
               int messageLength = testMessage.getBytes().length;

               // todo 构造 RecordParser
               RecordParser parser = RecordParser.newFixed(messageLength);
               parser.setOutput(new Handler<Buffer>() {
                   @Override
                   public void handle(Buffer buffer) {
                       String str = new String(Buffer.buffer().getBytes());
                       System.out.println(str);
                       if (testMessage.equals(str)){
                           System.out.println("good");
                       }
                   }
               });
               socket.handler(parser);

//               if (buffer.getBytes().length < messageLength) {
//                   System.out.println("半包, length = " + buffer.getBytes().length);
//                   return;
//               }
//               if (buffer.getBytes().length > messageLength) {
//                   System.out.println("粘包, length = " + buffer.getBytes().length);
//                   return;
//               }
//               String str = new String(buffer.getBytes(0, messageLength));
//               System.out.println(str);
//               if (testMessage.equals(str)){
//                   System.out.println("good");
//               }

//               // 处理接收到的字节数组
//               byte[] requestData = buffer.getBytes();
//
//               // 可以在此处自定义字节数组的处理逻辑
//
//               byte[] responseData = handleRequest(requestData);
//
//               // 发生响应，向连接到服务器的客户端发送数据
//               socket.write(Buffer.buffer(responseData));
           });
        });

        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("TCP server started on port " + port);
            } else {
                System.out.println("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
