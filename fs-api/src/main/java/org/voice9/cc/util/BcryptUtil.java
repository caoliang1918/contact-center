package org.voice9.cc.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by caoliang on 2020/11/2
 */
public class BcryptUtil {

    private final static int ROUNDS = 4;

    /**
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        return BCrypt.hashpw(data, BCrypt.gensalt(ROUNDS));
    }

    /**
     * 验签
     *
     * @param decryptStr sha256
     * @param hashed
     * @return
     */
    public static boolean checkPwd(String decryptStr, String hashed) {
        return BCrypt.checkpw(decryptStr, hashed);
    }

}
