package com.eg.todosomething.home;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Controller
public class ListController {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String TEST_LIST_KEY = "testList";
    private final Mustache mustache;

    // Constructor for ListController
    public ListController(MustacheFactory mustacheFactory, RedisTemplate<String, String> redisTemplate) {
        
        mustache = mustacheFactory.compile("mustachetemplates/list.mustache");
        this.redisTemplate = redisTemplate;

        // Initialize the list
        if (0 == redisTemplate.opsForList().size(TEST_LIST_KEY)) {
            redisTemplate.opsForList().rightPushAll(TEST_LIST_KEY, "one", "two", "three");
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public String list() {

        List<String> testList = redisTemplate.opsForList().range(TEST_LIST_KEY, 0, -1);
        StringWriter writer = new StringWriter();
        mustache.execute(writer, Map.of("testList", testList));
        return writer.toString();
    }

    // Create a method to add an item to the list
    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestParam String item) {
        redisTemplate.opsForList().rightPush(TEST_LIST_KEY, item);
        return list();
    }

    // Create a method to delete an item from the list
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam String item) {
        redisTemplate.opsForList().remove(TEST_LIST_KEY, 1, item);
        return list();
    }

    @DeleteMapping("/deleteall")
    @ResponseBody
    public String deleteAll() {
        redisTemplate.delete(TEST_LIST_KEY);
        return list();
    }

}