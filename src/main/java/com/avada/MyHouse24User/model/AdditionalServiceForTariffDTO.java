package com.avada.MyHouse24User.model;

import com.avada.MyHouse24User.entity.AdditionalService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceForTariffDTO {
    private AdditionalService additionalService;
    private Long price;
}

