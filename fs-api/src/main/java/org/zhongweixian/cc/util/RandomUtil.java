package org.zhongweixian.cc.util;

import java.util.List;

/**
 * Created by caoliang on 2020/11/6
 */
public class RandomUtil {

    public static String getRandom(List<String> list) {
        int index = (int) (Math.random() * list.size());
        return list.get(index);
    }
}
