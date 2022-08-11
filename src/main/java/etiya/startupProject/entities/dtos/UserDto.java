package etiya.startupProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private int id;

    @Size(min = 3)
    @NotBlank
    @NotNull
    private String firstName;

    @Size(min = 2)
    @NotBlank
    @NotNull
    private String lastName;

    @Email
    private String email;

    @Size(min = 4)
    @NotBlank
    @NotNull
    private String password;

}
