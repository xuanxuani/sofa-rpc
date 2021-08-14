package com.alipay.sofa.rpc.protocol;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;

import java.util.ArrayList;

public class TelnetTest {
    public static ArrayList<ProviderConfig> providerConfigs;
    public static ArrayList<ConsumerConfig> consumerConfigs;

    public static void main(String[] args) {
        //发布服务，publishHelloService，发布Hello服务的方法
        ProviderConfigRepository providerConfigRepository = ProviderConfigRepository.getProviderConfigRepository();

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

        ProviderConfig<EchoService> providerConfig2 = new ProviderConfig<EchoService>()
                .setInterfaceId(EchoService.class.getName())
                .setApplication(application)
                .setRef(new EchoServiceImpl())
                .setServer(serverConfig)
                .setRegister(false);

        providerConfig.export();
        providerConfig2.export();


        providerConfigRepository.addProviderConfig(providerConfig);
        providerConfigRepository.addProviderConfig(providerConfig2);
        providerConfigs = providerConfigRepository.getProvidedServiceList();


        //引用服务
        ApplicationConfig application2 = new ApplicationConfig().setAppName("test-client");

        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setApplication(application2)
                .setInterfaceId(HelloService.class.getName())
                .setDirectUrl("bolt://127.0.0.1:22000")
                .setRegister(false)
                .setTimeout(3000);
        HelloService helloService = consumerConfig.refer();

        ConsumerConfigRepository consumerConfigRepository = ConsumerConfigRepository.getConsumerConfigRepository();
        consumerConfigRepository.addConsumerConfig(consumerConfig);
        consumerConfigs = consumerConfigRepository.getReferredServiceList();


        NettyTelnetServer nettyTelnetServer = new NettyTelnetServer(1234);
        try {
            nettyTelnetServer.open();

        } catch (InterruptedException e) {
            nettyTelnetServer.close();
        }

    }

}