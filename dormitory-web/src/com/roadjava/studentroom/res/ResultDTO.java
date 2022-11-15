package com.roadjava.studentroom.res;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 结果类
 */
public class ResultDTO<T> {
    private String errCode;
    private String errMsg;
    private Boolean success = Boolean.TRUE;
    private T data;
    private Long total;
    private Map<String,Object> attributes = new HashMap<>();
    private ResultDTO(){
    }

    public void addAttr(String key,Object value){
        this.attributes.put(key,value);
    }

    public static <T> ResultDTO<T> buildSuccess(T t, Number total){
        ResultDTO<T> resultDTO = buildSuccess(t);
        resultDTO.setTotal(total.longValue());
        return resultDTO;
    }

    public static <T> ResultDTO<T> buildEmptySuccess(){
        return new ResultDTO<>();
    }

    public static <T> ResultDTO<T> buildSuccess(T t){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setData(t);
        return resultDTO;
    }
    public static <T> ResultDTO<T> buildFailure(String code, String errMsg) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setErrCode(code);
        resultDTO.setErrMsg(errMsg);
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    public static <T> ResultDTO<T> buildFailure(String errMsg) {
        return buildFailure(null,errMsg);
    }

    // setter getter
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
