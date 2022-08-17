package net.seehope.foodie.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dayday
 * @date 2022/7/22 19:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    /**
     * 状态码
     */
    private int status;

    private Object data;

    private String msg;

    public static JsonResult isOk(Object data){
        return new JsonResult(200,data,"success");
    }

    public static JsonResult error(int status,String msg){
        return new JsonResult(status,null,msg);
    }

    public static JsonResult errorParams(String msg,Object data){
        return new JsonResult(400,data,msg);
    }

    public static JsonResult errorAuthorize(String msg,Object data){
        return new JsonResult(401,data,msg);
    }
}
