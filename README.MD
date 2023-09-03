# 介绍
将项目中多业务场景路由代码的逻辑剥离出来

# 使用
1. maven坐标
```xml
<dependency>
    <groupId>io.github.topicstudy</groupId>
    <artifactId>multi-biz-router-core</artifactId>
    <version>RELEASE</version>
</dependency>
```

2. 示例代码
```java
@RestController
@RequestMapping("/test")
public class TestAddonRouterController {
    @Autowired
    private AddonRouter addonRouter;
    @Autowired
    private AddonCollector addonCollector;

    /**
     * http://127.0.0.1:8081/test/testBuyTicket?mode=2&com=CZ
     *
     * @param mode
     * @param com
     * @return
     */
    @RequestMapping("/testBuyTicket")
    public String testBuyTicket(String mode, String com) {
        try {
            // 选择的航司
            String airCompany = com;
            // 要买的票数量，时间、路线等条件不考虑
            Integer ticketCount = 5;

            // 调航司接口买票
            String res;
            if ("1".equals(mode)) {
                res = buyTicketFromAirCompany(airCompany, ticketCount);
            } else {
                res = buyTicketFromAirCompanyWithBizRouter(airCompany, ticketCount);
            }

            // 返回结果
            return res;
        } catch (BizException t) {
            t.printStackTrace();
            return t.getErrorCode() + "," + t.getErrorMsg() + ";" + t.getMessage();
        } catch (Throwable t) {
            return t.getMessage();
        }
    }

    /**
     * http://127.0.0.1:8081/test/addons
     *
     * @return
     */
    @GetMapping("/addons")
    public String getAddon() {
        Map<String, Slot> addonMap = addonCollector.getAddonMap();
        return addonMap.toString();
    }

    private String buyTicketFromAirCompany(String airCompany, Integer ticketCount) {
        switch (airCompany) {
            case "CA":
                // invoke CA buy ticket api
                return "success and ticket info";
            case "CZ":
                // invoke CZ buy ticket api
                return "fail+error msg:无票";
            default:
                return "fail+error msg:不支持航司:" + airCompany;
        }
    }

    private String buyTicketFromAirCompanyWithBizRouter(String airCompany, Integer ticketCount) {
        AddonIdentification addonIdentification = new AddonIdentification();
        addonIdentification.setBizLineCode(BizLineEnum.TRIP.getCode());
        addonIdentification.setSlotCode(SlotEnum.BUY_AIR_TICKET.getCode());
        addonIdentification.setAddonCode(airCompany);
        RunAddonOption runAddonOption = new RunAddonOption();
        runAddonOption.setQuietIfNoAddonExist(false);
        Object res = addonRouter.routeThenExecute(addonIdentification, ticketCount, runAddonOption);
        return res.toString();
    }
}

```
