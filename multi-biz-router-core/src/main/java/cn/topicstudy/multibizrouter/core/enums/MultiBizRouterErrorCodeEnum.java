package cn.topicstudy.multibizrouter.core.enums;

import cn.topicstudy.jutil.basic.error.BaseErrorCodeEnum;

public enum MultiBizRouterErrorCodeEnum implements BaseErrorCodeEnum {
    // 1:route
    PARAM_ABSENT_WHEN_ROUTE("TS-MULTI_BIZ_ROUTER-1-1","路由插件时，缺少必要参数：{0}"),
    ROUTE_NO_ADDON("TS-MULTI_BIZ_ROUTER-1-2","未路由到插件，槽：{0}，插件：{1}"),
    // 2:init addon
    ADDON_IDENTIFICATION_ABSENT("TS-MULTI_BIZ_ROUTER-2-1","初始化插件失败，插件缺少标识：{0}"),
    ;
    private String errorCode;
    private String errorMsg;

    MultiBizRouterErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
