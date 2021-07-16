package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {
    private ApplicationUserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    public UserService(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }


    /**
     * Create application user application user.
     *
     * @param user the user
     * @return the application user
     */
    public ApplicationUser createApplicationUser(ApplicationUser user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<ApplicationUser> findAll() {
        return userRepository.findAll();
    }

    /**
     * Delete user.
     *
     * @param id the id
     */
    public void deleteUser(long id) { userRepository.deleteById(id); }

    /**
     * Update user application user.
     *
     * @param user the user
     * @return the application user
     */
    public ApplicationUser updateUser(ApplicationUser user) {
        return userRepository.save(user);
    }
}