package com.example.designsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.designsystem.bean.Design;

import com.example.designsystem.bean.Student;
import com.example.designsystem.dto.ResultDataDto;
import com.example.designsystem.dto.ResultDto;
import com.example.designsystem.mapper.DesignMapper;
import com.example.designsystem.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/design")
@RestController
public class DesignController {
    @Autowired
    private DesignMapper designMapper;
    @Autowired
    private StudentMapper studentMapper;


    @RequestMapping("/getdesignsbyid")
    public Design getDesignById(
            @RequestParam("designId") Integer designId
    ){
        return designMapper.selectById(designId);
    }
    @RequestMapping("/getdesignsbypage")
    public ResultDataDto<Design> getDesignsByPage(
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {

        Page<Design> page = new Page<Design>(pageIndex, pageSize);
        LambdaQueryWrapper<Design> queryWrapper = new LambdaQueryWrapper<>();
        IPage<Design> designPage = designMapper.selectPage(page, queryWrapper);

        return new ResultDataDto<Design>(
                designPage.getRecords(),
                (int) designPage.getTotal()
        );
    }
    @RequestMapping("/getdesignbyconditions")
    public ResultDataDto<Design> getDesigsByName(
            @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam("state") String state,
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize
    ) {
        Page<Design> page = new Page<Design>(pageIndex, pageSize);
        QueryWrapper<Design> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", title);
        queryWrapper.like("category", category);
        queryWrapper.like("state", state);
        IPage<Design> designPage = designMapper.selectPage(page, queryWrapper);

        return new ResultDataDto<Design>(
                designPage.getRecords(),
                (int) designPage.getTotal()
        );
    }

    @RequestMapping("/deletedesign")
    public ResultDto deleteDesignsById(
            @RequestParam("designId") Integer designId
    ){
        try{
            designMapper.deleteById(designId);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @RequestMapping("/deletedesigns")
    public ResultDto deleteDesignsByIds(
            @RequestBody List<Integer> designIds
    ){
        try{

            designMapper.deleteBatchIds(designIds);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }
    @PostMapping("/adddesign")
    public ResultDto addDesign(
            @RequestBody Design design
    ){
        design.resetId();
        try{
            designMapper.insert(design);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @RequestMapping("/setdesign")
    public ResultDto setDesigns(
            @RequestBody Design design
    ){
        try{
            designMapper.updateById(design);
            if(design.getStudentId()!=null){
                Student student = studentMapper.selectById(design.getStudentId());
                student.setState(1);
                student.setDesignId(design.getDesignId());
                student.setDesignTitle(design.getTitle());
                studentMapper.updateById(student);
            }


        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }
}
