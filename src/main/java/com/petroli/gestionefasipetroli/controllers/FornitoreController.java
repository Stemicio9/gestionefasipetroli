package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.Fornitore;
import com.petroli.gestionefasipetroli.entities.QuotazioneGiornaliera;
import com.petroli.gestionefasipetroli.services.FornitoreService;
import com.petroli.gestionefasipetroli.utils.GeneraExcel;
import com.petroli.gestionefasipetroli.utils.GeneraExcelPrezziFornitori;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fornitori")
public class FornitoreController {

    @Autowired
    private FornitoreService fornitoreService;


    @GetMapping("getall")
    public List<Fornitore> getallfornitori(){
        return fornitoreService.tuttifornitori();
    }

    @GetMapping("getfornitore/{nomefornitore}")
    public Fornitore prendifornitore(@PathVariable String nomefornitore){
        return fornitoreService.getfornitore(nomefornitore);
    }

    @PostMapping("aggiungi")
    public List<Fornitore> aggiungifornitore(@RequestBody Fornitore fornitore){
        boolean result = fornitoreService.aggiungifornitore(fornitore);
        return getallfornitori();
    }

    @GetMapping("delete/{idfornitore}")
    public List<Fornitore> eliminafornitore(@PathVariable long idfornitore){
        boolean result = fornitoreService.rimuovifornitore(idfornitore);
        return getallfornitori();
    }

    @PostMapping("eliminaquotazione")
    public boolean eliminaquotazione(@RequestBody QuotazioneGiornaliera quotazioneGiornaliera){
        return fornitoreService.rimuoviquotazione(quotazioneGiornaliera);
    }

    @GetMapping("generaexcel/{id}")
    public ResponseEntity<byte[]> esportaexcel(@PathVariable long id){

        Fornitore fornitore = fornitoreService.getfornitoredaid(id);

        List<QuotazioneGiornaliera> listariepilogo = fornitore.getQuotazioni();
        GeneraExcelPrezziFornitori generatore = new GeneraExcelPrezziFornitori(listariepilogo, fornitore.getNomefornitore());
        XSSFWorkbook fileresult = generatore.export();


        ByteArrayOutputStream os = null;

        byte[] result = {};

        try {
            os = new ByteArrayOutputStream();
            fileresult.write(os);
            result = os.toByteArray();
            fileresult.close();
        }catch(Exception e){
            e.printStackTrace();
            try{
                fileresult.close();
            }catch(Exception ex){

            }
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // Here you have to set the actual filename of your pdf

        headers.setContentDispositionFormData("recap", "recap.xlsx");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(result, headers, HttpStatus.OK);
        return response;

    }

}
