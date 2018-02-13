package com.au.example.wfm.service;

import com.au.example.wfm.Repository.UserRepository;
import com.au.example.wfm.entity.User;
import com.au.example.wfm.exception.InvalidUserNameOrPassword;
import com.au.example.wfm.service.model.input.LoginInput;
import com.au.example.wfm.service.model.output.LoginOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Ayhan.Ugurlu on 13/02/2018
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepossitory;

    public LoginOutput login(LoginInput loginInput) throws InvalidUserNameOrPassword {
        User user = userRepossitory.findByEmail(loginInput.getEmail());
        if(user != null && user.getPassword().equals(loginInput.getPassword())){
            //TODO generate real token then save it and validate it for all request
            String token = UUID.randomUUID().toString();
            LoginOutput loginOutput = new LoginOutput();
            loginOutput.setToken(token);
            return loginOutput;
        }else{
            throw new InvalidUserNameOrPassword();
        }
    }

}
