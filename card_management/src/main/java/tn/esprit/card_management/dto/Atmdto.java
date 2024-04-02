package tn.esprit.card_management.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Atmdto {
    Long Id;
    String Location;
    float Somme;
    boolean Activated;
    String image;
}

