package com.hubery.exam;

import org.junit.Test;

import com.hubery.exam.domain.Daughter;
import com.hubery.exam.domain.Parent;

public class ConstructionTest {

    @Test
    public void inheritanceTest() {
        // Son son = new Son();

        Parent parent1 = new Parent();

        Parent parent2 = new Parent() {
            {
                setIsParent(true);
            }
        };
        parent1.getResult();
        parent2.getResult();
    }

    @Test
    public void multipleInheritanceTest() {
        Daughter daughter = new Daughter();
        System.out.println("strong:" + daughter.strong());
        System.out.println("king:" + daughter.kind());
    }

}
