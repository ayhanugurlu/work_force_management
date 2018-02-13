package com.au.example.wfm.service;

import com.au.example.wfm.exception.InvalidUserNameOrPassword;
import com.au.example.wfm.service.model.input.LoginInput;
import com.au.example.wfm.service.model.output.LoginOutput;

/**
 * Created by Ayhan.Ugurlu on 13/02/2018
 */
public interface LoginService {

    LoginOutput login(LoginInput loginInput) throws InvalidUserNameOrPassword;
}
