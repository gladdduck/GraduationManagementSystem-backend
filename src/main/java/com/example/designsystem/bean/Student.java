package com.example.designsystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.designsystem.commonenum.Gender;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
@Data
public class Student {
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String name;
    private Gender gender;
    private Date birthday;
    private String classes;
    private String address;
    private String telephones;
    private String email;
    private Integer state;
    private Integer designId;
    private String designTitle;
    private String thumb;


}
