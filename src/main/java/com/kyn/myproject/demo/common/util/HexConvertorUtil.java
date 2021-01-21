package com.kyn.myproject.demo.common.util;

/**
 * @author Kangyanan
 * @Description: 进制转换器
 * @date 2021/1/21 14:24
 */
public class HexConvertorUtil {

    /**
     * @Description 转换任意进制
     *
     * @Params
     * @Return
     * @Exceptions
     */
    public static String transform(long num, int hex) {
        //提高效率10进制直接返回；
        if (hex == 10){
            return String.valueOf(num);
        }
        long array[] = new long[100];
        int location = 0;
        while (num != 0) {
            long remainder = num % hex;
            num = num / hex;
            array[location] = remainder;
            location++;
        }
        return convert2String(array,location-1);
    }

    /**
     * @Description 生成string
     *
     * @Params
     * @Return
     * @Exceptions
     */
    private static String convert2String(long[] arr, int hex) {
        StringBuffer buffer = new StringBuffer();
        for (int i = hex; i >= 0; i--) {
            if (arr[i] > 9&&arr[i]<36) {
                buffer.append((char) (arr[i] + 55));
            } else if (arr[i]>=36){
                buffer.append((char) (arr[i] + 61));
            }else {
                buffer.append(arr[i] + "");
            }
        }
        return buffer.toString();
    }

    /**
     * 将byte[] 转换成16进制
     */
    public static String byte2Hex(byte[] srcBytes) {
        StringBuilder hexRetSB = new StringBuilder();
        for (byte b : srcBytes) {
            String hexString = Integer.toHexString(0x00ff & b);
            hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
        }
        return hexRetSB.toString();
    }

    /**
     * 将16进制字符串转为转换成字符串
     */
    public static byte[] hex2Bytes(String source) {
        byte[] sourceBytes = new byte[source.length() / 2];
        for (int i = 0; i < sourceBytes.length; i++) {
            sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
        }
        return sourceBytes;
    }

    /**
     * 将十六进制数据转换成ASCII字符串
     * @param data_in  十六进制字符串
     */
    public static String Hex2Ascii(byte[] data_in) {
        int len=data_in.length;
        byte data_out[]=new byte[len * 2];
        byte temp1[] = new byte[1];
        byte temp2[] = new byte[1];
        for (int i = 0, j = 0; i < len; i++) {
            temp1[0] = data_in[i];
            temp1[0] = (byte) (temp1[0] >>> 4);
            temp1[0] = (byte) (temp1[0] & 0x0f);
            temp2[0] = data_in[i];
            temp2[0] = (byte) (temp2[0] & 0x0f);
            if (temp1[0] >= 0x00 && temp1[0] <= 0x09) {
                (data_out[j]) = (byte) (temp1[0] + '0');
            } else if (temp1[0] >= 0x0a && temp1[0] <= 0x0f) {
                (data_out[j]) = (byte) (temp1[0] + 0x57);
            }
            if (temp2[0] >= 0x00 && temp2[0] <= 0x09) {
                (data_out[j + 1]) = (byte) (temp2[0] + '0');
            } else if (temp2[0] >= 0x0a && temp2[0] <= 0x0f) {
                (data_out[j + 1]) = (byte) (temp2[0] + 0x57);
            }
            j += 2;
        }
        return new String(data_out);
    }

    /**
     * 将ASCII字符串转换成十六进制数据
     * @param len ASCII字符串长度
     * @param data_in 待转换的ASCII字符串
     * @param data_out 已转换的十六进制数据
     */
    public static void Ascii2Hex(int len, byte[] data_in, byte[] data_out) {
        byte temp1[] = new byte[1];
        byte temp2[] = new byte[1];
        for (int i = 0, j = 0; i < len; j++) {
            temp1[0] = data_in[i];
            temp2[0] = data_in[i + 1];
            if (temp1[0] >= '0' && temp1[0] <= '9') {
                temp1[0] -= '0';
                temp1[0] = (byte) (temp1[0] << 4);
                temp1[0] = (byte) (temp1[0] & 0xf0);
            } else if (temp1[0] >= 'a' && temp1[0] <= 'f') {
                temp1[0] -= 0x57;
                temp1[0] = (byte) (temp1[0] << 4);
                temp1[0] = (byte) (temp1[0] & 0xf0);
            }
            if (temp2[0] >= '0' && temp2[0] <= '9') {
                temp2[0] -= '0';
                temp2[0] = (byte) (temp2[0] & 0x0f);
            } else if (temp2[0] >= 'a' && temp2[0] <= 'f') {
                temp2[0] -= 0x57;
                temp2[0] = (byte) (temp2[0] & 0x0f);
            }
            data_out[j] = (byte) (temp1[0] | temp2[0]);
            i += 2;
        }
    }
}
