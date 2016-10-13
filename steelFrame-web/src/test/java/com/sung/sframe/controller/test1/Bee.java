package com.sung.sframe.controller.test1;

/**
 * 蜜蜂实现类
 * <p>
 * Created by sungang on 2016/9/21.
 */
public class Bee extends Insect implements Attack {

    private Attack attack;

    public Bee(int size, String color, Attack attack) {
        super(size, color);
        this.attack = attack;
    }


    public void move() {
        attack.move();
    }

    public void attack() {
        attack.attack();
    }

}
