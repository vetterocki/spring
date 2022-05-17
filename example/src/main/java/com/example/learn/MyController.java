package com.example.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private EntitRepository entitRepository;

    @GetMapping("/test")
    public List<Example> getShow() {
        Example example = new Example();
        example.setSeatList(List.of(new Something(1), new Something(2), new Something(3)));
        entitRepository.save(example);
        return entitRepository.findAll();

    }
    @GetMapping("/all")
    public List<Example> getAll() {
        return entitRepository.findAll();
    }
}
