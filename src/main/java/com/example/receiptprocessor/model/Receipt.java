package com.example.receiptprocessor.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;
import java.util.UUID;


@Data
@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Pattern(regexp = "^[\\w\\s\\-&]+$")
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="receipt_id")
    private List<Item> items;
    @Pattern(regexp = "^\\d+\\.\\d{2}$")
    private String total;

}
