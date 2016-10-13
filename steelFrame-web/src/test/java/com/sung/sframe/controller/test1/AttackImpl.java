package com.sung.sframe.controller.test1;

/**
 * Created by sungang on 2016/9/21.
 */
public class AttackImpl implements Attack {

    private String move;
    private String attack;

    public AttackImpl(String move, String attack) {
        this.move = move;
        this.attack = attack;
    }

    @Override
    public void move() {
        System.out.println(move);
    }

    @Override
    public void attack() {
        move();
        System.out.println(attack);
    }
}
