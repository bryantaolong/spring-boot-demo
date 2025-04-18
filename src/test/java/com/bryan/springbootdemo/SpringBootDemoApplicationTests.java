package com.bryan.springbootdemo;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.bryan.springbootdemo.utils.redis.RedisHashUtil;
import com.bryan.springbootdemo.test.mapstruct.UserInfoMapper;
import com.bryan.springbootdemo.test.mapstruct.UserInfo;
import com.bryan.springbootdemo.test.mongodb.Posts;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBootDemoApplicationTests {

    @Autowired
    private RedisHashUtil redisHashUtil;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Qualifier("mongoTemplate")
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    void contextLoads() {

//        testMongoDB();
//        java14FeatureTest();
//        userConverterTest();
//        redisHashOperationTest();
    }

    @Test
    void testMongoDB() {
        // 准备查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is("Post Title 1"));

        // 执行查询
        List<Posts> resultList = mongoOperations.find(query, Posts.class);

        // 添加断言
        assertNotNull(resultList, "Result list should not be null");
        assertTrue(resultList.size() > 0, "Result list should contain elements");

        System.out.println(resultList);
    }

    @Test
    void java14FeatureTest() {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String day = switch (dayOfWeek) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> null;
        };
        System.out.println(day);
    }

    @Test
    void userConverterTest() {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("bryan");
            userInfo.setPassword("123456");
            userInfo.setEmail("bryan@gmail.com");

            UserInfoMapper userInfoMapper = Mappers.getMapper(UserInfoMapper.class);

            System.out.println(userInfoMapper.userInfoToUserInfoDTO(userInfo));
    }

    @Test
    void elasticsearchClientConnectionTest() throws IOException {
        System.out.println(elasticsearchTemplate.matchAllQuery());
    }

    @Test
    void redisHashOperationTest() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "test1");
        map.put("2", "test2");
        boolean isSuccess =  redisHashUtil.set("test", map);
        System.out.println(isSuccess);
    }

}
