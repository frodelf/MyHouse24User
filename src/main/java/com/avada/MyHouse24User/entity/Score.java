package com.avada.MyHouse24User.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "score")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = "flat")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private double balance;
    private String status;
    @Column(unique = true)
    private String number;
    @OneToOne(mappedBy = "score",cascade = CascadeType.DETACH)
    private Flat flat;
}
