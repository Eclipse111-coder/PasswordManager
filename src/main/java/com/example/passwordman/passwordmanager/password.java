package com.example.passwordman.passwordmanager;

import java.util.ArrayList;
import java.util.Random;

public class password {

    private String password = "";
    private byte passwordStrength = checkStrengthOfPassword();
    private String passwordPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()\\!№;%:?*";
    // it`s an alphabet in upper case
    private char[] passwordUPool = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    // it`s an alphabet in lower case
    private char[] passwordLPool = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public String caesarEncrypt(String needleText){
        byte shift = 8;
        int pos = -1;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < needleText.length(); i++) {
            char c = needleText.charAt(i);
            if (Character.isUpperCase(needleText.charAt(i))) {
                for (int j = 0; j < passwordUPool.length; j++){
                    if (passwordUPool[j] == c) {
                        pos = j;
                        break;
                    }
                }
                if (pos != -1) {
                    pos = (pos + shift) % 26;
                    if (pos < 0) pos += 26;
                    c = passwordUPool[pos];
                    result.append(c);
                } else {
                    result.append(c);
                }
                c = passwordUPool[pos];
            } else if (Character.isLowerCase(needleText.charAt(i))) {
                for (byte j = 0; j < passwordLPool.length; j++) {
                    if (passwordLPool[j] == c) {
                        pos = j;
                        break;
                    }
                }
                pos += shift;
                if (pos != -1) {
                    pos = (pos + shift) % 26;
                    if (pos < 0) pos += 26;
                    c = passwordLPool[pos];
                    result.append(c);
                } else {
                    result.append(c);
                }
                c = passwordLPool[pos];
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getPasswordStrength() {
        return passwordStrength;
    }

    public void setPasswordStrength(byte passwordStrength) {
        this.passwordStrength = passwordStrength;
    }

    public String getPasswordPool() {
        return passwordPool;
    }

    public void setPasswordPool(String passwordPool) {
        this.passwordPool = passwordPool;
    }
}
