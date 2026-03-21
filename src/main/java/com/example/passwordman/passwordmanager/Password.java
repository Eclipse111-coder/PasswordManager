package com.example.passwordman.passwordmanager;

import java.util.Random;

public class Password {

    private String password = "";
    private byte passwordStrength = checkStrengthOfPassword();
    private String passwordPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()\\!№;%:?*";
    private static final String U_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String L_POOL = "abcdefghijklmnopqrstuvwxyz";

    private final Random shift = new Random();

    public String caesarEncrypt(String needleText){
        int shiftInt = shift.nextInt(26);
        int pos = -1;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < needleText.length(); i++) {
            char c = needleText.charAt(i);
            if (Character.isUpperCase(needleText.charAt(i))) {
                pos = U_POOL.indexOf(c);
                if (pos != -1) {
                    pos = (pos + shiftInt) % 26;
                    if (pos < 0) pos += 26;
                    c = U_POOL.charAt(pos);
                    result.append(c);
                } else {
                    result.append(c);
                }
            } else if (Character.isLowerCase(needleText.charAt(i))) {
                pos = L_POOL.indexOf(c);
                if (pos != -1) {
                    pos = (pos + shiftInt) % 26;
                    if (pos < 0) pos += 26;
                    c = L_POOL.charAt(pos);
                    result.append(c);
                } else {
                    result.append(c);
                }
            }else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public byte checkStrengthOfPassword() {
        String specSym = "!@#$%^&*()\\№;%:?*";
        byte strength = 0;
        for (char c : specSym.toCharArray()) {
            if (password.indexOf(c) != -1){
                strength++;
                break;
            }
        }
        String tempNums = "1234567890";
        for (char c : tempNums.toCharArray()) {
            if (password.indexOf(c) != -1){
                strength++;
                break;
            }
        }
        if (password.length() > 8)
            strength++;
        return strength;
    }

    public char poolIndexChoose() {
        Random passwordPoolChoose = new Random();
        int index = passwordPoolChoose.nextInt(passwordPool.length());
        return passwordPool.charAt(index);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordPool(String passwordPool) {
        this.passwordPool = passwordPool;
    }

}
