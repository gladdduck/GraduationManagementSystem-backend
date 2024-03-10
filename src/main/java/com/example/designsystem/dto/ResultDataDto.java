package com.example.designsystem.dto;

import java.util.List;

public class ResultDataDto<T> {
    public String message;
    public Boolean success;
    public List<T> data;
    public Integer total;

    public ResultDataDto(List<T> data,Integer total){
        this.message = "200";
        this.success = true;
        this.data= data;
        this.total=total;
    }
}
