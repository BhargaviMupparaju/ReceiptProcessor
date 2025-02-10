package com.example.receiptprocessor;

import com.example.receiptprocessor.controller.ReceiptNotFoundException;
import com.example.receiptprocessor.model.Item;
import com.example.receiptprocessor.model.Receipt;
import com.example.receiptprocessor.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;


    public String generateProcessReceipt(Receipt receipt){
        Receipt saveReceipt= receiptRepository.save(receipt);
        return saveReceipt.getId().toString();
    }

    public int getPoints(String id){
        Receipt receipt = receiptRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ReceiptNotFoundException("Receipt not found exception:"+id));

        if(receipt==null){
            throw new IllegalArgumentException("No receipt is found with the ID");
        }

        int receiptPoints=0;

        receiptPoints=receiptPoints+ receipt.getRetailer().replaceAll("[^a-zA-Z0-9]","").length();

        double total= Double.parseDouble(receipt.getTotal());
        if(total== Math.floor(total)){
            receiptPoints+=50;
        }

        if(total%0.25==0){
            receiptPoints+=25;
        }

        receiptPoints+= (receipt.getItems().size()/2) * 5;


        for(Item item: receipt.getItems()){
            String itemDescription= item.getShortDescription().trim();

            if(itemDescription.length()%3==0){
                Double itemPrice= Double.parseDouble(item.getPrice());
                receiptPoints+= Math.round(itemPrice)*0.2;

            }
        }

        LocalDate purchaseDate= LocalDate.parse(receipt.getPurchaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int day= purchaseDate.getDayOfMonth();
        if(day%2!=0){
            receiptPoints+=6;
        }

        LocalTime timeOfPurchase= LocalTime.parse(receipt.getPurchaseTime());

        if(timeOfPurchase.isAfter(LocalTime.of(14,0)) && timeOfPurchase.isBefore(LocalTime.of(16,0))){
            receiptPoints+=10;
        }

        return receiptPoints;





    }
}
