package com.hubery.exam;

import org.junit.Test;

import com.hubery.exam.domain.Person;
import com.hubery.exam.utils.CloneUtils;

public class CloneTest {

    @Test
    public void test() {
        Person father = new Person("father");

        Person elderBrother = new Person("elder", father);

        Person youngBrother = CloneUtils.clone(elderBrother);

        youngBrother.setName("younger");

        elderBrother.getFather().setName("adopted father");

        System.out.println(elderBrother.getName() + "`s father is " + elderBrother.getFather().getName());

        System.out.println(youngBrother.getName() + "`s father is " + youngBrother.getFather().getName());
    }

}
