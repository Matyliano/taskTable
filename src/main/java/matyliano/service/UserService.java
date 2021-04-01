package matyliano.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import matyliano.configuration.security.JwtTokenUtil;
import matyliano.entity.User;
import matyliano.exception.exist.UserAlreadyExistException;
import matyliano.exception.notFound.NotEmptyException;
import matyliano.exception.notFound.TokenException;
import matyliano.exception.notFound.UserNotFoundException;
import matyliano.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;



    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " does not exist"));
    }

    public User getOneUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public User updateUser(Long id, User updateUser) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id " + id + " does not exist"));

        if (updateUser.getFirstName() != null) {
            user.setFirstName(updateUser.getFirstName());
            if (updateUser.getLastName() != null) {
                user.setLastName(updateUser.getLastName());
            }
            if (updateUser.getEmail() != null) {
                user.setEmail(updateUser.getEmail());
            }
            if(updateUser.getUsername() != null){
                user.setUsername(updateUser.getUsername());
            }
        }
        return userRepository.save(user);
    }
    
    public User save(User saveUser) {
        if (saveUser.getFirstName() == null || saveUser.getLastName() == null || saveUser.getEmail() == null) {
            throw new NotEmptyException("name");
        }
        List<User> userList = getAllUsers().stream()
                .filter(existUser->existUser.getUsername().equals(saveUser.getUsername())).collect(Collectors.toList());
        if (!userList.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        setUserPassword(saveUser);

        return userRepository.save(saveUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    private void setUserPassword(User user) {
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }



    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenUtil.createToken(username, userRepository.findByUsername(username).get().getRoles());
        } catch (AuthenticationException e) {
            throw new TokenException("Invalid username/password supplied");
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenUtil.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new UserAlreadyExistException("Username is already in use");
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public Optional<User> search(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("The user doesn't exist");
        }
        return user;
    }



}
