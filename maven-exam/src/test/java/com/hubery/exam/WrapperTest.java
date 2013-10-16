package com.hubery.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.hubery.exam.domain.User;

public class WrapperTest {

    @Test
    public void TestWrapper() {
        List<User> users = new ArrayList<User>();
        User user = new User();
        user.setName("Hubery");
        users.add(null);
        printUserName(users);
    }

    private void printUserName(List<User> users) {
        String name;
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                if (null != user) {
                    name = user.getName();
                    System.out.println(name);
                } else {
                    System.out.println("user is null");
                }
            }
        }
    }

    @Test
    public void DataTest() {
        int i = 1;
        long l = (long) i;
        f(l);
    }

    private static void f(Long l) {

    }

    @Test
    public void RandomTest() {
        Random random = new Random(1000);
        for (int i = 0; i < 3; i++) {
            System.out.println(i + "," + random.nextInt());
        }
    }
}
