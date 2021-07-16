package ch.zli.m223.punchclock.domain;

import javax.persistence.*;

/**
 * The type Massageur.
 */
@Entity
public class Massageur extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Type massageType;

    /**
     * Gets massage type.
     *
     * @return the massage type
     */
    public Type getMassageType() {
        return massageType;
    }

    /**
     * Sets massage type.
     *
     * @param massageType the massage type
     */
    public void setMassageType(Type massageType) {
        this.massageType = massageType;
    }
}
