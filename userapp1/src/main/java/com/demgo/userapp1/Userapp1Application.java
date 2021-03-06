package com.demgo.userapp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name = "chatbook", configuration = RibbonConfiguration.class)
@SpringBootApplication
@RequestMapping("/")
public class Userapp1Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Userapp1Application.class, args);
    }



    @Autowired
    private RestTemplate template;

    @GetMapping("/invoke")
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
