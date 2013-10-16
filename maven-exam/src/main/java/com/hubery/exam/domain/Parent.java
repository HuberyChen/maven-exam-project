package com.hubery.exam.domain;

public class Parent {

    private boolean isParent;

    public Parent() {
        // System.out.println("I am parent!");
    }

    protected void setIsParent(boolean confirm) {
        isParent = confirm;
    }

    public void getResult() {
        if (isParent) {
            System.out.println("true!");
        } else {
            System.out.println("false!");
        }
    }

}
