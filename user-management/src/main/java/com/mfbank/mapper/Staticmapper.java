package com.mfbank.mapper;

import com.mfbank.dto.Staticdto;

import com.mfbank.model.Static;


public class Staticmapper {

    public Staticdto statictostaticdto (Static stat) {
        Staticdto staticdto = new Staticdto();
        staticdto.setMsc(stat.getMsc());
        staticdto.setRoe(stat.getRoe());
        staticdto.setDE(stat.getDE());
        staticdto.setPtr(stat.getPtr());
        staticdto.setDcr(stat.getDcr());
        staticdto.setDfl(stat.getDfl());
        return staticdto;
    }
    public Static staticdtotostatic (Staticdto statdto) {
        Static stat = new Static();
        stat.setMsc(statdto.getMsc());
        stat.setRoe(statdto.getRoe());
        stat.setDE(statdto.getDE());
        stat.setPtr(statdto.getPtr());
        stat.setDcr(statdto.getDcr());
        stat.setDfl(statdto.getDfl());
        return stat;
    }
}
