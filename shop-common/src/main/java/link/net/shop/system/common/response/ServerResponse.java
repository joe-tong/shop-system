package link.net.shop.system.common.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import link.net.shop.system.common.enums.ResponseCode;

import java.io.Serializable;

/**
 * @author TPP
 * @Description: 统一响应数据格式
 * @date
 */
@ApiModel(description = "数据返回格式")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
    @ApiModelProperty("返回数据状态")
    private int status;
    @ApiModelProperty("返回的消息")
    private String msg;
    @ApiModelProperty("返回的数据")
    private T data;
    @ApiModelProperty("总条数")
    private Long total;

    public ServerResponse() {
    }

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.data = data;
        this.status = status;
    }

    public ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;

    }

    public ServerResponse(int status, String msg, T data, Long total) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.total = total;

    }


    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }


    /**
     * 正确
     * status赋值
     *
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> success() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    /**
     * 正确
     * status和msg赋值
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> success(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * 正确
     * status和data赋值
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * 正确
     * status,data,msg赋值
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> success(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 正确
     * data,msg,total赋值
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> success(String msg, T data, Long total) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data, total);
    }

    /**
     * 正确
     * data,total赋值
     *
     * @param
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> success(T data, Long total) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data, total);
    }

    /**
     * 错误status赋值
     *
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> error() {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg());
    }

    /**
     * status和msg赋值
     *
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> error(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    /**
     * errorCode和errorMessage赋值
     *
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> error(String errorMessage, int errorCode) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


}
