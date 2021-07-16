package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.ApplicationUser;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Application user repository.
 */
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    /**
     * Find by username application user.
     *
     * @param username the username
     * @return the application user
     */
    ApplicationUser findByUsername(String username);
}