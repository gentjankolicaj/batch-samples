package com.example.batch_samples.batch1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String account;
    private Date timeStamp;
    private BigDecimal bigDecimal;

}
