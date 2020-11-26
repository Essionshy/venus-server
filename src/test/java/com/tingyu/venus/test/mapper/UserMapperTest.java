package com.tingyu.venus.test.mapper;

import com.tingyu.venus.VenusServerApplication;
import com.tingyu.venus.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author essionshy
 * @Create 2020/10/21 17:02
 * @Version venus-server
 */
@SpringBootTest(classes = {VenusServerApplication.class})
@ContextConfiguration
@WebAppConfiguration
public class UserMapperTest {

    @Autowired
    UserRepository userMapper;

    @Test
    void testInsert(){


    }
}
