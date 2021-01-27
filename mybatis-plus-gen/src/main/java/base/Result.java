package base;


import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * @author ：David.Yan
 * @date ：Created in 2019/3/10 20:00
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class Result implements Serializable {

    private int code;
    private String message;
    private Object data;



    //成功调用生成返回
    public  static Result ok(Object data)
    {
        Result result =new Result();
        result.setData(data);
        result.setCode(200);
        result.setMessage("调用成功");
        return result;
    }

    //错误调用生成返回
    public static Result fail(int code,@Nullable Exception data)
    {
        Result result =new Result();
        result.setData(data);
        result.setCode(500);
        if(data!=null)
            result.setMessage(data.getMessage());
        return result;
    }

}
