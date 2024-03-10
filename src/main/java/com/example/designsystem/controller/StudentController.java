package com.example.designsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.designsystem.bean.Student;
import com.example.designsystem.dto.ResultDataDto;
import com.example.designsystem.dto.ResultDto;
import com.example.designsystem.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;


    @RequestMapping("/getstudentname")
    public Map<String,Integer> getStudentName(){
        List<Student> students = studentMapper.selectList(null);
        Map<String,Integer> map = new HashMap<>();
        for(Student student:students){
            if(student.getDesignId()==null){
                map.put(student.getName(),student.getStudentId());
            }
        }
        return map;
    }

    @RequestMapping("/getstudents")
    public ResultDataDto<Student> getStudents(){
        List<Student> students = studentMapper.selectList(null);
        return new ResultDataDto<>(
                students,
                students.size()
        );
    }

    @RequestMapping("/getstudentsbypage")
    public ResultDataDto<Student> getStudentsByPage(
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {
        Page<Student> page = new Page<Student>(pageIndex, pageSize);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        IPage<Student> studentPage = studentMapper.selectPage(page, queryWrapper);
        return new ResultDataDto<Student>(
                studentPage.getRecords(),
                (int) studentPage.getTotal()
        );
    }

    @RequestMapping("/getstudentsbyename")
    public ResultDataDto<Student> getStudentsByName(
            @RequestParam("studentName") String studentName,
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {
        Page<Student> page = new Page<Student>(pageIndex, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", studentName);
        IPage<Student> studentPage = studentMapper.selectPage(page, queryWrapper);
        return new ResultDataDto<Student>(
                studentPage.getRecords(),
                (int) studentPage.getTotal()
        );
    }


    @RequestMapping("/deletestudent")
    public ResultDto deleteStudentsById(
            @RequestParam("studentId") Integer studentId
    ){
        try{
            studentMapper.deleteById(studentId);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @RequestMapping("/deletestudents")
    public ResultDto deleteStudentsByIds(
            @RequestBody List<Integer> studentIds
    ){
        try{

            studentMapper.deleteBatchIds(studentIds);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @PostMapping("/addstudent")
    public ResultDto addStudent(
            @RequestBody Student student
    ){
        try{
            studentMapper.insert(student);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @PostMapping("/addstudents")
    public ResultDto addStudents(
            @RequestBody List<Student> students
    ){
        try{
            students.forEach(student -> studentMapper.insert(student));
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }



    @RequestMapping("/setstudent")
    public ResultDto setStudents(
            @RequestBody Student student
    ){
        try{
            studentMapper.updateById(student);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

}
