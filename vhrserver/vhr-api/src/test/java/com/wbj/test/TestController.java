package com.wbj.test;

import com.wbj.common.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 王兵杰
 * @date 2021/6/17
 */
@SpringBootTest
public class TestController {

    @Test
    void createPassword(){
        String salt = MD5Utils.getSalt();
        String s = MD5Utils.md5("123466" + salt);
        System.out.println(s);
        System.out.println(salt);
    }
}
