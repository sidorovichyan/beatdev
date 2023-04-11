package by.beatdev.testtask.dto;

import by.beatdev.testtask.entity.NetworkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String imageURI;
    private NetworkStatus status;
}
