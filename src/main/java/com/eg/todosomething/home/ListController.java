package com.eg.todosomething.home;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ListController {
    private final MustacheFactory mustacheFactory;

    List<String> testList = new ArrayList<>();
    private final Mustache mustache;

    // Constructor for ListController
    public ListController(MustacheFactory mustacheFactory) {
        this.mustacheFactory = mustacheFactory;
        mustache = mustacheFactory.compile("mustachetemplates/list.mustache");

        testList.addAll(List.of("one", "two", "three"));
    }

    @GetMapping("/list")
    @ResponseBody
    public String list() {

        StringWriter writer = new StringWriter();
        mustache.execute(writer, Map.of("testList", testList));

        return writer.toString();
    }

    // Create a method to add an item to the list
    @PostMapping("/add")
    @ResponseBody
    public String add(@RequestParam String item) {
        testList.add(item);
        return list();
    }

    // Create a method to delete an item from the list
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam String item) {
        testList.remove(item);
        return list();
    }

}