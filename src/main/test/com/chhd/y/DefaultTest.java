package com.chhd.y;

import com.chhd.y.util.MD5Utils;
import com.chhd.y.util.PropertiesUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class DefaultTest {

    @Test
    public void main() {
        System.out.println("\n``````````````````");
        String salt = PropertiesUtils.getProperty("salt");
        String encode = MD5Utils.encode("123");
        System.out.println(salt + "\n" + encode);
        System.out.println("``````````````````");
    }
}
