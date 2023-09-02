package cn.topicstudy.multibizrouter.test.addon.buyairticket;

import cn.topicstudy.multibizrouter.core.Slot;
import cn.topicstudy.multibizrouter.test.enums.BizLineEnum;
import cn.topicstudy.multibizrouter.test.enums.SlotEnum;
import cn.topicstudy.multibizrouter.test.enums.BuyAirTicketAddonEnum;
import org.springframework.stereotype.Component;

@Component
public class BuyAirTicketCZAddon implements Slot<Integer, String> {
    @Override
    public String bizLineCode() {
        return BizLineEnum.TRIP.getCode();
    }

    @Override
    public String slotCode() {
        return SlotEnum.BUY_AIR_TICKET.getCode();
    }

    @Override
    public String addonCode() {
        return BuyAirTicketAddonEnum.CZ.getCode();
    }

    @Override
    public String run(Integer count) {
        System.out.println("BuyTicketAddon CZ,count:" + count);
        return "BuyTicketAddon CZ,count:" + count;
    }
}
