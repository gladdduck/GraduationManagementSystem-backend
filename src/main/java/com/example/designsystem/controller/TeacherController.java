package com.example.designsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.designsystem.bean.Teacher;
import com.example.designsystem.dto.ResultDataDto;
import com.example.designsystem.dto.ResultDto;
import com.example.designsystem.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teacher")
@RestController
public class TeacherController {
    @Autowired
    private TeacherMapper teacherMapper;


    @RequestMapping("/getteachers")
    public ResultDataDto<Teacher> getTeacher(){
        List<Teacher> teacher = teacherMapper.selectList(null);
        return new ResultDataDto<Teacher>(
                teacher,
                teacher.size()
        );
    }

    @RequestMapping("/getteacherbyconditions")
    public ResultDataDto<Teacher> getDesigsByName(
            @RequestParam("conditions") String conditions,
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {
        Page<Teacher> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", conditions).or().like("direction", conditions).or().like("professional_title", conditions);
        IPage<Teacher> teacherPage = teacherMapper.selectPage(page, queryWrapper);
        return new ResultDataDto<Teacher>(
                teacherPage.getRecords(),
                (int) teacherPage.getTotal()
        );
    }


    @RequestMapping("/deleteteacher")
    public ResultDto deleteTeachersById(
            @RequestParam("teacherId") Integer teacherId
    ){
        try{
            teacherMapper.deleteById(teacherId);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @RequestMapping("/deleteteachers")
    public ResultDto deleteTeachersByIds(
            @RequestBody List<Integer> teacherIds
    ){
        try{
            teacherMapper.deleteBatchIds(teacherIds);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }
    
    @PostMapping("/addteacher")
    public ResultDto addTeacher(
            @RequestBody Teacher teacher
    ){
        try{
            teacherMapper.insert(teacher);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }




    @RequestMapping("/setteacher")
    public ResultDto setTeachers(
            @RequestBody Teacher teacher
    ){
        try{
            teacherMapper.updateById(teacher);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

}
