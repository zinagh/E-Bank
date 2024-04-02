package com.mfbank.mapper;

import com.mfbank.dto.Userdto;
import com.mfbank.model.User;

import java.util.List;

public interface IUserMapper {
    Userdto userTouserdto (User user);
    User userdtoTouser (Userdto userdto);

    List<Userdto> usersTouserdtos (List<User> users);


}
