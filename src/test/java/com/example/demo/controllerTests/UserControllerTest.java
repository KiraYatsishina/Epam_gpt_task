package com.example.demo.controllerTests;

import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Alice");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Bob");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UserDto> users = userController.getAllUsers();

        // Assert
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).getName());
        assertEquals("Bob", users.get(1).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setName("Charlie");

        User user = new User();
        user.setId(1L);
        user.setName("Charlie");

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDto createdUser = userController.createUser(userDto);

        // Assert
        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        assertEquals("Charlie", createdUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
