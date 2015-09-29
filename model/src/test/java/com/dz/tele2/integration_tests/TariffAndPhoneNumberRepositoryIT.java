package com.dz.tele2.integration_tests;

import com.dz.tele2.entity.PhoneNumber;
import com.dz.tele2.entity.Tariff;
import com.dz.tele2.repository.PhoneNumberRepository;
import com.dz.tele2.repository.TariffRepository;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.dz.tele2.config.DataSetsConst.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Alex on 30.09.15.
 */
@DatabaseSetup(TARIFF_DATASET)
public class TariffAndPhoneNumberRepositoryIT extends BaseRepositoryIT {

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Test
    public void testCreation() {
        Tariff tariff = tariffRepository.findOne(1L);
        assertThat(tariffRepository.count()).isEqualTo(1);
        assertThat(tariff.getName()).isEqualTo("First");
        Set<PhoneNumber> phoneNumberSet = tariff.getPhoneNumbers();
        assertThat(phoneNumberSet.size()).isEqualTo(1);
        Iterator<PhoneNumber> iterator = phoneNumberSet.iterator();
        PhoneNumber phoneNumber = null;
        if (iterator.hasNext()) {
            phoneNumber = iterator.next();
        }
        assertThat(phoneNumber).isNotNull();
        assertThat(phoneNumber.getNumber()).isEqualTo("9274664585");
        assertThat(phoneNumber.getTariff()).isEqualTo(tariff);
    }

    @Test
    public void testUpdating() {
        Tariff tariff = tariffRepository.findOne(1L);
        Set<PhoneNumber> phoneNumberSet = tariff.getPhoneNumbers();
        Iterator<PhoneNumber> iterator = phoneNumberSet.iterator();
        PhoneNumber phoneNumber = null;
        if (iterator.hasNext()) {
            phoneNumber = iterator.next();
        }
        assertThat(phoneNumber).isNotNull();
        phoneNumber.setNumber("111");
        phoneNumberRepository.save(phoneNumber);
        tariffRepository.save(tariff);

        tariff = tariffRepository.findOne(1L);
        phoneNumberSet = tariff.getPhoneNumbers();
        iterator = phoneNumberSet.iterator();
        phoneNumber = null;
        if (iterator.hasNext()) {
            phoneNumber = iterator.next();
        }
        assertThat(phoneNumber.getNumber()).isEqualTo("111");
    }

    @Test
    public void testRemoving() {
        Tariff tariff = tariffRepository.findOne(1L);
        tariff.setPhoneNumbers(new HashSet<>());
        PhoneNumber phoneNumber = phoneNumberRepository.findByTariff(tariff);
        phoneNumber.setTariff(null);
        phoneNumberRepository.save(phoneNumber);
        tariffRepository.delete(1L);
        long tariffsCount = tariffRepository.count();
        assertThat(tariffsCount).isZero();
        long phoneNumberCount = phoneNumberRepository.count();
        assertThat(phoneNumberCount).isEqualTo(1);
    }
}
