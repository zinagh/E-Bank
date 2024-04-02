package com.mfbank.service;
import com.mfbank.dto.Staticdto;
import com.mfbank.dto.Userdto;
import com.mfbank.mapper.Staticmapper;
import com.mfbank.model.Static;
import com.mfbank.model.User;
import com.mfbank.repository.StaticRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StaticServiceImp  implements IStaticService{
    StaticRepository staticRepository;
    Staticmapper staticmapper;
    public List<Staticdto> retrieveAllStatics() {
        List<Static>  statics =staticRepository.findAll();
        List<Staticdto> staticdtos = staticmapper.staticsTostaticdtos(statics);
        return staticdtos;
    }
    public Staticdto retrieveStatic(String reference) {
     Static stat = staticRepository.findById(reference).get();
     Staticdto staticdto = staticmapper.statictostaticdto(stat);
     return staticdto;
    }

    public void addStatic(Staticdto statdto) {
        System.out.println(statdto);
       Static stat= staticmapper.staticdtotostatic(statdto);
        System.out.println(stat);
        staticRepository.save(stat);
    }

    public void removeStatic(String reference) {
        staticRepository.deleteById(reference);
    }
    public Static modifyStatic(Staticdto statdto) {
        Static stat = staticmapper.staticdtotostatic(statdto);
        staticRepository.save(stat);
        return stat ;
    }
}
