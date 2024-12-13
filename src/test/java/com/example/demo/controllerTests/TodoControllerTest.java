package com.example.demo.controllerTests;

import com.example.demo.controller.TodoController;
import com.example.demo.dto.TodoDto;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoControllerTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTodos() {
        // Arrange
        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setTitle("Test Todo 1");
        todo1.setDescription("Description 1");
        User user1 = new User();
        user1.setId(1L);
        todo1.setUser(user1);

        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setTitle("Test Todo 2");
        todo2.setDescription("Description 2");
        User user2 = new User();
        user2.setId(2L);
        todo2.setUser(user2);

        when(todoRepository.findAll()).thenReturn(Arrays.asList(todo1, todo2));

        // Act
        List<TodoDto> todos = todoController.getAllTodos();

        // Assert
        assertNotNull(todos);
        assertEquals(2, todos.size());
        assertEquals("Test Todo 1", todos.get(0).getName());
        assertEquals(1L, todos.get(0).getUserId());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void testCreateTodo() {
        // Arrange
        TodoDto todoDto = new TodoDto();
        todoDto.setName("New Todo");
        todoDto.setDescription("New Description");
        todoDto.setUserId(1L);

        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("New Todo");
        todo.setDescription("New Description");
        User user = new User();
        user.setId(1L);
        todo.setUser(user);

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        // Act
        TodoDto createdTodo = todoController.createTodo(todoDto);

        // Assert
        assertNotNull(createdTodo);
        assertEquals(1L, createdTodo.getId());
        assertEquals("New Todo", createdTodo.getName());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void testUpdateTodo() {
        // Arrange
        Todo existingTodo = new Todo();
        existingTodo.setId(1L);
        existingTodo.setTitle("Old Todo");
        existingTodo.setDescription("Old Description");
        User user = new User();
        user.setId(1L);
        existingTodo.setUser(user);

        TodoDto todoDto = new TodoDto();
        todoDto.setName("Updated Todo");
        todoDto.setDescription("Updated Description");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(existingTodo);

        // Act
        ResponseEntity<TodoDto> response = todoController.updateTodo(1L, todoDto);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Todo", response.getBody().getName());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, times(1)).save(existingTodo);
    }

    @Test
    void testDeleteTodo() {
        // Arrange
        Todo todo = new Todo();
        todo.setId(1L);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        doNothing().when(todoRepository).delete(todo);

        // Act
        ResponseEntity<?> response = todoController.deleteTodo(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, times(1)).delete(todo);
    }

    @Test
    void testDeleteTodo_NotFound() {
        // Arrange
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = todoController.deleteTodo(1L);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, never()).delete(any(Todo.class));
    }
}

