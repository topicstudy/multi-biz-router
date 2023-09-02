package cn.topicstudy.multibizrouter.core.entity;

import lombok.Data;

/**
 * 插件标识
 * 定位到一个插件
 */
@Data
public class AddonIdentification {
    private String bizLineCode;
    private String slotCode;
    private String addonCode;
}
