package com.example.designsystem.dto;

public class ResultDto {
    public String message;
    public Boolean success;
    public ResultDto(String message, Boolean success){
        this.message=message;
        this.success=success;
    }
}


