package tn.esprit.card_management.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.card_management.model.FeeType;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedto {
    private Long id;
    private String name;
    private String description;
    private double amount;
    private FeeType type;
}
