package com.example.designsystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.designsystem.commonenum.Category;
import com.example.designsystem.commonenum.State;

import lombok.Data;
import lombok.Generated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
@Data
public class Design {
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer designId;
    private String title;
    private String description;
    private State state;
    private Date startDate;
    private Date endDate;
    private Category category;
    private Integer count;
    private Integer studentId;
    private Integer teacherId;
    private String studentName;
    private String teacherName;


    public void resetId() {
        this.designId = null;
    }

}
