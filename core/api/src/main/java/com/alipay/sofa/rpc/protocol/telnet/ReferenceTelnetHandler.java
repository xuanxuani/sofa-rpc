package com.alipay.sofa.rpc.protocol.telnet;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.protocol.*;

@Extension("reference")
public class ReferenceTelnetHandler implements TelnetHandler{
    @Override
    public String getCommand() {
        return "reference";
    }

    @Override
    public String getDescription() {
        return "show ConsumerConfig!";
    }

    @Override
    public String telnet(String message) {
        StringBuilder result = new StringBuilder(80000000);
        ConsumerConfigRepository consumerConfigRepository = ConsumerConfigRepository.getConsumerConfigRepository();
        String[] syntax = message.split("\\s+");
        if (syntax.length != 1) {
            if (!consumerConfigRepository.getReferredService().containsKey(syntax[1])) {
                result.append("The Service is not provided");
                return result.toString();
            }

        } else {
            result.append("The InterfaceId cannot be null,please type help");
            return result.toString();
        }

        result.append("ConsumerConfig:\r\n");
        ConsumerConfig consumerConfig = consumerConfigRepository.getConsumerConfig(syntax[1]);
        result.append("Protocol:" + TAP + consumerConfig.getProtocol() + "\r\n");
        result.append("DirectUrl:" + TAP + consumerConfig.getDirectUrl() + "\r\n");
        result.append("isGeneric:" + TAP + consumerConfig.isGeneric() + "\r\n");
        result.append("InvokeType:" + TAP + consumerConfig.getInvokeType() + "\r\n");
        result.append("ConnectTimeout:" + TAP + consumerConfig.getConnectTimeout() + "\r\n");
        result.append("DisconnectTimeout:\t\t" + consumerConfig.getDisconnectTimeout() + "\r\n");
        result.append("Cluster:" + TAP + consumerConfig.getCluster() + "\r\n");
        result.append("ConnectionHolder:\t\t" + consumerConfig.getConnectionHolder() + "\r\n");
        result.append("AddressHolder:" + TAP + consumerConfig.getAddressHolder() + "\r\n");
        result.append("LoadBalancer:" + TAP + consumerConfig.getLoadBalancer() + "\r\n");
        result.append("isLazy:\t" + TAP + consumerConfig.isLazy() + "\r\n");
        result.append("isSticky" + TAP + consumerConfig.isSticky() + "\r\n");
        result.append("isCheck:" + TAP + consumerConfig.isCheck() + "\r\n");
        result.append("ConnectionNum:" + TAP + consumerConfig.getConnectionNum() + "\r\n");
        result.append("HeartbeatPeriod:\t\t" + consumerConfig.getHeartbeatPeriod() + "\r\n");
        result.append("ReconnectPeriod:\t\t" + consumerConfig.getReconnectPeriod() + "\r\n");
        result.append("Router:\t" + TAP + consumerConfig.getRouter() + "\r\n");
        result.append("RouterRef:" + TAP + consumerConfig.getRouterRef() + "\r\n");
        result.append("OnReturn:" + TAP + consumerConfig.getOnReturn() + "\r\n");
        result.append("OnConnect:" + TAP + consumerConfig.getOnConnect() + "\r\n");
        result.append("OnAvailable:" + TAP + consumerConfig.getOnAvailable() + "\r\n");
        result.append("Bootstrap:" + TAP + consumerConfig.getBootstrap() + "\r\n");
        result.append("AddressWait:" + TAP + consumerConfig.getAddressWait() + "\r\n");
        result.append("RepeatedReferLimit:\t\t" + consumerConfig.getRepeatedReferLimit() + "\r\n");
        result.append("Timeout:" + TAP + consumerConfig.getTimeout() + "\r\n");
        result.append("Retries:" + TAP + consumerConfig.getRetries() + "\r\n");
        result.append("Concurrents:" + TAP + consumerConfig.getConcurrents() + "\r\n");

        return result.toString();
    }

}
