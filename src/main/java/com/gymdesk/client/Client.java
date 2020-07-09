package com.gymdesk.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "client", url = "https://placekitten.com/g/5000/5000")
public interface Client {

    @RequestMapping(method = RequestMethod.GET)
    byte[] getKitty();

}
