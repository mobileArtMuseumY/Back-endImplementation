package com.artmall.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 自定义响应结构
 */
public class CodeResult {

    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //业务响应模式
    private Integer status;

    //响应信息
    private String msg;

    //响应中的数据
    private Object data;
    private String md5;


    public CodeResult(Integer status, String msg, Object data, String md5) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.md5 = md5;
    }

    public CodeResult(String msg, Object data, String md5) {
        this.msg = msg;
        this.data = data;
        this.md5 = md5;
    }

    public CodeResult(Object data, String md5, Object o) {
        this.data = data;
        this.md5 = md5;
    }


    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public static CodeResult build(Integer status,String msg,Object data){
        return new CodeResult(status,msg,data);
    }

    public static CodeResult ok(Object data){
        return new CodeResult(data);
    }

    public static CodeResult ok(){
        return new CodeResult(null);
    }

    public CodeResult() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static CodeResult build(Integer status,String msg){
        return new CodeResult(status,msg,null);
    }
    public CodeResult(Object data){
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    /**
     * 将结果集转换为CodeResult对象
     *
     */
    public static CodeResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, CodeResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static CodeResult format(String json) {
        try {
            return MAPPER.readValue(json, CodeResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static CodeResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
