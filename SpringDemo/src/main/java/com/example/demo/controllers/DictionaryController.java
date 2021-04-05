package com.example.demo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final ObjectNode objects = new ObjectNode(objectMapper.getNodeFactory());

    @GetMapping("/objects")
    public JsonNode all() {
        return objects;
    }

    @PostMapping("/objects/{id}")
    public JsonNode add(@PathVariable(name = "id") String id, @RequestBody ObjectNode requestData) {
        objects.set(id, requestData);
        var node = new ObjectNode(objectMapper.getNodeFactory());
        node.put("status", "ok");
        return node;
    }

    @GetMapping("/objects/{id}")
    public JsonNode getOne(@PathVariable(name = "id") String id) {
        return objects.get(id);
    }
}
