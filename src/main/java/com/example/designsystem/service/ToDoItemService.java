package com.example.designsystem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.designsystem.bean.ToDoItem;
import com.example.designsystem.mapper.ToDoItemMapper;
import org.springframework.stereotype.Service;

@Service
public class ToDoItemService extends ServiceImpl<ToDoItemMapper, ToDoItem> {

}