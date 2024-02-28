package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.dto.TransactionCarddto;
import tn.esprit.card_management.dto.UserAsEmployeedto;
import tn.esprit.card_management.mapper.IUserAsEmployeemapper;
import tn.esprit.card_management.model.BankAccountForCard;
import tn.esprit.card_management.model.TransactionCard;
import tn.esprit.card_management.model.UserAsEmployee;
import tn.esprit.card_management.repository.UserAsEmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAsEmplyeeService {

    private final IUserAsEmployeemapper iUserAsEmployee;
    private final UserAsEmployeeRepository userAsEmployeeRepository;
    public void addUserAsEmployee(UserAsEmployeedto userAsEmployeedto) {
        UserAsEmployee userAsEmployee = iUserAsEmployee.fromdtoToentity(userAsEmployeedto);
        userAsEmployeeRepository.save(userAsEmployee);
    }
    public List<UserAsEmployee> retrieveall() {
           List< UserAsEmployee> userAsEmployees = userAsEmployeeRepository.findAll();
           List<UserAsEmployeedto> userAsEmployeesdtos = iUserAsEmployee.fromListentityTodtos(userAsEmployees);
        return userAsEmployees;
    }

}

