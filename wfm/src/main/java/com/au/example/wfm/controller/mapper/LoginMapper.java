package com.au.example.wfm.controller.mapper;

import com.au.example.wfm.controller.model.req.LoginReq;
import com.au.example.wfm.controller.model.resp.LoginResp;
import com.au.example.wfm.service.model.input.LoginInput;
import com.au.example.wfm.service.model.output.LoginOutput;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by Ayhan.Ugurlu on 13/02/2018
 */
@Component
public class LoginMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(LoginInput.class, LoginReq.class)
                .byDefault()
                .register();

        factory.classMap(LoginResp.class, LoginOutput.class)
                .byDefault()
                .register();



    }
}
