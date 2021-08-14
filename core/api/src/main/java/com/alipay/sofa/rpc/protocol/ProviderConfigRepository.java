package com.alipay.sofa.rpc.protocol;

import com.alipay.common.tracer.core.utils.AssertUtils;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ProviderConfigRepository {
    public static ArrayList<ProviderConfig> providedServiceList = new ArrayList<>();
    private final ConcurrentHashMap<String, ProviderConfig> providedService = new ConcurrentHashMap<>();

    private static volatile ProviderConfigRepository providerConfigRepository;

    private ProviderConfigRepository() {}

    public static ProviderConfigRepository getProviderConfigRepository() {
        if (providerConfigRepository == null){
            synchronized (ProviderConfigRepository.class){
                if (providerConfigRepository == null){
                    providerConfigRepository = new ProviderConfigRepository();
                }
            }
        }
        return providerConfigRepository;
    }

    /**
     * 发布服务
     *
     * @param providerConfig
     */
    public void addProviderConfig(ProviderConfig providerConfig){
        if(providerConfig != null){
            providedService.putIfAbsent(providerConfig.getInterfaceId(),providerConfig);
//        ConcurrentHashMap Cache = providedService.get(providerConfig.getBootstrap());
            providedServiceList.add(providerConfig);
//        return true;

        }

    }

//    public ProviderConfig<T> publishHelloService(T,T,String name,  int port ){
//        ProviderConfig<T> providerConfig = new ProviderConfig<T>()
//                .setInterfaceId(HelloService.class.getName())
//                .setApplication(new ApplicationConfig().setAppName(name) )
//                .setRef(new HelloServiceImpl())
//                .setServer(new ServerConfig()
//                        .setPort(port)
//                        .setDaemon(false))
//                .setRegister(false);
//        providerConfig.export();
//        return providerConfig;
//    }
//    public ProviderConfig publishEchoService(String name,  int port ){
//        ProviderConfig<EchoService> providerConfig = new ProviderConfig<HelloService>()
//                .setInterfaceId(HelloService.class.getName())
//                .setApplication(new ApplicationConfig().setAppName(name) )
//                .setRef(new HelloServiceImpl())
//                .setServer(new ServerConfig()
//                        .setPort(port)
//                        .setDaemon(false))
//                .setRegister(false);
//        providerConfig.export();
//        return providerConfig;
//    }



   /* public boolean addPc(ProviderConfig pc){
        if (list.add(pc)) {
            return true;
        }
        return false;
    }*/

    /**
     * 获取ProviderConfig
     *
     * @param key 唯一id
     * @return the ProviderConfig
     */
    public ProviderConfig getProviderConfig(String key){
//        ProviderConfig provideConfig = providedService.get(interfaceId);
//        if(provideConfig !=null){
//            return provideConfig;
//        }
//        return null;
        return providedService.get(key);
    }



    /**
     * 获取已发布服务列表
     *
     * @return
     */
    public ArrayList<ProviderConfig> getProvidedServiceList() {
        return providedServiceList;
    }

    /**
     * 获取已发布服务
     *
     * @return 所有ProviderConfig
     */
    public ConcurrentHashMap<String, ProviderConfig> getProvidedServiceMap(){
        return providedService;
    }

    /**
     * 移除 ProviderConfig
     *
     * @param key 唯一id
     */
    public void removeProviderConfig(String key){
        providedService.remove(key);
    }

    /**
     *
     * @param providedServiceList
     */
    public void setProvidedServiceList(ArrayList<ProviderConfig> providedServiceList) {
        this.providedServiceList = providedServiceList;
    }
}

