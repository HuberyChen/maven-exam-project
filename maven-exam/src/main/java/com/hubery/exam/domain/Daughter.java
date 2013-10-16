package com.hubery.exam.domain;

public class Daughter extends MotherImpl implements Father {

    @Override
    public int kind() {
        return super.kind() + 2;
    }

    public int strong() {
        return new FatherImpl() {
            @Override
            public int strong() {
                return super.strong() - 2;
            }
        }.strong();
    }
}
