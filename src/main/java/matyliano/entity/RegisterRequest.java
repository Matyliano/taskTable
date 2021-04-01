package matyliano.entity;

import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String userName;

    @Email
    private String email;
    private String password;
}
