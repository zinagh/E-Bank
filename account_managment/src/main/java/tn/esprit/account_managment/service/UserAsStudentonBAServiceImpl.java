package tn.esprit.account_managment.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.mapper.IUserAsStudentonBAMapper;
import tn.esprit.account_managment.model.TransactionBankAccount;
import tn.esprit.account_managment.model.UserAsStudentonBA;
import tn.esprit.account_managment.repository.UserAsStudentonBARepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

public class UserAsStudentonBAServiceImpl implements IUserAsStudentonBAService
{
    UserAsStudentonBARepository userAsStudentonBARepository;
    IUserAsStudentonBAMapper iUserAsStudentonBAMapper;

    public List<UserAsStudentonBADto> retrieveAllUserAsStudentonBAs() {
        List<UserAsStudentonBA> userAsStudentonBAS =  userAsStudentonBARepository.findAll();
        List<UserAsStudentonBADto> userAsStudentonBADtos = iUserAsStudentonBAMapper.
    }
    public UserAsStudentonBA retrieveUserAsStudentonBA(String userAsStudentonBAId) {
        return userAsStudentonBARepository.findById(userAsStudentonBAId).get();
    }
    public UserAsStudentonBA addUserAsStudentonBA(UserAsStudentonBA u) {
        return userAsStudentonBARepository.save(u);
    }
    public void removeUserAsStudentonBA(String userAsStudentonBAId) {
        userAsStudentonBARepository.deleteById(userAsStudentonBAId);
    }
    public UserAsStudentonBA modifyUserAsStudentonBA(UserAsStudentonBA userAsStudentonBA) {
        return userAsStudentonBARepository.save(userAsStudentonBA);
    }

}
