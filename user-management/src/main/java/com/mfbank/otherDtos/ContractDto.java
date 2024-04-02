package com.mfbank.otherDtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Long id;
    private String ContractReference;
    private String userFirstName;
    private String userLastName ;
    private String residenceLocation;
    private CreditDto creditDto ;


}
