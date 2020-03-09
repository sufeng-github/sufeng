package paul.sydney.commen.result;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 7801949203978416054L;
 
    /**
     * 标识本次调用是否返回
     */
    private boolean           success;
 
    /**
     * 本次调用返回code，一般为错误代码
     */
    private String            code;
 
    /**
     * 本次调用返回的消息，一般为错误消息
     */
    private String            message;
 
    public BaseResult() {
        this.code = CommonResultCode.Success.getCode();
        this.success = true;
        this.message = CommonResultCode.Success.getMessage();
    }
 
    /**
     * 设置错误信息
     * 
     * @param code
     * @param message
     */
    public <R extends BaseResult> R setError(String code, String message) {
        this.code = code;
        this.success = false;
        this.message = message;
        return (R) this;
    }
 
    /**
     * 设置错误信息
     * 
     * @param errcode
     * @param args
     * @return
     */
    public <R extends BaseResult> R setError(IErrorCode errcode, Object... args) {
        this.code = errcode.getCode();
        this.success = false;
        String err = errcode.getMessage();
        if (args == null || args.length == 0) {
            this.message = err;
        } else {
            this.message = String.format(err, args);
        }
        return (R) this;
    }
 
    public boolean isSuccess() {
        return success;
    }
 
    public void setSuccess(boolean success) {
        this.success = success;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
}