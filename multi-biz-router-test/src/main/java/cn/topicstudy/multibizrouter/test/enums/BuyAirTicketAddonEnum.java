package cn.topicstudy.multibizrouter.test.enums;

public enum BuyAirTicketAddonEnum {
    CA("CA", "中航"),
    CZ("CZ", "南航"),
    ;

    private final String code;
    private final String desc;


    BuyAirTicketAddonEnum(String code, String desc) {
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
