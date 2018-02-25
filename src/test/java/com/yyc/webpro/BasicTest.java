package com.yyc.webpro;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动加载ioc容器
 */

//用哪个类来跑
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junitspring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BasicTest {

}
