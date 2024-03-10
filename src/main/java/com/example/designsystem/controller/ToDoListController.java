package com.example.designsystem.controller;


import com.example.designsystem.bean.Design;
import com.example.designsystem.bean.Teacher;
import com.example.designsystem.bean.ToDoItem;
import com.example.designsystem.commonenum.Category;
import com.example.designsystem.commonenum.State;
import com.example.designsystem.dto.ResultDto;
import com.example.designsystem.mapper.DesignMapper;
import com.example.designsystem.mapper.StudentMapper;
import com.example.designsystem.mapper.TeacherMapper;
import com.example.designsystem.mapper.ToDoItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping("/dashboard")
@RestController
public class ToDoListController {

    @Autowired
    private ToDoItemMapper todolistMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DesignMapper designMapper;



    @RequestMapping("/getlist")
    public List<ToDoItem> getList(){
        List<ToDoItem> todolist = todolistMapper.selectList(null);
        return todolist;
    }
    @RequestMapping("/getItemNums")
    public Map<String,Integer> getItemNums(){
        int teacherNum = teacherMapper.selectList(null).size();
        int studentNum = studentMapper.selectList(null).size();
        int designNum = designMapper.selectList(null).size();
        Map<String,Integer> map = new java.util.HashMap<>();
        map.put("teacherNum",teacherNum);
        map.put("studentNum",studentNum);
        map.put("designNum",designNum);
        return map;
    }
    @RequestMapping("/getStatePercent")
    public Map<State,Double> getStatePercent(){
        List<Design> designList = designMapper.selectList(null);
        int designNum = designList.size();
        Map<State, Integer> stateCounts = new HashMap<>();
        for (Design design : designList) {
            State state = design.getState();
            stateCounts.put(state, stateCounts.getOrDefault(state, 0) + 1);
        }
        Map<State, Double> stateProportions = new HashMap<>();
        for (State state : State.values()) {
            int count = stateCounts.getOrDefault(state, 0);
            double proportion = (double) count / designNum * 100;
            stateProportions.put(state, proportion);
        }
        return stateProportions;
    }
    @RequestMapping("/getPieData")
    public Map<Category,Integer> getPieData() {
        List<Design> designList = designMapper.selectList(null);
        Map<Category, Integer> categoryCounts = new HashMap<>();
        for (Design design : designList) {
            Category category = design.getCategory();
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
        }
        return categoryCounts;
    }

    @RequestMapping("/getBarData")
    public Map<String,Integer> getBarData() {
        List<Teacher> teacherList = teacherMapper.selectList(null);
        Set<String> titleSet = new HashSet<>();
        for (Teacher teacher : teacherList) {
            String title = teacher.getProfessionalTitle();
            titleSet.add(title);
        }
        Map<String, Integer> titleCounts = new HashMap<>();
        for (Teacher teacher : teacherList){
            String title = teacher.getProfessionalTitle();
            titleCounts.put(title, titleCounts.getOrDefault(title, 0) + 1);
        }
        return titleCounts;
    }



    @RequestMapping("/addlist")
    public ResultDto addList(@RequestParam("todoDone") Boolean todoDone,
                             @RequestParam("todoName") String todoName){
        try{
            ToDoItem todolist = new ToDoItem(todoName,todoDone);
            todolistMapper.insert(todolist);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }
}
