package com.mfbank.service;
import com.mfbank.dto.Userdto;
import com.mfbank.mapper.Usermapper;
import com.mfbank.model.Notification;
import com.mfbank.model.User;
import com.mfbank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImp  implements IUserService{
    UserRepository userRepository;
    Usermapper usermapper;
    public List<Userdto> retrieveAllUsers() {
     List<User>  users =userRepository.findAll();
     List<Userdto> userdtos = usermapper.usersTouserdtos(users);
     return userdtos;
    }
    public Userdto retrieveUser(String userName) {
        User user=  userRepository.findById(userName).get();
        Userdto userdto = usermapper.userTouserdto(user);
        return userdto;
    }
    public void addUser(Userdto userdto ) {
        User user= usermapper.userdtoTouser(userdto) ;
        userRepository.save(user);
    }
    public void removeUser(String userName) {
        userRepository.deleteById(userName);
    }
    public User modifyUser(Userdto userdto) {
        User user = usermapper.userdtoTouser(userdto);

         userRepository.save(user);
        return user;
    }
}
