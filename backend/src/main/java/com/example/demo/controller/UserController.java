package com.example.demo.controller;

import com.example.demo.model.Result;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.MD5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestParam("username")
                               @Pattern(regexp = "^\\S{5,16}$",
                                       message = "Username must be 5 to 16 non-space characters.") String userName,
                           @RequestParam("password")
                           @Pattern(regexp = "^\\S{5,16}$",
                                   message = "Password must be 5 to 16 characters with no spaces.") String password) {
//        Query User
        User u = userService.findByUserName(userName);
        if (u == null){
            userService.register(userName, password);
            return Result.success();
        }else{
            return Result.error("Username is already taken");
        }

    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam("username")
                                    @Pattern(regexp = "^\\S{5,16}$",
                                            message = "Username must be 5 to 16 non-space characters.") String userName,
                                @RequestParam("password")
                                    @Pattern(regexp = "^\\S{5,16}$",
                                            message = "Password must be 5 to 16 characters with no spaces.") String password){
        User loginUser = userService.findByUserName(userName);
        if(loginUser == null){
            return Result.error("Username is wrong");
        }


        // 判断密码是否正确 loginUser对象中的password是密文
        if (MD5Util.md5(password).equals(loginUser.getPassword())) {
            // 登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("Incorrect password");
    }

}
