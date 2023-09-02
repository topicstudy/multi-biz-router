package cn.topicstudy.multibizrouter.core;

import cn.topicstudy.jutil.basic.collection.CollectionUtil;
import cn.topicstudy.jutil.basic.error.CommonAssertUtil;
import cn.topicstudy.jutil.basic.text.StringUtil;
import cn.topicstudy.multibizrouter.core.enums.MultiBizRouterErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AddonCollector implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private List<Slot> addonList;

    private Map<String, Slot> addonMap = new HashMap<>();
    private boolean isInitialed = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isInitialed) {
            return;
        }
        initAddon();
    }

    private synchronized void initAddon() {
        if (CollectionUtil.isEmpty(addonList)) {
            return;
        }
        for (Slot slot : addonList) {
            String slotCode = slot.slotCode();
            String addonCode = slot.addonCode();
            CommonAssertUtil.throwException(StringUtil.isBlank(slotCode),
                    MultiBizRouterErrorCodeEnum.ADDON_IDENTIFICATION_ABSENT, "槽");
            CommonAssertUtil.throwException(StringUtil.isBlank(addonCode),
                    MultiBizRouterErrorCodeEnum.ADDON_IDENTIFICATION_ABSENT, "插件");
            addonMap.put(slot.bizLineCode() + "#" + slotCode + "#" + addonCode, slot);
        }
        isInitialed = true;
    }

    public Slot getAddon(String bizLineCode, String slotCode, String addonCode) {
        return addonMap.get(bizLineCode + "#" + slotCode + "#" + addonCode);
    }

    public Map<String, Slot> getAddonMap() {
        return addonMap;
    }
}
