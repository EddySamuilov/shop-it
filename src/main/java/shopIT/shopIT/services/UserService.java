package shopIT.shopIT.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopIT.shopIT.dtos.UserProfileDTO;
import shopIT.shopIT.dtos.UserRegisterDTO;
import shopIT.shopIT.enums.RoleName;
import shopIT.shopIT.mappers.UserMapper;
import shopIT.shopIT.models.AppUser;
import shopIT.shopIT.models.Role;
import shopIT.shopIT.models.User;
import shopIT.shopIT.repositories.RoleRepository;
import shopIT.shopIT.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found!";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        return new AppUser(
            user.getUsername(),
            user.getPassword(),
            getAuthorities(user)
        );
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, username)));
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
            .collect(Collectors.toList());
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User user = userMapper.toEntity(userRegisterDTO);

        user.setRoles(Set.of(getUserRole()));
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setImageURL("https://upload.wikimedia.org/wikipedia/en/thumb/3/33/Patrick_Star.svg/1200px-Patrick_Star.svg.png");

        userRepository.save(user);
    }

    // TODO: Optimize the following method
    private Role getUserRole() {
        return roleRepository.findAll()
            .stream()
            .filter(role -> role.getRoleName() == RoleName.ROLE_USER)
            .findAny()
            .orElseThrow();
    }
//
//    // TODO: Optimize the following method
//    private Role getUserRole() {
//        return roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow();
//    }

    public UserProfileDTO getUserProfile(String username) {
        User user = findByUsername(username);
        return userMapper.toDTO(user);
    }

    public void update(UserProfileDTO userProfileDTO) {
        User user = findByUsername(userProfileDTO.getUsername());

        user.setUsername(userProfileDTO.getUsername());
        user.setFirstName(userProfileDTO.getFirstName());
        user.setLastName(userProfileDTO.getLastName());
        user.setEmail(userProfileDTO.getEmail());
        user.setImageURL(userProfileDTO.getImageURL());
        user.setModified(LocalDateTime.now());

        userRepository.save(user);
    }
}
