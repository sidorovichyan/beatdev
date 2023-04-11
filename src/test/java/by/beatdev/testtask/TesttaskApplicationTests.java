package by.beatdev.testtask;

import by.beatdev.testtask.dto.UserDTO;
import by.beatdev.testtask.entity.NetworkStatus;
import by.beatdev.testtask.entity.User;
import by.beatdev.testtask.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TesttaskApplicationTests {

    private User testUser = new User(0L,"John Doe","johndoe@example.com","https://example.com/image.jpg",NetworkStatus.Online);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createUserTest() throws Exception {
        when(userService.save(any(User.class))).thenReturn(testUser);
        mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testUser)))
                .andExpect(status().isCreated());

    }

    @Test
    public void getUserTest() throws Exception {
        when(userService.findById(any())).thenReturn(Optional.ofNullable(testUser));
        mockMvc.perform(get("/user/0"))
                .andExpect(status().isOk());
        
        Optional<User> userOptional = Optional.empty();
        when(userService.findById(any())).thenReturn(userOptional);
        mockMvc.perform(get("/user/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserStatusTest() throws Exception {
        when(userService.findById(any())).thenReturn(Optional.ofNullable(testUser));
        mockMvc.perform(put("/user/0/Online"))
                .andExpect(status().isOk());

        Optional<User> userOptional = Optional.empty();
        when(userService.findById(any())).thenReturn(userOptional);
        mockMvc.perform(put("/user/0/Offline"))
                .andExpect(status().isNotFound());
    }
}
