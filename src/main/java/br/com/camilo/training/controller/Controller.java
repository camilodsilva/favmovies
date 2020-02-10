package br.com.camilo.training.controller;

import br.com.camilo.training.error.ResourceNotFoundException;
import br.com.camilo.training.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apis/controllers")
public class Controller {

    @GetMapping
    public String index() {
        return "------- Hello world";
    }

    @GetMapping(path = "hello", params = {"name"})
    public ResponseEntity<?> hello(@RequestParam final String name) {
        Student student = new Student(name);

        if (name.equals("hello"))
            throw new ResourceNotFoundException(String.format("Not found: %s", name));

        return new ResponseEntity<>(student, HttpStatus.OK);
        // return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping(path = "world")
    public String world() {
        return "blablabla";
    }
}
