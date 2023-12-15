package org.voice9.cc.util;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by caoliang on 2020/11/6
 */
public class RandomUtil {

    public static String getRandom(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
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

    public static Integer getNum(String coreUuid, Integer threadNum) {
        if (coreUuid == null) {
            return RandomUtils.nextInt(0, threadNum);
        }
        int hash = coreUuid.hashCode();
        return Math.abs(hash % threadNum);
    }


}
