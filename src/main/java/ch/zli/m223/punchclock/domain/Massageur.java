package ch.zli.m223.punchclock.domain;

import javax.persistence.*;

@Entity
public class Massageur extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Type massageType;

    public Type getMassageType() {
        return massageType;
    }

    public void setMassageType(Type massageType) {
        this.massageType = massageType;
    }
}
