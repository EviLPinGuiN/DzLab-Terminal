package com.dz.tele2.integration_tests;

import com.dz.tele2.entity.Client;
import com.dz.tele2.repository.ClientRepository;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static com.dz.tele2.config.DataSetsConst.*;


/**
 * Created by Alex on 29.09.15.
 */

@DatabaseSetup(CLIENT_DATASET)
public class ClientRepositoryIT extends BaseRepositoryIT{

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testClientCreation() {
        Client client = clientRepository.findOne(1L);
        assertThat(clientRepository.count()).isEqualTo(1);
        assertThat(client.getName()).isEqualTo("Alex Kel");
        assertThat(client.getAddress()).isEqualTo("Kazan");
        assertThat(client.getEmail()).isEqualTo("1906.kas@gmail.com");
        assertThat(client.getAltPhone()).isEqualTo("9274664585");
        assertThat(client.getPhoneNumbers()).isEmpty();
    }

    @Test
    public void testClientUpdating() {
        Client client = clientRepository.findOne(1L);
        client.setName("Alexander");
        clientRepository.save(client);
        client = clientRepository.findByName("Alexander");
        assertThat(client).isNotNull();
    }

    @Test
    public void testClientRemoving() {
        clientRepository.delete(1L);
        long clientsCount = clientRepository.count();
        assertThat(clientsCount).isZero();
    }
}
