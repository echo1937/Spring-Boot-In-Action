package com.example.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.springbootmybatisplus.entity.UserDO;
import com.example.springbootmybatisplus.pojo.SexEnum;
import com.example.springbootmybatisplus.service.IUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * https://www.bezkoder.com/swagger-3-annotations/
 */
@OpenAPIDefinition(info = @Info(title = "Knife4j - 样例演示", description = "标准版", contact = @Contact(name = "时代楷模"), version = "1.0-SNAPSHOT"))
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "样例接口", description = "以UserController为例")
public class UserController {

    private final IUserService userService;

    /**
     * description: 新增
     */
    @Operation(summary = "新增(固定属性的)用户", description = "演示和对比Java对象和数据库字段之间的关系")
    @GetMapping("/save")
    public boolean saveUser() {
        UserDO userDO = new UserDO();
        userDO.setNickname("大漂亮");
        userDO.setSex(SexEnum.MAN);
        userDO.setHobbies(Set.of("游泳", "健身"));
        userDO.setIpaddress("8.8.8.8");
        return userService.save(userDO);
    }

    /**
     * description: 修改
     */
    @Parameters(value = {
            @Parameter(name = "nickname", description = "用户名"),
            @Parameter(name = "id", description = "用户id")})
    @Operation(summary = "更新用户", description = "update user's nickname by id")
    @GetMapping("/update")
    public boolean update(@RequestParam String nickname, @RequestParam Long id) {
        UserDO userDO = new UserDO();
        userDO.setNickname(nickname);
        userDO.setId(id);
        return userService.updateById(userDO);
    }

    /**
     * description: 删除
     */
    @Operation(summary = "删除用户", description = "Delete user by id")
    @GetMapping("/delete")
    public boolean delete(@Parameter(description = "用户id") @RequestParam Long id) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        return userService.removeById(userDO);
    }

    /**
     * description: 列表
     */
    @Operation(summary = "查看用户", description = "Get list of users")
    @GetMapping("/list")
    public List<UserDO> listUser() {
        return userService.list();
    }

    /**
     * description: 分页列表
     */
    @Parameters(value = {
            @Parameter(name = "current", description = "当前分页"),
            @Parameter(name = "size", description = "分页大小")})
    @Operation(summary = "分页展示", description = "以分页的方式展示用户信息")
    @GetMapping("/page")
    public Page<UserDO> pageUser(@RequestParam(defaultValue = "1") int current, @RequestParam(defaultValue = "5") int size) {
        return userService.page(new Page<>(current, size), new QueryWrapper<>(new UserDO()));
    }


    /**
     * 序列化、反序列化、校验
     */
    @Operation(summary = "新增用户", description = "演示如何使用在@RequestBody接收枚举字段、jsonb字段, 不会真正操作db")
    @PostMapping("/add")
    public UserVo addUser(@RequestBody UserVo userVo) {
        return userVo;
    }

}

@Data
@Schema(description = "请求体UserVO")
class UserVo {
    @Schema(description = "主键, 由db自动生成", example = "100", requiredMode = RequiredMode.NOT_REQUIRED)
    private Long id;
    @Schema(description = "昵称", minLength = 2, maxLength = 18, example = "小明", requiredMode = RequiredMode.REQUIRED)
    private String nickname;
    @Schema(description = "性别", example = "男", requiredMode = RequiredMode.REQUIRED)
    private SexEnum sex;
    @Schema(description = "爱好")
    private Set<String> hobbies;
    @Schema(description = "ip地址", example = "127.0.0.1")
    private String ipaddress;
}