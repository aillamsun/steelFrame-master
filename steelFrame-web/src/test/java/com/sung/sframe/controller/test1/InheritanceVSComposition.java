package com.sung.sframe.controller.test1;

/**
 * Created by sungang on 2016/9/21.
 */
public class InheritanceVSComposition {

    public static void main(String[] args) {
        Bee a = new Bee(1, "black", new AttackImpl("fly", "move"));
        a.attack();


        Bee b = new Bee(1, "black", new AttackImpl("fly", "sting"));
        b.attack();
    }
}
