package com.tingyu.venus.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/21 15:27
 * @Version venus-server
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CommonResult implements Serializable {
    private int code;

    private String message;

    private Map<String,Object> data=new HashMap<>();


    public CommonResult(int code, String message) {
        this.code = code;
        this.message = message;

    }



    public static CommonResult ok(){

        return new CommonResult().setCode(2000).setMessage("Success");
    }

    public static CommonResult error(){
        return new CommonResult().setCode(500).setMessage("Error");
    }


    public CommonResult code(int code){
        this.code=code;
        return this;
    }

    public CommonResult message(String message){
        this.message=message;
        return this;
    }



    public  CommonResult data(String key,Object value){

        this.data.put(key,value);


        return this;
    }

    public CommonResult data(Map<String ,Object> data){
        this.data=data;
        return this;
    }

}
