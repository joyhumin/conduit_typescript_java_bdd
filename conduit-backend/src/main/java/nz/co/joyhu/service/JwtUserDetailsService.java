package nz.co.joyhu.service;

import nz.co.joyhu.api.v1.NewUser;
import nz.co.joyhu.api.v1.User;
import nz.co.joyhu.model.UserEntity;
import nz.co.joyhu.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JwtUserDetailsService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByUsername(username);
        // UserDetails only reaquire username,
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public void save(NewUser newUser) { 
        final UserEntity user = new UserEntity();
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        final UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        final User existUser = new User();
        existUser.setUsername(user.getUsername());
        existUser.setBio(user.getBio());
        existUser.setImage(user.getImage());
        existUser.setEmail(user.getEmail());
        return existUser;
    }
}
