package cn.topicstudy.multibizrouter.core;

/**
 * 业务系统通过实现Slot，创建addon
 *
 * @param <P>
 * @param <R>
 */
public interface Slot<P, R> {
    String bizLineCode();

    String slotCode();

    String addonCode();

    R run(P param);
}
