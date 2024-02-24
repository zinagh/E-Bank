package tn.esprit.account_managment.mapper;

import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.model.UserAsStudentonBA;

import java.util.List;

public interface IUserAsStudentonBAMapper
{
    UserAsStudentonBADto usertodto(UserAsStudentonBA userAsStudentonBA);
    UserAsStudentonBA dtotouser(UserAsStudentonBADto userAsStudentonBADto);

    List<UserAsStudentonBADto> userstodtos(List<UserAsStudentonBA> userAsStudentonBAS);
    List<UserAsStudentonBA> dotousers(List<UserAsStudentonBADto> userAsStudentonBADtos);

}
