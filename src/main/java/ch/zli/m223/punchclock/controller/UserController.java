package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.UserService;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;
import org.h2.engine.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param applicationUserRepository the application user repository
     * @param bCryptPasswordEncoder     the b crypt password encoder
     * @param userService               the user service
     */
    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserService userService) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    /**
     * Sign up.
     *
     * @param user the user
     */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationUser> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Create application user application user.
     *
     * @param user the user
     * @return the application user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUser createApplicationUser(@Valid @RequestBody ApplicationUser user) {
        return userService.createApplicationUser(user);
    }

    /**
     * Delete user.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    /**
     * Update user application user.
     *
     * @param user the user
     * @return the application user
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationUser updateUser(@Valid @RequestBody ApplicationUser user) {
        return userService.updateUser(user);
    }
}
