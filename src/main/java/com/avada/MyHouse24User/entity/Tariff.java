package com.avada.MyHouse24User.entity;

import com.avada.MyHouse24User.model.AdditionalServiceForTariffDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tariff")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    @Column(name = "date_edit")
    private Date dateEdit;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TariffAdditionalService> tariffAdditionalService;


    public void setTariffAdditionalService(List<AdditionalServiceForTariffDTO> additionalServiceForTariffDTOS) {
        if (this.tariffAdditionalService != null) {
            this.tariffAdditionalService.clear();
        }else{
            this.tariffAdditionalService = new ArrayList<>();
        }
        for (AdditionalServiceForTariffDTO additionalService : additionalServiceForTariffDTOS) {
            TariffAdditionalService tariffAdditionalService = new TariffAdditionalService();
            tariffAdditionalService.setTariff(this);
            tariffAdditionalService.setPrice(additionalService.getPrice());
            tariffAdditionalService.setAdditionalService(additionalService.getAdditionalService());
            this.tariffAdditionalService.add(tariffAdditionalService);
        }
    }
}
