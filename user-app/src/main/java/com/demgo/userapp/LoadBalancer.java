package com.demgo.userapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RibbonClient(name = "chatbook", configuration = RibbonConfiguration.class)
@SpringBootApplication
//@RequestMapping("/")
public class LoadBalancer {

    @Autowired
    private RestTemplate template;

    @GetMapping("/invoke2")
    public String invokeChatbook()
    {
        String url = "http://chatbook/chatbook-app/chat";

        return template.getForObject(url, String.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate template()
    {
        return new RestTemplate();
    }


}
