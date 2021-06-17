package com.wbj.controller;


import com.wbj.common.Result;
import com.wbj.entity.Hr;
import com.wbj.service.IHrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wbj
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/hr")
@Api(value = "hr", tags = "hr管理")
@CrossOrigin
public class HrController {


    @Autowired
    private IHrService hrService;
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "登录账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, paramType = "query")
    })
    @PostMapping("/login")
    public Result login(@RequestParam("name") String name,@RequestParam("password")String password){
        return hrService.login(name, password);
    }

    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hr", value = "用户", required = true, paramType = "body"),

    })
    @PostMapping("/register")
    public Result register(@RequestBody Hr hr){
        return hrService.register(hr);
    }
}

