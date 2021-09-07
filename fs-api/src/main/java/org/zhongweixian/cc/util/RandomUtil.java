package org.zhongweixian.cc.util;

import java.util.List;
import java.util.Set;

/**
 * Created by caoliang on 2020/11/6
 */
public class RandomUtil {

    public static String getRandom(List<String> list) {
        int index = (int) (Math.random() * list.size());
        return list.get(index);
    }

    public static String getRandomKey(Set<String> set) {
        int index = (int) (Math.random() * set.size());
        int i = 0;
        for (String key : set) {
            if (i == index) {
                return key;
            }
            i++;
        }
        return null;
    }


}
