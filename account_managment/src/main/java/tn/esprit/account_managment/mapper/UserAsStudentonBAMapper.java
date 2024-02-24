package tn.esprit.account_managment.mapper;

import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.model.TransactionBankAccount;
import tn.esprit.account_managment.model.UserAsStudentonBA;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAsStudentonBAMapper implements IUserAsStudentonBAMapper
{
    //user->dto
    @Override
    public UserAsStudentonBADto usertodto(UserAsStudentonBA userAsStudentonBA)
    {
        UserAsStudentonBADto userAsStudentonBADto = new UserAsStudentonBADto();
        userAsStudentonBADto.setUserName(userAsStudentonBA.getUserName());
        userAsStudentonBADto.setFirstname(userAsStudentonBA.getFirstname());
        userAsStudentonBADto.setLastname(userAsStudentonBA.getLastname());
        userAsStudentonBADto.setFinancial_informations(userAsStudentonBA.getFinancial_informations());
        userAsStudentonBADto.setDatesPaiments(userAsStudentonBA.getDatesPaiments());
        userAsStudentonBADto.setMontantsPaiment(userAsStudentonBA.getMontantsPaiment());
        return userAsStudentonBADto;
    }


    //dto->user
    @Override
    public UserAsStudentonBA dtotouser(UserAsStudentonBADto userAsStudentonBADto)
    {
        UserAsStudentonBA userAsStudentonBA = new UserAsStudentonBA();
        userAsStudentonBA.setUserName(userAsStudentonBADto.getUserName());
        userAsStudentonBA.setFirstname(userAsStudentonBADto.getFirstname());
        userAsStudentonBA.setLastname(userAsStudentonBADto.getLastname());
        userAsStudentonBA.setFinancial_informations(userAsStudentonBADto.getFinancial_informations());
        userAsStudentonBA.setDatesPaiments(userAsStudentonBADto.getDatesPaiments());
        userAsStudentonBA.setMontantsPaiment(userAsStudentonBADto.getMontantsPaiment());
        return userAsStudentonBA;

    }

    @Override
    public List<UserAsStudentonBADto> userstodtos(List<UserAsStudentonBA> userAsStudentonBAS)
    {
        return userAsStudentonBAS.stream()
                .map(this::usertodto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserAsStudentonBA> dotousers(List<UserAsStudentonBADto> userAsStudentonBADtos)
    {
        return userAsStudentonBADtos.stream()
                .map(this::dtotouser)
                .collect(Collectors.toList());
    }

}
