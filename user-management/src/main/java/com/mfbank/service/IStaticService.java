package com.mfbank.service;
import com.mfbank.dto.Staticdto;
import com.mfbank.model.Static;

import java.util.List;

public interface IStaticService {
    public List<Staticdto> retrieveAllStatics();
    public Staticdto retrieveStatic(String reference);
    public void addStatic(Staticdto statdto);
    public void removeStatic(String reference);
    public Static modifyStatic(Staticdto statdto);
}
