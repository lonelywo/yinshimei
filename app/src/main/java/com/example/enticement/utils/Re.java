package com.example.enticement.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;

/**
 * 正则匹配工具类
 *
 * @author Cloud
 * @date 2017/11/25
 */
public class Re {

    /**
     * 检查字符串中是否为标题
     *
     * @param text text
     * @return 标题
     */
    @Nullable
    public static String checkTitle(String text) {

        if (TextUtils.isEmpty(text)) {
            return null;
        }
//
//        if (SPUtils.getMineCopy().equals(text)) {
//            return null;
//        }

        if (hasUrl(text) && hasTaoWord(text)) {

            Pattern pattern1 = Pattern.compile("【我剁手都要买的宝贝（(.*?)），");
            Matcher matcher1 = pattern1.matcher(text);
            if (matcher1.find()) {
                return matcher1.group(1);
            }

            Pattern pattern4 = Pattern.compile("(.*?)\\n【在售价】");
            Matcher matcher4 = pattern4.matcher(text);
            if (matcher4.find()) {
                String s = matcher4.group(1);

                if (s.endsWith("【包邮】")) {
                    s = s.replace("【包邮】", "");
                }

                return s;
            }

            Pattern pattern5 = Pattern.compile("【这个#聚划算团购#宝贝不错:(.*?)\\(分享自");
            Matcher matcher5 = pattern5.matcher(text);
            if (matcher5.find()) {
                return matcher5.group(1);
            }

            Pattern pattern3 = Pattern.compile("即可查看此商品:【(.*?)】\\(未安装App点这里");
            Matcher matcher3 = pattern3.matcher(text);
            if (matcher3.find()) {
                return matcher3.group(1);
            }

            Pattern pattern2 = Pattern.compile("【(.*?)】http");
            Matcher matcher2 = pattern2.matcher(text);
            if (matcher2.find()) {
                return matcher2.group(1);
            }

            Pattern pattern6 = Pattern.compile("(.*?)【包邮】");
            Matcher matcher6 = pattern6.matcher(text);
            if (matcher6.find()) {
                return matcher6.group(1);
            }

        }

        if (hasTaoWord(text)) {

            Pattern pattern = Pattern.compile("【(.*?)】，");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }

        if (onlyTaoWord(text)) {
            return null;
        }

        if (hasUrl(text)) {
            return null;
        }

        if (isNumberAndLetter(text)) {
            return null;
        }

        if (text.length() > 20 && text.length() <= 60) {
            return text;
        }

        return null;
    }

    /**
     * 判断是否有Url
     *
     * @param text String
     * @return 结果
     */
    private static boolean hasUrl(String text) {
        Pattern pattern = Pattern.compile("[a-zA-z]+://[^\\s]*");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * 判断是仅仅是淘口令
     *
     * @param text String
     * @return 结果
     */
    private static boolean onlyTaoWord(String text) {
        Pattern pattern = Pattern.compile("^(《|￥|》|€)(.*?)(《|￥|》|€)$");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * 判断是否有淘口令
     *
     * @param text String
     * @return 结果
     */
    private static boolean hasTaoWord(String text) {
        Pattern pattern = Pattern.compile("(《|￥|》|€)(.*?)(《|￥|》|€)");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * 判断11位数字
     *
     * @param text String
     * @return 结果
     */
    public static boolean is11Number(String text) {
        Pattern r = Pattern.compile("^\\d{11}$");
        Matcher m = r.matcher(text);
        return m.find();
    }

    /**
     * 数字和字母
     *
     * @param text String
     * @return 结果
     */
    private static boolean isNumberAndLetter(String text) {
        Pattern r = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher m = r.matcher(text);
        return m.find();
    }

    /**
     * 纯字母
     *
     * @param text
     * @return
     */
    public static boolean isLetter(String text) {
        Pattern r = Pattern.compile("^[a-zA-Z]*$");
        Matcher m = r.matcher(text);
        return m.find();
    }


    /**
     * 判断是否是淘宝域名链接
     *
     * @param url
     * @return
     */
    public static boolean isTaoBaoUrl(String url) {
        return url.contains("taobao.com");
    }

    /**
     * 判断标题识别
     *
     * @param text
     * @return
     */
    public static String getTitle(String text) {
        Pattern pattern1 = Pattern.compile("\\[cp\\](.*?)\\[/cp\\]");
        Matcher matcher1 = pattern1.matcher(text);
        if (matcher1.find()) {
            return matcher1.group(1);
        }
        return null;
    }


    public static String getActivityId(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Pattern pattern = Pattern.compile("activity.*?d=(.*?)&");
        Matcher matcher = pattern.matcher(url + "&");
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
