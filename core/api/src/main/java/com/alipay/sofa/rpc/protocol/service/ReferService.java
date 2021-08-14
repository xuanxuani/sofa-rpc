package com.alipay.sofa.rpc.protocol.service;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.protocol.EchoService;
import com.alipay.sofa.rpc.protocol.EchoServiceImpl;
import com.alipay.sofa.rpc.protocol.HelloService;
import com.alipay.sofa.rpc.protocol.HelloServiceImpl;
public class ReferService {
    public static void main(String[] args) throws InterruptedException {
        ApplicationConfig application = new ApplicationConfig().setAppName("test-client");

        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setApplication(application)
                .setInterfaceId(HelloService.class.getName())
                .setDirectUrl("bolt://127.0.0.1:22000")
                .setRegister(false)
                .setTimeout(3000);
        HelloService helloService = consumerConfig.refer();

//        ConsumerConfig<EchoService> consumerConfig2 = new ConsumerConfig<EchoService>()
//                .setInterfaceId(EchoService.class.getName())
//                .setApplication(application)
//                .setDirectUrl("bolt://127.0.0.1:22000")
//                .setRegister(false)
//                .setTimeout(3000);
//        EchoService echoService = consumerConfig2.refer();
//
//        consumerConfig2.unRefer();

//        LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);

//        while (true) {
//            try {
//                String s = helloService.sayHello("xxx", 22);
//                LOGGER.warn("{}", s);
//            } catch (Exception e) {
//                LOGGER.error(e.getMessage(), e);
//            }
//            try {
//                Thread.sleep(2000);
//            } catch (Exception e) {
//            }
//        }
    }
}
