package in.co.web.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseResponse {

    private int status;
    private String message;
    private Object result;

    public BaseResponse(int status, String message, Object result){
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
