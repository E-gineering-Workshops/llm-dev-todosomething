package com.eg.todosomething.home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataRedisTest
@ImportAutoConfiguration(exclude = RedisConfig.class)
@ContextConfiguration(classes = {EmbeddedRedisConfig.class})
public class ListControllerTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ListOperations<String, String> listOps;

    private static final String SOURCE_LIST_KEY = "sourceList";
    private static final String DESTINATION_LIST_KEY = "destinationList";

    @BeforeEach
    public void setUp() {
        listOps = redisTemplate.opsForList();
        listOps.getOperations().delete(SOURCE_LIST_KEY);
        listOps.getOperations().delete(DESTINATION_LIST_KEY);
        listOps.rightPushAll(SOURCE_LIST_KEY, "one", "two", "three");
    }

    @Test
    public void testRightPopAndLeftPush() {
        // Perform the rightPopAndLeftPush operation
        String poppedElement = listOps.rightPopAndLeftPush(SOURCE_LIST_KEY, DESTINATION_LIST_KEY);

        // Verify the element moved
        assertEquals("three", poppedElement);
        assertEquals("three", listOps.leftPop(DESTINATION_LIST_KEY));
        assertEquals(2, listOps.size(SOURCE_LIST_KEY));
        assertEquals(0, listOps.size(DESTINATION_LIST_KEY));
    }
}