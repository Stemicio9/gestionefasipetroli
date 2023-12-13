package com.petroli.gestionefasipetroli.controllers;

import com.petroli.gestionefasipetroli.dto.ContainerRiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.dto.ResponseFile;
import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.Bonifico;
import com.petroli.gestionefasipetroli.entities.Cliente;
import com.petroli.gestionefasipetroli.entities.Riepilogo;
import com.petroli.gestionefasipetroli.entities.RiepilogoFile;
import com.petroli.gestionefasipetroli.services.PuntiVenditaService;
import com.petroli.gestionefasipetroli.services.RiepilogoFileService;
import com.petroli.gestionefasipetroli.services.RiepilogoService;
import com.petroli.gestionefasipetroli.utils.Calcolipreventivo;
import com.petroli.gestionefasipetroli.utils.GeneraExcel;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/riepilogo")
public class RiepilogoController {

    @Autowired
    private RiepilogoService riepilogoService;

    @Autowired
    private Calcolipreventivo calcolipreventivo;

    @Autowired
    private PuntiVenditaService puntiVenditaService;

    @GetMapping("get/{id}")
    public Riepilogo getbyid(@PathVariable long id){
        return riepilogoService.findriepilogobyid(id);
    }

    @PostMapping("getall")
    public List<RiepilogoPerFrontend> getallbydaterange(@RequestBody DateRange range){
        long startTime = System.currentTimeMillis();
        List<Riepilogo> lista = riepilogoService.getallriepiloghiindaterange(range);
        List<RiepilogoPerFrontend> result = trasformalista(lista);
        long endtime = System.currentTimeMillis();
        System.out.println("TEMPO PER L'ESECUZIONE = " + (endtime-startTime) + " MILLISECONDI");
        return result;
    }

    @PostMapping("getallpaged")
    public ContainerRiepilogoPerFrontend getallbydaterange(@RequestBody DateRange range, @RequestParam int page, @RequestParam int size){
        List<Riepilogo> lista = riepilogoService.getallriepiloghiindaterange(range,page, size);
        List<RiepilogoPerFrontend> result = trasformalista(lista);
        ContainerRiepilogoPerFrontend res = new ContainerRiepilogoPerFrontend();
        res.setData(result);
        res.setNumero(getallbydaterange(range).size());
        return res;
    }

    @PostMapping("getallpercliente")
    public List<RiepilogoPerFrontend> getallbycliente(@RequestBody Cliente cliente){
        List<Riepilogo> lista = riepilogoService.getallbycliente(cliente);
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


        GeneraExcel generatore = new GeneraExcel(listariepilogo, puntiVenditaService);



        SXSSFWorkbook fileresult = generatore.export();


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

    @PostMapping("bonifico/save/{id}")
    public RiepilogoPerFrontend salvabonifico(@PathVariable long id, @RequestBody Bonifico bonifico){
        boolean result = riepilogoService.aggiungibonifico(bonifico,id);
        Riepilogo riep = riepilogoService.findriepilogobyid(id);
        return calcolipreventivo.trasformariepilogo(riep);
    }

    @PostMapping("bonifico/delete/{id}")
    public RiepilogoPerFrontend rimuovibonifico(@PathVariable long id, @RequestBody Bonifico bonifico){
        boolean result = riepilogoService.rimuovibonifico(bonifico,id);
        Riepilogo riep = riepilogoService.findriepilogobyid(id);
        return calcolipreventivo.trasformariepilogo(riep);
    }


    public String inseriscituttoinlistabonifici(){
       return riepilogoService.cambiatuttiiriepiloghiconlistabonifici();
    }



    // parte relativa ai file

    @Autowired
    private RiepilogoFileService storageService;

    @PostMapping("/upload/{idriepilogo}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable long idriepilogo) {
        String message = "";
        try {
            RiepilogoFile dasalvare = storageService.store(file);
            riepilogoService.aggiungifile(dasalvare, idriepilogo);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/deletefile/{idriepilogo}")
    public boolean deletefile(@PathVariable long idriepilogo, @RequestBody long idfile){
        return riepilogoService.rimuovifile(idriepilogo, idfile);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<List<ResponseFile>> getListFiles(@PathVariable long id) {

        List<ResponseFile> files = riepilogoService.getTuttiFileDiRiepilogo(id).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(""+dbFile.getId())
                    .toUriString();
            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable long id) {
        RiepilogoFile fileDB = storageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }


}
