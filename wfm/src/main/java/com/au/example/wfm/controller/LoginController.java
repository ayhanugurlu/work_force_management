package com.au.example.wfm.controller;

import com.au.example.wfm.exception.InvalidUserNameOrPassword;
import com.au.example.wfm.service.LoginService;
import com.au.example.wfm.service.model.input.LoginInput;
import com.au.example.wfm.service.model.output.LoginOutput;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.au.example.wfm.controller.model.req.LoginReq;
import com.au.example.wfm.controller.model.resp.LoginResp;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ayhan.Ugurlu on 13/02/2018
 */
@RestController
public class LoginController {


    @Autowired
    @Qualifier("loginMapper")
    private MapperFacade mapperFacade;


    @Autowired
    LoginService loginService;

    @ApiOperation(value = "login user ",
            notes = "login user into the wfm application.<br/>")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    LoginResp loginUsers(@ApiParam(value = "username and password") @RequestBody LoginReq loginRequest) throws InvalidUserNameOrPassword {
        LoginInput loginInput = mapperFacade.map(loginRequest, LoginInput.class);
        LoginOutput loginOutput = loginService.login(loginInput);
        LoginResp loginResp = mapperFacade.map(loginOutput, LoginResp.class);
        return loginResp;
    }
}
