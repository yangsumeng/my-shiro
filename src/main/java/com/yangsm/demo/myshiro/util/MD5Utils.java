package com.yangsm.demo.myshiro.util;

//import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//@Slf4j
public class MD5Utils {
//    public static void main(String[] args) throws IOException {
//
//        File file = new File("D:/game/123.apk");
//        String md5 = getFileMD5String(file);
//
//
//        System.out.println("MD5:"+md5);
//        System.out.println("Linux: 命令md5校验码是 4988114592d45ff34560fdf9b859d789  testremote.zip");
//        long end = System.currentTimeMillis();
//
//    }

    /*
     * {"data":"{\"user_info\":{\"systemid\":81951128310,\"user_name\":\"demo11\",\"grade\":1,\"teaching_version\":\"\\u4eba\\u6559\\u7248\"},\"k_info\":{\"k_id\":819,\"k_name\":\"demo-k\",\"k_subject\":\"subject\"},\"microcourse_info\":{\"microcourse_id\":8310,\"microcourse_name\":\"\\u4e00\\u5143\\u4e8c\\u6b21\\u65b9\\u7a0b\",\"duration\":600},\"log_time\":1580523750,\"channel\":1,\"log_type\":1}"
     * ,
     * "token":"55e62c92aa77f25cf5d29044251ec49c"}
     *
     */
//    public static void main(String[] args) {
//        String data = "{\"user_info\":{\"systemid\":81951128310,\"user_name\":\"demo11\",\"grade\":1,\"teaching_version\":\"\\u4eba\\u6559\\u7248\"},\"k_info\":{\"k_id\":819,\"k_name\":\"demo-k\",\"k_subject\":\"subject\"},\"microcourse_info\":{\"microcourse_id\":8310,\"microcourse_name\":\"\\u4e00\\u5143\\u4e8c\\u6b21\\u65b9\\u7a0b\",\"duration\":600},\"log_time\":1580523750,\"channel\":1}";
//        //"[data:data值]LogReport@1QAZXSW2"
//        String text ="[data:"+data+"]LogReport@1QAZXSW2";
//        System.out.println(encrypt(text));
//        System.out.println("a5dc27062ed272ae858a28f141ffd3f0");


//    }


    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    protected static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
//            log.error("MD5:",nsaex);
        }
    }

    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }
        fis.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String encrypt(String text) {
        return DigestUtils.md5Hex(text);
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}
