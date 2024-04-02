package com.mfbank.account_managment.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private FeeType feeType;
    private double amountPercent;
    private String description;
    @OneToMany(mappedBy = "defaultFees")
    @JsonIgnore
    private List<BankAccount> bankAccounts;
    @OneToMany(mappedBy = "internationnalFees")
    @JsonIgnore
    private List<InternationalTransfer> internationalTransfers;

}