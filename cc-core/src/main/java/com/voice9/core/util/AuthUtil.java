package com.voice9.core.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoliang on 2022/1/18
 */
public class AuthUtil {

    /**
     * 生成token
     *
     * @param key
     * @param id
     * @param secretKey
     * @return
     */
    public static String createToken(String key, Long id, String secretKey) {
        Long time = Instant.now().getEpochSecond();
        Map<String, Object> params = new HashMap<>(4);
        params.put("key", key);
        params.put("id", id);
        params.put("time", time);
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, secretKey).hmacHex(JSON.toJSONString(params));
    }
}
