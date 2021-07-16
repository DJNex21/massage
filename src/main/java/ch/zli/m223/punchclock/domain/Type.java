package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Type.
 */
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "massageur_id", nullable = false)
    private List<Massageur> massageurs;


    /**
     * Instantiates a new Type.
     *
     * @param id   the id
     * @param name the name
     */
    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * Instantiates a new Type.
     */
    public Type() {

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
