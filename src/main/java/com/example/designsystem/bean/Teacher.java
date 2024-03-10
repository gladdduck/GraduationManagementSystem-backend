package com.example.designsystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.designsystem.commonenum.Gender;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
public class Teacher {
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  teacherId;
    private String name;
    private Gender gender;
    private String direction;
    private String telephones;
    private String email;
    private String professionalTitle;

}
