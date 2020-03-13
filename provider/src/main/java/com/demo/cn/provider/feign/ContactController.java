package com.demo.cn.provider.feign;

import com.demo.cn.common.model.Contact;

import com.demo.cn.provider.server.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/11/17
 * Time: 13:23
 * Description: No Description
 */
@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;


    //商品名查询 分页
    @GetMapping(value = "/queryByName")
    public List<Contact> queryByName(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "start") int start,
                                     @RequestParam(value = "offset") int offset) {
        return contactService.queryByName(name, start, offset);
    }


    //添加联系人
    @PostMapping(value = "/addContact")
    public boolean addContact(@RequestBody Map<String, Contact> map) {
        Contact contact = map.get("yj");
        return contactService.addContact(contact);
    }

    //删除联系人
    @PostMapping(value = "/delContact")
    public boolean delContact(@RequestParam(value = "contactId") Long contactId) {
        return contactService.delContact(contactId);
    }
}
