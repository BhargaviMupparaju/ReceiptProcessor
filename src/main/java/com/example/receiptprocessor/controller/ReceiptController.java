package com.example.receiptprocessor.controller;


import com.example.receiptprocessor.ReceiptService;
import com.example.receiptprocessor.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;


    @PostMapping("/process")
    public ResponseEntity<Map<String,String>> generateProcessReceipt(@RequestBody Receipt receipt){

        if(receipt==null || receipt.getRetailer()==null || receipt.getPurchaseDate()==null||
                receipt.getPurchaseTime()==null || receipt.getItems()==null|| receipt.getTotal()==null){
            throw new IllegalReceiptDataException("Invalid receipt with missing required fields");
        }
            String id= receiptService.generateProcessReceipt(receipt);
            return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String,Integer>> getReceiptPoints(@PathVariable String id){
        Integer points= receiptService.getPoints(id);
        return ResponseEntity.ok(Map.of("points",points));
    }
}
