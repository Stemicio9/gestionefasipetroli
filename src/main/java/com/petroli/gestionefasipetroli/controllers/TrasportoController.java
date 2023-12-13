package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.dto.TrasportoFilter;
import com.petroli.gestionefasipetroli.dto.Viaggio;
import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Trasporto;
import com.petroli.gestionefasipetroli.services.TrasportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/trasporto")
public class TrasportoController {

    @Autowired
    private TrasportoService trasportoService;



    @GetMapping("getall")
    public List<Trasporto> getall(){
        return trasportoService.getalltrasporti();
    }

    @GetMapping("findbyfabbisognoid/{id}")
    public Trasporto findbyid(@PathVariable long id){
        return trasportoService.getbyfabbisognoid(id);
    }

    @PostMapping("salva")
    public boolean salva(@RequestBody Trasporto trasporto){
       return trasportoService.salva(trasporto);
    }

    @PostMapping("rimuovi")
    public boolean rimuovi(@RequestBody Trasporto trasporto){
        return trasportoService.rimuovi(trasporto);

    }

    @PostMapping("rimuovidafabbisogno")
    public boolean rimuovidafabbisogno(@RequestBody Fabbisogno fabbisogno){
        Trasporto dacancellare = findbyid(fabbisogno.getId());
        return rimuovi(dacancellare);
    }


    @PostMapping("findviaggio")
    public Set<Viaggio> fiindviaggio(@RequestBody TrasportoFilter trasportoFilter){
        if(trasportoFilter.getDatafine() == null) {
            System.out.println("CERCO IN DATA: " + trasportoFilter.getData().toString());
            Set<Viaggio> result = trasportoService.findbyfilter(trasportoFilter);
            return result;
        }else{
            System.out.println("STO CERCANDO NEL RANGE DI DATE");
            System.out.println("DATA INIZIO: " + trasportoFilter.getData().toString());
            System.out.println("DATA FINE: " + trasportoFilter.getDatafine().toString());
            Set<Viaggio> result = new HashSet<>();
            List<Date> listadate = getDatesBetween(trasportoFilter.getData(),trasportoFilter.getDatafine());
            for(Date curr : listadate){
                trasportoFilter.setData(curr);
                result.addAll(trasportoService.findbyfilter(trasportoFilter));
            }
            return result;
        }
    }


    private List<Date> getDatesBetween(
            Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

}
