package paul.sydney.commen.result;

public enum CommonResultCode implements IErrorCode {
    /**
     * 调用成功
     */
    Success("successful."),
    /**
     * 未定义错误
     */
    UndefinedError("undefined error:%s.");
 
    private String message;
 
    private CommonResultCode(String message) {
        this.message = message;
    }
 
    @Override
    public String getCode() {
        return this.name();
    }
 
    @Override
    public String getMessage() {
        return this.message;
    }
}
