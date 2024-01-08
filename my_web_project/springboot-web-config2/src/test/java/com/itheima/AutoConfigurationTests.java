package com.itheima;


import com.example.HeaderGenerator;
import com.example.HeaderParser;
import com.example.TokenParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class AutoConfigurationTests {

    @Autowired
    private ApplicationContext applicationContext;

    //获取tokenparser
    @Test
    public void testTokenParser(){
        System.out.println(applicationContext.getBean(TokenParser.class));
    }

    //获取headerparser
    @Test
    public void testHeaderParser(){
        System.out.println(applicationContext.getBean(HeaderParser.class));
    }

    //获取headergenerator
    @Test
    public void testHeaderGenerator(){
        System.out.println(applicationContext.getBean(HeaderGenerator.class));
    }
}
