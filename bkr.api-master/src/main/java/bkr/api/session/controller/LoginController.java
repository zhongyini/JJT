package bkr.api.session.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bkr.api.session.dto.UserLoginReqDto;
import bkr.api.session.dto.UserLoginResDto;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.ResultCode;
import bkr.base.util.string.StringUtil;
import bkr.core.user.entity.User;
import bkr.core.user.service.LoginService;

/**
 * 登录登出控制器
 * 
 * @author chengd
 * 
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    /** 登录服务 */
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * 
     * @param response
     *            ：用于保存token到cookie中
     * @param map
     *            ：包含userName和password
     * @return
     */
    @RequestMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public JsonResult<UserLoginResDto> login(@RequestBody UserLoginReqDto loginReqDto) {
        // 入力验证
        // 用户名
        if (StringUtil.isEmpty(loginReqDto.getName())) {
            return new JsonResult<UserLoginResDto>(ResultCode.PARAMS_ERROR, "用户名不能为空！");
        }

        // 密码
        if (StringUtil.isEmpty(loginReqDto.getPassword())) {
            return new JsonResult<UserLoginResDto>(ResultCode.PARAMS_ERROR, "密码不能为空！");
        }

        // 根据用户名和密码取得注册用户
        User user = loginService.login(loginReqDto.getName(),
                loginReqDto.getPassword());

        // 用户不存在的长
        if (user == null) {
            return new JsonResult<UserLoginResDto>(ResultCode.FAILURE, "登录失败,用户名密码错误");
        }

        // Controller的入出力原则上使用DTO
        ModelMapper modelMapper = new ModelMapper();
        UserLoginResDto dto = modelMapper.map(user, UserLoginResDto.class);

        return new JsonResult<UserLoginResDto>(ResultCode.SUCCESS, "登录成功", dto);
    }
    
}
