package algo.security;

import java.security.MessageDigest;

public class MD5Demo {

    public static void main(String[] args) {
        System.out.println(getMD5Code("message"));
    }


    public MD5Demo() {

    }

    public static String getMD5Code(String message) {
        String md5Str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(message.getBytes());
            md5Str = bytes2Hex(md5Bytes);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    private static String bytes2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int tmp;
        try {
            for (byte aByte : bytes) {
                tmp = aByte;
                if (tmp < 0) tmp += 256;
                if (tmp < 16) sb.append("0");
                sb.append(Integer.toHexString(tmp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}