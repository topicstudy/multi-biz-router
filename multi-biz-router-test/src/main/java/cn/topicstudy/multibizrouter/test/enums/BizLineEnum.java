package cn.topicstudy.multibizrouter.test.enums;

/**
 * 公司下的业务线
 */
public enum BizLineEnum {
    TRIP("TRIP", "文旅"),
    SHOP("SHOP", "电商"),
    UGC("UGC", "内容"),
    ;

    private final String code;
    private final String desc;


    BizLineEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
