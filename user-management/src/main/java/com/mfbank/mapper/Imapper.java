package com.mfbank.mapper;
import com.mfbank.dto.Userdto;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface Imapper {
    UserRepresentation mapuserRep (Userdto userdto);
    void assignerole(String role , String id);

    UserRepresentation mapuserRepToUpdate(Userdto userdto);


}
