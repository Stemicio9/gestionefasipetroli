package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.Riepilogo;
import com.petroli.gestionefasipetroli.services.RiepilogoService;
import com.petroli.gestionefasipetroli.utils.Calcolipreventivo;
import com.petroli.gestionefasipetroli.utils.ConvertitoreRiepilogo;
import com.petroli.gestionefasipetroli.utils.GeneraExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/riepilogo")
public class RiepilogoController {

    @Autowired
    private RiepilogoService riepilogoService;

    @Autowired
    private Calcolipreventivo calcolipreventivo;

    @PostMapping("getall")
    public List<RiepilogoPerFrontend> getallbydaterange(@RequestBody DateRange range){
        List<Riepilogo> lista = riepilogoService.getallriepiloghiindaterange(range);
        List<RiepilogoPerFrontend> result = trasformalista(lista);
        return result;
    }

    @PostMapping("save")
    public boolean salva(@RequestBody RiepilogoPerFrontend riepilogoPerFrontend){
        return riepilogoService.salvariepilogo(riepilogoPerFrontend);
    }


    public List<RiepilogoPerFrontend> trasformalista(List<Riepilogo> lista){
        List<RiepilogoPerFrontend> result = new LinkedList<>();

        for(Riepilogo curr : lista){
            result.add(calcolipreventivo.trasformariepilogo(curr));
        }

        return result;
    }



    @PostMapping("/generaexcel")
    public ResponseEntity<byte[]> esportaexcel(@RequestBody DateRange range){

        List<RiepilogoPerFrontend> listariepilogo = getallbydaterange(range);
        GeneraExcel generatore = new GeneraExcel(listariepilogo);
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
