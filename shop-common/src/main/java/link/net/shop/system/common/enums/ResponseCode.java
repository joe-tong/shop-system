package link.net.shop.system.common.enums;//


/**
 * @author tpp
 */

public enum ResponseCode implements BaseEnum<Integer> {
    /**
     * BASE响应状态数据
     */
    SUCCESS(200),
    ERROR(500),





    ;



    private int code;
    private String msg;

    private ResponseCode(int code) {
        this.code = code;
        this.msg = this.name();
    }

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Integer code() {
        return this.code;
    }


    public int getCode() {
        return this.code;
    }
}
