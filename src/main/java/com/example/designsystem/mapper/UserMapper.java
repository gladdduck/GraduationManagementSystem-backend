package com.example.designsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.designsystem.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
