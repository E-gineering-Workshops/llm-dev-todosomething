package com.eg.todosomething.home;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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
        mustache.execute(writer, Map.of("testList",testList));

        return writer.toString();
    }

    public Mono<ServerResponse> handleFormPost(ServerRequest request) {
        return request.formData()
                .flatMap(formData -> {
                    testList.add(formData.getFirst("item"));
                    return ServerResponse.ok().body(Mono.fromSupplier(this::list), String.class);
                });
    }
}
