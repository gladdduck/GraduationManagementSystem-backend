package com.example.designsystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class ToDoItem {
    public ToDoItem(){

    }
    public ToDoItem(String todoName,Boolean todoDone){
        this.todoDone = todoDone;
        this.todoName = todoName;
    }
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String todoName;

    public Boolean todoDone;

}
