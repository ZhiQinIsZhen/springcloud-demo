package com.liyz.cloud.service.member.util;

import com.liyz.cloud.common.model.constant.member.MemberServiceCodeEnum;
import com.liyz.cloud.common.model.exception.mmeber.RemoteMemberServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.liyz.cloud.common.model.constant.common.CommonConstant.EMAILREG;
import static com.liyz.cloud.common.model.constant.common.CommonConstant.PHONEREG;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/12 10:06
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberUtil {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 匹配手机
     *
     * @param mobile
     * @return
     */
    public static boolean matchMobile(String mobile) {
        Pattern p = Pattern.compile(PHONEREG);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 匹配邮箱
     *
     * @param email
     * @return
     */
    public static boolean matchEmail(String email) {
        Pattern p = Pattern.compile(EMAILREG);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 获取随机数字
     *
     * @param length 随机数长度
     * @return
     */
    public static String randomInteger(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 校验地址是否是邮件或者手机号码格式，如果不是，则抛出异常
     *
     * @param address
     * @param codeEnum
     * @return 1：手机号码；2：邮件
     */
    public static int checkMobileEmail(String address, MemberServiceCodeEnum codeEnum) {
        int type;
        if (MemberUtil.matchMobile(address)) {
            type = 1;
        } else if (MemberUtil.matchEmail(address)){
            type = 2;
        } else {
            throw new RemoteMemberServiceException(codeEnum);
        }
        return type;
    }

    /**
     * 利用MD5进行加密
     *
     * @param plainText 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String encodeMD5(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        // 16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
