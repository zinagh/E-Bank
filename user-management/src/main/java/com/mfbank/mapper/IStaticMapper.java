package com.mfbank.mapper;

import com.mfbank.dto.Staticdto;
import com.mfbank.dto.Userdto;
import com.mfbank.model.Static;
import com.mfbank.model.User;

import java.util.List;

public interface IStaticMapper {
    Staticdto statictostaticdto (Static stat);
    Static staticdtotostatic (Staticdto statdto);
    List<Staticdto> staticsTostaticdtos (List<Static> statics);
    List<Static> staticdtosdtosTostatics(List<Staticdto> staticdtos);


}
