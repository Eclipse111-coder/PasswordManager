package com.example.passwordman.passwordmanager;

import java.util.Random;

public class password {

    private String password = "";
    private byte passwordStrength = checkStrengthOfPassword();
    private String passwordPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()\\!№;%:?*";

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
