package com.example.designsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.designsystem.bean.Student;
import com.example.designsystem.bean.ToDoItem;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends BaseMapper<Student> {
}
