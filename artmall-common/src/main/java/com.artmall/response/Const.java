package com.artmall.response;

/**标识常量
 * @author
 * @create 2018-08-06 15:05
 **/

public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String IP_ADDRESS="";

    public enum LoginType {

        STUDENT("Student"),BUSINESS("Business"),ADMIN("Admin");

        private String type;

        private LoginType(String type) {
            this.type = type;
        }

        @Override
        public String toString(){
            return this.type.toString();
        }
    }




    public enum OrderStatusEnum{
        CANCELED(0,"已取消"),
        NO_PAY(10,"未支付"),;

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;

        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }

    }
    //是否通过登录
    public static boolean isPass=false;

    /**
     * order状态码
     */
    public   static final byte ORDER_NOPAY = 0;
    public   static final byte ORDER_PAY =1;
    public   static final byte ORDER_TRATING=2;
    public  static final byte ORDER_SUCCESS=3;
    public   static final byte ORDER_FAILE=4;


    /**
     * works作品的状态码
     */
    public static final byte WORKS_STATUS_SHOW=1;
    public static final byte WORKS_STATUS_BIDDING=2;
    public static final byte WORKS_STATUS_CONFIRM = 3;
    public static final byte WORKS_STATUS_SUCESS=4;


    /**
     * project项目的状态码
     */
    public static final byte PROJECT_BIDDING_ADD=0;

    public static final byte PROJECT_BIDDING_NOT_VERIFIED =  10;
    public static final byte PROJECT_BIDDING_VERIFIED =2;
    public static final byte PROJECT_BIDDING= 3;
    public static final byte PROJECT_BIDDING_DONE =4;
    public static final byte PROJECT_ORDER_NOT_PAY = 5;
    public static final byte PROJECT_ORDER_BEING=6;
    public static final byte PROJECT_ORDER_DONE=7;
    public static final byte PROJECT_ORDER_FAILE=8;
    public static final byte PROJECT_BIDDING_OUT_OFF_DATA = 9;


    /**
     * bid投标的状态码
     */
    public static final byte BID_STATUS_BIDDING = 0;
    public static final byte BID_STATUS_SUCCESS =2;
    public static final byte BID_STATUS_FAILE=1;

    /**
     * 订单的类型
     * 1为单独购买作品
     * 2为通过发布项目选购
     */
    public static final byte ORDER_TYPE_ONLY=1;
    public static final byte ORDER_TYPE_BID=2;


    /**
     * 邮件发送的内容
     */
    public static final String FORGET_TITLE_SIGN_UP = "The register massage";

    public static final String SIGN_CONTENT = "welcome to register artmall,please click it:";

    public static final String FORGET_CONTENT = "please reset the password:";

    public static final String ORDER_INFO = "项目信息";
    public static final String SEND_TO_STUDENT="恭喜你被选中啦，以下是企业的联系方式，请尽快联系哦";

    public static final String SEND_TO_BUSINESS="付款成功，以下是学生的信息，请尽快联系，完成项目把！";


    /**
     * 性别
     */
    public static final Byte MAN = 1;
    public static final Byte WOMAN=2;

    /**
     * business状态
     */
    public static final byte BUSINESS_EMAIL = 1;
    public static final byte BUSINESS_PASS=2;
    public static final byte BUSINESS_NOT_PASS=3;
    public static final byte BUSINESS_DELETE=4;

    /**
     * student状态
     */
    public static final byte STUDENT_INIT = 1;
    public static final byte STUDENT_NORMAL = 2;
    public static final byte STUDENT_DELETE = 3;

    /**
     * 角色
     */
    public static final long ROLE_STUDENT=1;
    public static final long ROLE_BUSINESS=2;
    public static final long ROLE_ADMIN=3;

    /**
     * 收藏的状态
     */
    public static final byte IS_COLLECT=1;
    public static final byte DELETE_COLLECT=0;

    /**
     * 加1或减1
     */
    public static final int ADD=1;
    public static final int MINUS=0;

    /**
     * relation状态
     */
    public static final byte IS_FOLLOW = 1;
    public static final byte DELETE_FOLLOW = 0;
    public static final byte FOLLOW_EACHOTHER=3;


}


