package cn.topicstudy.multibizrouter.core;

import cn.topicstudy.jutil.basic.error.CommonAssertUtil;
import cn.topicstudy.jutil.basic.text.StringUtil;
import cn.topicstudy.multibizrouter.core.entity.AddonIdentification;
import cn.topicstudy.multibizrouter.core.entity.RunAddonOption;
import cn.topicstudy.multibizrouter.core.enums.MultiBizRouterErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddonRouter {
    @Autowired
    private AddonCollector addonCollector;

    public <P, R> Slot<P, R> route(AddonIdentification addonIdentification) {
        CommonAssertUtil.throwException(addonIdentification == null, MultiBizRouterErrorCodeEnum.PARAM_ABSENT_WHEN_ROUTE, "插件标志");
        String bizLineCode = addonIdentification.getBizLineCode();
        String slotCode = addonIdentification.getSlotCode();
        String addonCode = addonIdentification.getAddonCode();
        CommonAssertUtil.throwException(StringUtil.isBlank(bizLineCode), MultiBizRouterErrorCodeEnum.PARAM_ABSENT_WHEN_ROUTE, "业务线");
        CommonAssertUtil.throwException(StringUtil.isBlank(slotCode), MultiBizRouterErrorCodeEnum.PARAM_ABSENT_WHEN_ROUTE, "插槽");
        CommonAssertUtil.throwException(StringUtil.isBlank(addonCode), MultiBizRouterErrorCodeEnum.PARAM_ABSENT_WHEN_ROUTE, "插件");
        Slot<P, R> slot = addonCollector.getAddon(bizLineCode, slotCode, addonCode);
        return slot;
    }

    public <P, R> Object routeThenExecute(AddonIdentification addonIdentification, P param, RunAddonOption option) {
        Slot<P, R> slot = route(addonIdentification);
        CommonAssertUtil.throwException(slot == null && !option.isQuietIfNoAddonExist(), MultiBizRouterErrorCodeEnum.ROUTE_NO_ADDON,
                addonIdentification.getSlotCode(), addonIdentification.getAddonCode());

        R res = slot.run(param);
        return res;
    }
}
