package matyliano.dto;

import java.util.List;
import matyliano.enums.Role;

public class UserResponseDTO {


  private Long id;

  private String username;

  private String email;

  List<Role> roles;

}
