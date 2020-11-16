package com.melon.sdk.streaming;

import com.melon.sdk.utils.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ParamGenerator {
    public String hasilEnc = "";

    public ParamGenerator(int i, int i2) throws Exception {
        byte[] bytes = ("songId=" + Integer.toString(i2) + "&userId=" + Integer.toString(i) + "&timestamp=" + GetTimeStamp()).getBytes();
        byte[] bytes2 = "indiMelonStmSvc!".getBytes();
        byte[] generateIv = generateIv();
        byte[] encrypt = new AES128CBC().encrypt(bytes, bytes2, generateIv);
        String hexStr = toHexStr(generateIv);
        String hexStr2 = toHexStr(encrypt);
        this.hasilEnc = hexStr + hexStr2;
    }

    public static String GetTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static byte[] generateIv() throws NoSuchAlgorithmException {
        byte[] bArr = new byte[16];
        SecureRandom.getInstance("SHA1PRNG").nextBytes(bArr);
        return bArr;
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String toHexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            byte b2 = b & Base64.EQUALS_SIGN_ENC;
            if (b2 <= 15) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(b2));
        }
        return stringBuffer.toString();
    }

    public String getHasilEnc() {
        return this.hasilEnc;
    }
}
