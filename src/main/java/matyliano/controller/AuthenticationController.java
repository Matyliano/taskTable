package matyliano.controller;


import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import matyliano.dto.UserDTO;
import matyliano.dto.UserResponseDTO;
import matyliano.entity.User;
import matyliano.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController {


    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    public String signup( @RequestBody UserDTO user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @PostMapping("/signin")
    public String login(
                        @RequestParam String username,
                        @RequestParam String password) {
        return userService.signin(username, password);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponseDTO search(@PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }
}
