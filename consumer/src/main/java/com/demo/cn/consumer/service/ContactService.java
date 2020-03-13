package com.demo.cn.consumer.service;

import com.demo.cn.common.model.Contact;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/11/17
 * Time: 13:44
 * Description: No Description
 */
@FeignClient(name = "provider", fallback = ContactService.fallback.class) //这里的name是你想调用服务的name
public interface ContactService {
    //这里的请求方式是根据你想请求的接口的类型来定义的 这里的value是你想请求接口的uri
    //根据名字模糊查询
    @GetMapping(value = "contact/queryByName")
    public List<Contact> queryByName(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "start") int start,
                                     @RequestParam(value = "offset") int offset);

    //新增联系人
    @PostMapping(value = "contact/addContact")
    public boolean addContact(@RequestBody Map<String, Contact> map);


    //删除联系人
    @PostMapping(value = "contact/delContact")
    public boolean delContact(@RequestParam(value = "contactId") Long contactId);


    @Component
    public class fallback implements ContactService {


        @Override
        public List<Contact> queryByName(String name, int start, int offset) {
            return null;
        }

        @Override
        public boolean addContact(Map<String, Contact> map) {
            return false;
        }

        @Override
        public boolean delContact(Long contactId) {
            return false;
        }
    }

}
