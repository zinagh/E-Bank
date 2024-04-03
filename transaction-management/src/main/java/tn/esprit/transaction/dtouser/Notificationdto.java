package tn.esprit.transaction.dtouser;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notificationdto {
    private String reference;
    private String sender;
    private String receiver;
    private String type;
    private LocalDateTime sentTime;
    private Boolean isRead;
    private Set<Userdto> users;
}
