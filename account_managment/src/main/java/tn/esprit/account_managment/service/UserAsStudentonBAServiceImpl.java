package tn.esprit.account_managment.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.mapper.IUserAsStudentonBAMapper;
import tn.esprit.account_managment.model.TransactionBankAccount;
import tn.esprit.account_managment.model.UserAsStudentonBA;
import tn.esprit.account_managment.repository.UserAsStudentonBARepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAsStudentonBAServiceImpl implements IUserAsStudentonBAService
{
    @Autowired
    private final UserAsStudentonBARepository userAsStudentonBARepository;
    @Autowired
    private final IUserAsStudentonBAMapper iUserAsStudentonBAMapper;

    @Override
    public List<UserAsStudentonBADto> retrieveAllUserAsStudentonBAs() {
        List<UserAsStudentonBA> userAsStudentonBAS =  userAsStudentonBARepository.findAll();
        List<UserAsStudentonBADto> userAsStudentonBADtos = iUserAsStudentonBAMapper.userstodtos(userAsStudentonBAS);
        return userAsStudentonBADtos;
    }

    @Override
    public UserAsStudentonBADto retrieveUserAsStudentonBA(String userAsStudentonBAId) {
        UserAsStudentonBA userAsStudentonBA = userAsStudentonBARepository.findById(userAsStudentonBAId).get();
        UserAsStudentonBADto userAsStudentonBADto = iUserAsStudentonBAMapper.usertodto(userAsStudentonBA);
        return userAsStudentonBADto;
    }

    @Override
    public void addUserAsStudentonBA(UserAsStudentonBADto userAsStudentonBADto) {
        UserAsStudentonBA userAsStudentonBA = iUserAsStudentonBAMapper.dtotouser(userAsStudentonBADto);
         userAsStudentonBARepository.save(userAsStudentonBA);
    }
    @Override
    public void removeUserAsStudentonBA(String userAsStudentonBAId) {
        userAsStudentonBARepository.deleteById(userAsStudentonBAId);
    }

    @Override
    public void modifyUserAsStudentonBA(UserAsStudentonBADto userAsStudentonBADto) {
        UserAsStudentonBA userAsStudentonBA = iUserAsStudentonBAMapper.dtotouser(userAsStudentonBADto);
        userAsStudentonBARepository.save(userAsStudentonBA);

    }

}
