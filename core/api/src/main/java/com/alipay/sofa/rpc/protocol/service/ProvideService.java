package com.alipay.sofa.rpc.protocol.service;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.protocol.EchoService;
import com.alipay.sofa.rpc.protocol.EchoServiceImpl;
import com.alipay.sofa.rpc.protocol.HelloService;
import com.alipay.sofa.rpc.protocol.HelloServiceImpl;

public class ProvideService {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig().setAppName("test-server");

        ServerConfig serverConfig = new ServerConfig()
                .setPort(22000)
                .setDaemon(false);

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())
                .setApplication(application)
                .setRef(new HelloServiceImpl())
                .setServer(serverConfig)
                .setRegister(false);

//        ProviderConfig<EchoService> providerConfig2 = new ProviderConfig<EchoService>()
//                .setInterfaceId(EchoService.class.getName())
//                .setApplication(application)
//                .setRef(new EchoServiceImpl())
//                .setServer(serverConfig)
//                .setRegister(false);

        providerConfig.export();
//        providerConfig2.export();

//        ProviderConfigRepository providerConfigRepository = new ProviderConfigRepository();
//        ProviderConfig providerConfig = providerConfigRepository.publishHelloService("test-server", 22000);
//
//        list.add(providerConfig);

//        LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);
    }
}
