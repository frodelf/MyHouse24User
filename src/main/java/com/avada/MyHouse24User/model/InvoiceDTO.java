package com.avada.MyHouse24User.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class InvoiceDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String status;
}
