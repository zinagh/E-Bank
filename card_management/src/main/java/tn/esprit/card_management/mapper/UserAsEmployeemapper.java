package tn.esprit.card_management.mapper;

import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.UserAsEmployeedto;
import tn.esprit.card_management.model.UserAsEmployee;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAsEmployeemapper implements IUserAsEmployeemapper {

@Override
 public UserAsEmployeedto fromentityTodto(UserAsEmployee userAsEmployee){
    UserAsEmployeedto userAsEmployeedto = new UserAsEmployeedto();
    userAsEmployeedto.setUserName(userAsEmployee.getUserName());
    return userAsEmployeedto;
}
@Override
    public UserAsEmployee fromdtoToentity(UserAsEmployeedto userAsEmployeedto){
    UserAsEmployee userAsEmployee= new UserAsEmployee();
    UserAsEmployee.setUserName(userAsEmployeedto.getUserName());
    return userAsEmployee;
}
    @Override
    public List<UserAsEmployeedto>fromListentityTodtos (List<UserAsEmployee> userAsEmployees) {
        return userAsEmployees.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());
    }
    @Override
    public List<UserAsEmployee>fromListdtosToentities(List<UserAsEmployeedto> userAsEmployeedtos){
        return userAsEmployeedtos.stream()
                .map(this::fromdtoToentity)
                .collect(Collectors.toList());
    }



}

