package com.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.rpc.Client;
import org.msgpack.rpc.Server;
import org.msgpack.rpc.dispatcher.MethodDispatcher;
import org.msgpack.rpc.loop.EventLoop;
import org.msgpack.rpc.reflect.InvokerBuilder;
import org.msgpack.rpc.reflect.MethodSelector;
import org.msgpack.rpc.reflect.Reflect;
import org.msgpack.rpc.reflect.ReflectionInvokerBuilder;

import static org.junit.Assert.assertEquals;

import com.entity.Dept;
import com.entity.User;
import com.service.IDeptService;
import com.service.IUserService;
import com.service.impl.UserServiceImpl;


public class TestMessagePack {
    
    @Test
    public void testService() {
        long userId = 1l;
        IUserService userServcie = new UserServiceImpl(); 
        User user = userServcie.getUserById(userId);
        assertEquals("name-" + userId, user.getUsername());
        assertEquals("address-" + userId, user.getAddress().get(0).getName());
        assertEquals("dept-" + userId, user.getDept().getName());
    }
    
   @Test
    public void testRPC()  {
        Server server = null;
        Client client = null;
        int port = 19860;
        long userId = 2l;
        MessagePack mp = new MessagePack();
        ServerApp serverApp = new ServerApp();
        
        try {
            server = new Server(EventLoop.defaultEventLoop());
            server.serve(new ReflectionMethodDispatcher(serverApp,
                MethodSelector.selectRpcServerMethod(serverApp.getClass()),mp));
            server.listen(port);
            
            client = new Client("127.0.0.1", port);
            client.setRequestTimeout(100);

            IUserService userService = client.proxy(IUserService.class);
            User user = userService.getUserById(userId);
            assertEquals("name-" + userId, user.getUsername());
            assertEquals("address-" + userId, user.getAddress().get(0).getName());
            assertEquals("dept-" + userId, user.getDept().getName());
            
            IDeptService deptService = client.proxy(IDeptService.class);
            Dept dept = deptService.getDeptById(2l);
            assertEquals("name-" + 2, dept.getName());
            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }finally{
            if(client != null) {
                client.close();
            }
            if(server != null) {
                server.close();
            }
        }
    }
    
     class ReflectionMethodDispatcher extends MethodDispatcher {
        public ReflectionMethodDispatcher(Object target, Method[] methods, 
                MessagePack messagePack) {
            super(new Reflect(messagePack),target, methods);
            InvokerBuilder builder = new ReflectionInvokerBuilder(messagePack);
            for(Method method : methods) {
                super.methodMap.put(method.getName(), builder.buildInvoker(method));
            }
        }
    }
    
}
