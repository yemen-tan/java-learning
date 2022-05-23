package com.simon;

import com.simon.entity.Users;
import com.simon.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JavaLearningApplicationTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void test01(){
        Users users = new Users();
        users.setId("1908017YR51G1XWH");
        List<Users> select = usersMapper.select(users);
        for(Users u : select){
            System.out.println(u.getNickname());
        }
    }

}