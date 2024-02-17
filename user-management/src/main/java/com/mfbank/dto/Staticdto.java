package com.mfbank.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staticdto {
    private String reference;
    private Float msc;
    private Float roe;
    private Float dE;
    private Float ptr;
    private Float dcr;
    private Float dfl;

}
