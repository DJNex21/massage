package ch.zli.m223.punchclock.domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;


@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private List<ApplicationUser> user;


    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
