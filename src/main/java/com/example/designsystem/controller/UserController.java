package com.example.designsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.designsystem.bean.Student;
import com.example.designsystem.bean.User;
import com.example.designsystem.dto.ResultDto;
import com.example.designsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public ResultDto login(
            @RequestBody Map<String, String> userMap
    ){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userMap.get("username"));
        User user = userMapper.selectOne(queryWrapper);
        if (user.getPassword().equals(userMap.get("password"))) {
            return new ResultDto(user.getUserId().toString(), true);
        } else {
            return new ResultDto("password is wrong", false);
        }
    }
    @RequestMapping("/getsignatures")
    public ResultDto getSignatures(
            @RequestParam("userId") Integer userId
    ){
        User user = userMapper.selectById(userId);
        return new ResultDto(user.getSignatures(), true);
    }

    @RequestMapping("/resetsignature")
    public ResultDto resetSignature(
            @RequestBody Map<String, String> userMap
    ){
        User user = userMapper.selectById(userMap.get("userId"));
        user.setSignatures(userMap.get("signature"));
        userMapper.updateById(user);
        return new ResultDto("success", true);
    }

    @RequestMapping("/resetpassword")
    public ResultDto getUser(
            @RequestBody Map<String, String> userMap
    ){
        User user = userMapper.selectById(userMap.get("id"));
        if (user.getPassword().equals(userMap.get("old"))) {
            user.setPassword(userMap.get("new"));
            userMapper.updateById(user);
            return new ResultDto("success", true);
        } else {
            return new ResultDto("old password is wrong", false);
        }
    }
}
