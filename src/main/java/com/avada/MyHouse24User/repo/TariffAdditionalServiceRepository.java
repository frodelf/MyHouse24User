package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Tariff;
import com.avada.MyHouse24User.entity.TariffAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TariffAdditionalServiceRepository extends JpaRepository<TariffAdditionalService, Long> {
    List<TariffAdditionalService> findAllByTariff(Tariff tariff);
    void deleteAllByTariff(Tariff tariff);
}
