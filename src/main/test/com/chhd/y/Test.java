package com.chhd.y;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class Test {

    @org.junit.Test
    public void main() {
        System.out.println("````````````````````````````1");
        System.out.println("````````````````````````````2");
    }
}
