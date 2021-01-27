package com.imooc.sell;

import com.imooc.sell.entity.User;
import com.imooc.sell.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class SellApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void listTest() {
        List<User> users = userMapper.selectList(null);
        log.info("{}",users);
    }

    @Test
    void insertTest(){
        User user = new User();
        user.setAge(12);
        user.setEmail("789771@qq.com");
        user.setName("王五");
        userMapper.insert(user);
    }
    @Test
    void updateTest(){
        User user = userMapper.selectById(1238085091876478978L);
        user.setAge(111);
        user.setEmail("789771@qq.com");
        user.setName("测试乐观锁失败");
        user.setId(1238085091876478978L);;
        userMapper.updateById(user);
    }
    @Test
    void deleteTest(){
        userMapper.deleteById(1238085091876478978L);
    }
}
