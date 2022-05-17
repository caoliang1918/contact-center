package org.cti.cc.util;

import com.alibaba.fastjson.JSONObject;

import java.time.Instant;

public class LicenseUtil {

    /**
     * @param salt
     * @param key
     * @param agentNum
     * @param expires
     * @return
     */
    public static String generateLicense(String salt, String key, Integer agentNum, Long expires) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("salt", salt);
        jsonObject.put("agentNum", agentNum);
        jsonObject.put("expires", expires);
        return AesUtils.encrypt(jsonObject.toJSONString(), key);
    }

    /**
     * @param salt     平台唯一标识
     * @param content  加密密文
     * @param key      秘钥
     * @param agentNum 平台坐席数
     * @return
     */
    public static Boolean checkLicense(String salt, String content, String key, Integer agentNum) throws Exception {
        String decodeStr = AesUtils.decrypt(content, key);
        JSONObject jsonObject = JSONObject.parseObject(decodeStr);
        if (!salt.equals(jsonObject.get("salt"))) {
            return false;
        }
        Long time = Instant.now().getEpochSecond();
        if (time > jsonObject.getLong("expires")) {
            return false;
        }
        if (agentNum > jsonObject.getInteger("agentNum")) {
            return false;
        }
        return true;
    }

}
