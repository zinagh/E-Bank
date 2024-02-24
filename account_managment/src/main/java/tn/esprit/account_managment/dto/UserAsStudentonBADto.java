package tn.esprit.account_managment.dto;
import lombok.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAsStudentonBADto
{
    private String userName;
    private String firstname;
    private String lastname;
    private String financial_informations;
    private Date datesPaiments;
    private Double montantsPaiment;
}
