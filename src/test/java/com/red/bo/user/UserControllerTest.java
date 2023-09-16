package com.red.bo.user;

import com.red.bo.core.user.User;
import com.red.bo.core.user.UserService;
import com.red.bo.web.user.UserController;
import com.red.bo.web.user.mapper.UserDTO;
import com.red.bo.web.user.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCurrentUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        var userDTO = UserDTO.builder().id(1L).username("testuser").build();

        when(userService.getAllUsers()).thenReturn(List.of(user));
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);

        ResponseEntity<UserDTO> responseEntity = userController.getCurrentUser();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(userDTO.getId(), responseEntity.getBody().getId());
        assertEquals(userDTO.getUsername(), responseEntity.getBody().getUsername());

        verify(userService, times(1)).getAllUsers();
        verify(userMapper, times(1)).toUserDTO(user);
    }

    @Test
    public void testDeleteUser_Success() {
        var userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = userController.deleteContact(userId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    public void testDeleteUser_NotFound() {
        var userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(false);

        ResponseEntity<Void> responseEntity = userController.deleteContact(userId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }
}
