package com.mfbank.mapper;
import com.mfbank.dto.Userdto;
import com.mfbank.model.User;

public class Usermapper {

    public Userdto userTouserdto (User user) {
        Userdto userdto = new Userdto();
        userdto.setUserName(user.getUserName());
        userdto.setNom(user.getNom());
        userdto.setPrenom(user.getPrenom());
        userdto.setEmail(user.getEmail());
        userdto.setDateNaissance(user.getDateNaissance());
        userdto.setCin(user.getCin());
        userdto.setNumTel(user.getNumTel());
        userdto.setRole(user.getRole());
        return userdto;
    }
    public User userdtoTouser (Userdto userdto) {
        User user = new User();
        user.setUserName(userdto.getUserName());
        user.setNom(userdto.getNom());
        user.setPrenom(userdto.getPrenom());
        user.setEmail(userdto.getEmail());
        user.setDateNaissance(userdto.getDateNaissance());
        user.setCin(userdto.getCin());
        user.setNumTel(userdto.getNumTel());
        user.setRole(userdto.getRole());
        return user;
    }
}






