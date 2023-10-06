package com.avada.MyHouse24User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(length = 1000)
    private String text;
    private String fromName;
    private Long fromId;
    private Boolean isUser;
    private LocalDate date;
    @ManyToOne
    private House house;
}
