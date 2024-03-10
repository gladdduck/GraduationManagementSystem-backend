package com.example.designsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.designsystem.bean.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper extends BaseMapper<Message> {
}
