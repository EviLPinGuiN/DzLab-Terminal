package com.dz.tele2.entity;

import javax.persistence.*;
import java.io.Serializable;

import com.dz.tele2.entity.enums.PhoneNumberType;

/**
 * Created by Alex on 29.09.15.
 */
@Entity
@Table(name = "Phone_number")
public class PhoneNumber implements Serializable{

    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tariff tariff;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public void setType(PhoneNumberType type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
