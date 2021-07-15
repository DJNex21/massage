package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "massageur_id", nullable = false)
    private List<Massageur> massageurs;



    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Type() {

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
