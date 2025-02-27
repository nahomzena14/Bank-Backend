package com.example.backend.dto;

import com.example.backend.entity.User;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing user details.
 * This class is used to transfer non-sensitive user information, excluding the password.
 */
@Data
public class UserDTO {

    private Long user_id;
    private String userName;
    private String fullName;

    /**
     * Constructs a {@code UserDTO} from a {@link User} entity.
     *
     * @param user the user entity to be converted into a DTO
     */
    public UserDTO(User user) {
        this.user_id = user.getId();
        this.userName = user.getUsername();
        this.fullName = user.getFullName();
    }
}
