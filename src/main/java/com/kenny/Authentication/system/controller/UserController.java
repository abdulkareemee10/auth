package com.kenny.Authentication.system.controller;

import com.kenny.Authentication.system.dto.ForgotPasswordRequest;
import com.kenny.Authentication.system.dto.LoginRequest;
import com.kenny.Authentication.system.dto.Request;
import com.kenny.Authentication.system.dto.Response;
import com.kenny.Authentication.system.entities.Profile;
import com.kenny.Authentication.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(
            @RequestBody  Request request){
        return userService.signUp(request);
    }
    @PostMapping("/login")
    public Response userLogin(LoginRequest loginRequest){
      return userService.login(loginRequest);

    }

    @PutMapping("/{email}")
    public void changePassword(@PathVariable("email") String email, @RequestBody ForgotPasswordRequest forgotPasswordRequest){
        try

        {
            userService.changePassword(forgotPasswordRequest, email);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
