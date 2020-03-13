package com.demo.cn.provider.server;

import com.demo.cn.common.mapper.ContactMapper;
import com.demo.cn.common.model.Contact;
import com.demo.cn.common.model.ContactExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/11/17
 * Time: 13:23
 * Description: No Description
 */
@Service
public class ContactService {

    @Autowired
    private ContactMapper contactMapper;

    //商品名查询 分页
    public List<Contact> queryByName(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "start") int start,
                                     @RequestParam(value = "offset") int offset) {
        ContactExample example = new ContactExample();
        example.createCriteria().andCnameLike("%" + name + "%");
        example.setStartRow((start - 1) * offset);
        example.setPageSize(offset);
        example.setOrderByClause("id");
        List<Contact> list = contactMapper.selectByExample(example);
        return list;
    }

    //添加联系人

    public boolean addContact(@Valid Contact contact) {
        //产品名不能重复
        ContactExample example = new ContactExample();
        example.createCriteria().andCnameEqualTo(contact.getCname());
        List<Contact> Contact1 = contactMapper.selectByExample(example);
        if (0 != Contact1.size()) {
            throw new RuntimeException("名字不能重复！");
        }
        int isSuccess = contactMapper.insert(contact);
        if (0 == isSuccess) {
            return false;
        }
        return true;
    }

    //删除联系人
    public boolean delContact(@RequestParam(value = "contactId") Long contactId) {

        int isDel = contactMapper.deleteByPrimaryKey(contactId);

        switch (isDel) {
            case 1:
                return true;

            default:
                return false;
        }
    }
}
