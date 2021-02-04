package com.example.distributedlock;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "RESERVATION")
@Entity
public class Order {
    @Id
    @SequenceGenerators(@SequenceGenerator(name = "ORDER_ID", sequenceName = "ORDER_ID_SEQUENCE"))
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_SEQUENCE")
    Long id;
    String name;
}
