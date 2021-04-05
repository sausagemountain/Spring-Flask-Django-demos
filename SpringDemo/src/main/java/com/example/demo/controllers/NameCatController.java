package com.example.demo.controllers;

import com.example.demo.data.NameCat;
import com.example.demo.data.NameCatRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/demo")
public class NameCatController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final ObjectNode objects = new ObjectNode(objectMapper.getNodeFactory());

    private final NameCatRepo repo;

    public NameCatController(NameCatRepo repo) {
        this.repo = repo;
    }


    @GetMapping("/")
    public NameCat[] getAll() {
        List<NameCat> all = new LinkedList<>();
        repo.findAll().forEach(all::add);
        return all.toArray(new NameCat[0]);
    }

    @GetMapping("/{id}")
    public JsonNode getOne(@PathVariable(name = "id") String id) {
        Optional<NameCat> nameCat = repo.findById(id);
        if (nameCat.isPresent()) {
            return objectMapper.convertValue(nameCat.get(), ObjectNode.class);
        }
        var node = new ObjectNode(objectMapper.getNodeFactory());
        node.put("status", MessageFormat.format("no record with id {0}", id));
        return node;
    }

    @PostMapping("/")
    public NameCat create(@RequestBody NameCat element) {
        String id = UUID.randomUUID().toString();
        if (element.getId() == null) {
            element.setId(id);
        } else {
            id = element.getId();
        }
        repo.save(element);
        return repo.findById(id).get();
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public JsonNode modify(@PathVariable(name = "id") String id, @RequestBody NameCat modification) {
        Optional<NameCat> optionalNameCat = repo.findById(id);
        if (optionalNameCat.isEmpty()) {
            var node = new ObjectNode(objectMapper.getNodeFactory());
            node.put("status", MessageFormat.format("no record with id {0}", id));
            return node;
        }

        NameCat nameCat = optionalNameCat.get();
        if (modification.getName() != null) {
            nameCat.setName(modification.getName());
        }
        if (modification.getCat() != null) {
            nameCat.setCat(modification.getCat());
        }
        nameCat = repo.save(nameCat);
        return objectMapper.convertValue(nameCat, ObjectNode.class);
    }

    @DeleteMapping("/{id}")
    public JsonNode delete(@PathVariable(name = "id") String id) {
        Optional<NameCat> nameCat = repo.findById(id);
        var node = new ObjectNode(objectMapper.getNodeFactory());
        if (nameCat.isPresent()) {
            repo.deleteById(id);
            node.put("status", "success");
            return node;
        }
        node.put("status", MessageFormat.format("no record with id {0}", id));
        return node;
    }
}
