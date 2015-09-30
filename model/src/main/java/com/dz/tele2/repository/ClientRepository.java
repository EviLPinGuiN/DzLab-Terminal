package com.dz.tele2.repository;

import com.dz.tele2.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex on 29.09.15.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);
}
