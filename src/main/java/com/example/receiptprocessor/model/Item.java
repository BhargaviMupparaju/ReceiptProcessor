package com.example.receiptprocessor.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Pattern(regexp = "^[\\w\\s\\-]+$")
    private String shortDescription;
    @Pattern(regexp = "^\\d+\\.\\d{2}$")
    private String price;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;



}
