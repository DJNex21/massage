package ch.zli.m223.punchclock.domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;


/**
 * The type Role.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private List<ApplicationUser> user;


    /**
     * Instantiates a new Role.
     *
     * @param id   the id
     * @param name the name
     */
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new Role.
     */
    public Role() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
