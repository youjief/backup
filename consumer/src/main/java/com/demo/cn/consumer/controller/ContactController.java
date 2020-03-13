package com.demo.cn.consumer.controller;

import com.demo.cn.common.model.Contact;
import com.demo.cn.common.untils.result.ReturnResult;
import com.demo.cn.common.untils.result.ReturnResultUtils;
import com.demo.cn.common.vo.ContactVo;
import com.demo.cn.common.vo.Pages;
import com.demo.cn.consumer.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/11/17
 * Time: 13:43
 * Description: No Description
 */
@Api(tags = "联系人")
@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;


    //查询名字
    @ApiOperation(value = "模糊查询")
    @GetMapping(value = "/queryByName")
    public ReturnResult<Pages> queryByName(@ApiParam(value = "不输入查所有，不可以输入空格查询") @RequestParam(value = "name", required = false) String name,
                                           @ApiParam(value = "起始页") @RequestParam(value = "start") int start,
                                           @ApiParam(value = "页面大小") @RequestParam(value = "offset") int offset) {

        Pages pages = new Pages();
        List<Contact> list = contactService.queryByName(name, start, offset);
        pages.setCurrentPage(start);
        pages.setPagesize(offset);
        pages.setCurrList(list);
        return ReturnResultUtils.returnSuccess(pages);
    }

    //新增产品
    @ApiOperation(value = "新增联系人")
    @PostMapping(value = "/addContact")

    public ReturnResult<String> addNewProduct(@Valid ContactVo contactVo) {

        Contact contact = new Contact();
        BeanUtils.copyProperties(contactVo, contact);
        Map<String, Contact> map = new HashMap<>();
        map.put("yj", contact);
        boolean flag = contactService.addContact(map);
        if (flag) {
            return ReturnResultUtils.returnSuccess("新增联系人成功！");
        }
        return ReturnResultUtils.returnFail("新增联系人失败！");
    }


    //删除联系人
    @ApiOperation(value = "删除联系人")
    @PostMapping(value = "/delContact")
    public ReturnResult<String> delContact(@RequestParam(value = "contactId") Long contactId) {
        if (contactService.delContact(contactId)) {
            return ReturnResultUtils.returnSuccess("删除联系人成功！");
        }
        return ReturnResultUtils.returnFail("删除联系人失败！");
    }

}
