package com.eg.todosomething.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public Mono<String> sayHello(@RequestParam String name) {
        return Mono.just(String.format("Hello, %s!", name));
    }
}
