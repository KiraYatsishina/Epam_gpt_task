package com.example.demo.controller;

import com.example.demo.dto.TodoDto;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream().map(todo -> {
            TodoDto todoDto = new TodoDto();
            todoDto.setId(todo.getId());
            todoDto.setName(todo.getTitle());
            todoDto.setDescription(todo.getDescription());
            todoDto.setUserId(todo.getUser().getId());
            return todoDto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public TodoDto createTodo(@RequestBody TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setTitle(todoDto.getName());
        todo.setDescription(todoDto.getDescription());
        User user = new User();
        user.setId(todoDto.getUserId()); // Assuming user ID is provided in the request
        todo.setUser(user);
        todo = todoRepository.save(todo);

        todoDto.setId(todo.getId());
        return todoDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(todoDto.getName());
                    todo.setDescription(todoDto.getDescription());
                    todoRepository.save(todo);
                    todoDto.setId(todo.getId());
                    todoDto.setUserId(todo.getUser().getId());
                    return ResponseEntity.ok(todoDto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todoRepository.delete(todo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
