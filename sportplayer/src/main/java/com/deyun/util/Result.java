package com.deyun.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private int status;
    private String message;
    private Object data;


    public static Result success(Object data){
        Result result=new Result();
        result.message="操作成功!";
        result.status=200;
        result.data=data;
        return result;
    }
    public static Result fail(String message){
        Result result=new Result();
        result.message=message;
        result.status=400;
        return result;
    }
}
