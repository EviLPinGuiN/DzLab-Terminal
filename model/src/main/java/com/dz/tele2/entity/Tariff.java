package com.dz.tele2.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Alex on 29.09.15.
 */
@Entity
@Table(name = "Tariffs")
public class Tariff implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<PhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TariffDetail> tariffDetails;

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

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Set<TariffDetail> getTariffDetails() {
        return tariffDetails;
    }

    public void setTariffDetails(Set<TariffDetail> tariffDetails) {
        this.tariffDetails = tariffDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        return id.equals(tariff.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
