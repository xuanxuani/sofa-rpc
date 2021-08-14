package com.alipay.sofa.rpc.protocol;

import com.alipay.sofa.rpc.config.ConsumerConfig;


import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ConsumerConfigRepository {
    private static volatile ConsumerConfigRepository consumerConfigRepository;
    public static ArrayList<ConsumerConfig> referredServiceList = new ArrayList<>();
    public static ConcurrentHashMap<String, ConsumerConfig> referredService = new ConcurrentHashMap<>();

    private ConsumerConfigRepository() {
    }

    public static ConsumerConfigRepository getConsumerConfigRepository() {
        if (consumerConfigRepository == null) {
            synchronized (ConsumerConfigRepository.class) {
                if (consumerConfigRepository == null) {
                    consumerConfigRepository = new ConsumerConfigRepository();
                }
            }
        }
        return consumerConfigRepository;
    }

    /**
     * 引用服务
     *
     * @param consumerConfig
     */
    public void addConsumerConfig(ConsumerConfig consumerConfig) {
        if (consumerConfig != null) {
            referredService.putIfAbsent(consumerConfig.getInterfaceId(), consumerConfig);
            referredServiceList.add(consumerConfig);
        }
    }


    /**
     * 获取引用服务
     *
     * @param key
     * @return
     */
    public ConsumerConfig getConsumerConfig(String key) {
        return referredService.get(key);
    }

    /**
     * 移除引用服务
     *
     * @param key
     */
    public void removeConsumerConfig(String key) {
        referredService.remove(key);
    }

    /**
     * 获取引用服务列表
     *
     * @return
     */
    public ArrayList<ConsumerConfig> getReferredServiceList() {
        return referredServiceList;
    }

    /**
     * 获取引用服务列表
     *
     * @return
     */
    public ConcurrentHashMap<String, ConsumerConfig> getReferredService() {
        return referredService;
    }

    /**
     * @param referredServiceList
     */
    public void setReferredServiceList(ArrayList<ConsumerConfig> referredServiceList) {
        ConsumerConfigRepository.referredServiceList = referredServiceList;
    }
}
