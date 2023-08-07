package com.avada.MyHouse24User.model;

import com.avada.MyHouse24User.entity.Flat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MasterRequestDTO {
    private Long id;
    private String role;
    private Flat flat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern = "HH:mm")
    private Date time;
    private String description;
    private String status;
}
