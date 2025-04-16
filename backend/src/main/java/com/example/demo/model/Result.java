package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;

//    with return data
    public static <T> Result<T> success(T data) {
        return new Result<>(0,"success",data);
    }

    public static Result success(){
        return new Result(0,"success",null);
    }

    public static Result error(String msg){
        return new Result(1,msg,null);
    }

}
