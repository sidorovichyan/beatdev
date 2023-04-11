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
public class UserStatusDTO {
    private Long id;
    private NetworkStatus previousStatus;
    private NetworkStatus status;
}
