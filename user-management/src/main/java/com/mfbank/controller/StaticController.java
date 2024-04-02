package com.mfbank.controller;
import com.mfbank.dto.Staticdto;
import com.mfbank.model.Static;
import com.mfbank.service.IStaticService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
public class StaticController {
    IStaticService staticService;


    @GetMapping("/retrieve-all-statics")
    public List<Staticdto> retrieveAllStatics() {
        List<Staticdto> listStaticdtos = staticService.retrieveAllStatics();
        return listStaticdtos;
    }

    @GetMapping("/retrieve-static/{reference}")
    public Staticdto retrieveStatic(@PathVariable("reference") String reference) {
        Staticdto stat = staticService.retrieveStatic(reference);
        return stat;
    }

    @PostMapping("/add-static")
    public void addStatic(@RequestBody Staticdto s) {
        staticService.addStatic(s);

    }

    @DeleteMapping("/remove-static/{reference}")
    public void removeStatic(@PathVariable("reference") String reference) {
        staticService.removeStatic(reference);
    }

    @PutMapping("/modify-static")
    public Static modifyStatic(@RequestBody Staticdto s) {
        Static stat = staticService.modifyStatic(s);
        return stat;
    }

}
