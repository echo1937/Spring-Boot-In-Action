package org.example.single.address.controller;

import org.example.single.address.entity.Address;
import org.example.single.address.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Address)表控制层
 */
@RestController
public class AddressController {
    /**
     * 服务对象
     */
    @Resource
    private AddressService addressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("address/selectOne")
    public Address selectOne(Long id) {
        return this.addressService.queryById(id);
    }

}