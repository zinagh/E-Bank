package com.mfbank.mapper;
import com.mfbank.dto.Staticdto;
import com.mfbank.model.Static;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class Staticmapper implements IStaticMapper{

    @Override
    public Staticdto statictostaticdto (Static stat) {
        Staticdto staticdto = new Staticdto();
        staticdto.setReference(stat.getReference());
        staticdto.setMsc(stat.getMsc());
        staticdto.setRoe(stat.getRoe());
        staticdto.setDe(stat.getDE());
        staticdto.setPtr(stat.getPtr());
        staticdto.setDcr(stat.getDcr());
        staticdto.setDfl(stat.getDfl());
        return staticdto;
    }
    @Override
    public Static staticdtotostatic (Staticdto statdto) {
        Static stat = new Static();
        stat.setReference(statdto.getReference());
        stat.setMsc(statdto.getMsc());
        stat.setRoe(statdto.getRoe());
        stat.setDE(statdto.getDe());
        stat.setPtr(statdto.getPtr());
        stat.setDcr(statdto.getDcr());
        stat.setDfl(statdto.getDfl());
        return stat;
    }
    @Override
    public List<Staticdto> staticsTostaticdtos (List<Static> statics){
        return statics.stream()
                .map(this::statictostaticdto)
                .collect(Collectors.toList());

    }
    @Override
   public List<Static> staticdtosdtosTostatics(List<Staticdto> staticdtos){
        return staticdtos.stream()
                .map(this::staticdtotostatic)
                .collect(Collectors.toList());

    }
}
