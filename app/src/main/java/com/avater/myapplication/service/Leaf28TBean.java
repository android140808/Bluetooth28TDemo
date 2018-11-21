package com.avater.myapplication.service;

import java.util.Arrays;

/**
 * Created by Avatar on 2018/8/14.
 */

public class Leaf28TBean {
    private String name;
    private int grade;
    private byte[] commandsCode;
    private String tips;

    public Leaf28TBean() {
    }

    public Leaf28TBean(String name, int grade, byte[] commandsCode, String tips) {
        this.name = name;
        this.grade = grade;
        this.commandsCode = commandsCode;
        this.tips = tips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public byte[] getCommandsCode() {
        return commandsCode;
    }

    public void setCommandsCode(byte[] commandsCode) {
        this.commandsCode = commandsCode;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public void sendDatatoDevice() {

    }

    @Override
    public String toString() {
        return "Leaf28TBean{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", commandsCode=" + Arrays.toString(commandsCode) +
                ", tips='" + tips + '\'' +
                '}';
    }
}
