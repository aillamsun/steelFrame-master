package com.sung.sframe.controller.test1;

/**
 * 名为Insect（昆虫）的类，这个类包含两个方法：
 * 1）移动 move()；
 * 2）攻击 attack()。
 * <p>
 * Created by sungang on 2016/9/21.
 */
public class Insect {

    private int size;
    private String color;

    public Insect(int size, String color) {
        this.size = size;
        this.color = color;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
