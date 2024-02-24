package tn.esprit.account_managment.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.service.IUserAsStudentonBAService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserAsStudentonBAController {

    private final IUserAsStudentonBAService iUserAsStudentonBAService;


    @PostMapping("/addUserasstudent")
    public void addUser(@RequestBody UserAsStudentonBADto userAsStudentonBADto) {
        iUserAsStudentonBAService.addUserAsStudentonBA(userAsStudentonBADto);
    }

    @GetMapping("/getbankaccountby/{userAsStudentonBAId}")
    public UserAsStudentonBADto getUserasstudentbyId(@PathVariable String userAsStudentonBAId) {
        return iUserAsStudentonBAService.retrieveUserAsStudentonBA(userAsStudentonBAId);

    }

    @GetMapping("/getallUsers")
    public List<UserAsStudentonBADto> getallUsers() {
        return iUserAsStudentonBAService.retrieveAllUserAsStudentonBAs();
    }

    @PutMapping("/modifyuser")
    public void modifyuser(@RequestBody UserAsStudentonBADto userAsStudentonBADto) {
        iUserAsStudentonBAService.modifyUserAsStudentonBA(userAsStudentonBADto);
    }

    @DeleteMapping("/deleteuserby/{userAsStudentonBAId}")
    public void deleteuserby(@PathVariable String userAsStudentonBAId) {
         iUserAsStudentonBAService.removeUserAsStudentonBA(userAsStudentonBAId);

    }




}
