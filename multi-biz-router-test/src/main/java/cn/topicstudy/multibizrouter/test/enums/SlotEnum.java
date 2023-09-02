package cn.topicstudy.multibizrouter.test.enums;

public enum SlotEnum {
    BUY_AIR_TICKET("ticket-air-buyFromAirCompany", "购买飞机票"),
    ;

    private final String code;
    private final String desc;


    SlotEnum(String code, String desc) {
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
