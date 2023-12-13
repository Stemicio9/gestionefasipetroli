package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.*;
import com.petroli.gestionefasipetroli.repositories.BonificoRepository;
import com.petroli.gestionefasipetroli.repositories.RiepilogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class RiepilogoService {

    @Autowired
    private FabbisognoService fabbisognoService;

    @Autowired
    private RiepilogoRepository riepilogoRepository;

    @Autowired
    private PreventivoService preventivoService;

    @Autowired
    private TrasportoService trasportoService;

    @Autowired
    private BonificoRepository bonificoRepository;

    @Autowired
    private ClienteService clienteService;



    public Riepilogo findriepilogobyid(long id){
        return riepilogoRepository.findById(id);
    }

    public List<Riepilogo> getallriepiloghiindaterange(DateRange range){
        List<Fabbisogno> listainrange = fabbisognoService.getindaterange(range);
        creariepiloghimancanti(listainrange);
        return riepilogoRepository.findAllByFabbisogno_DataBetween(range.getData1(),range.getData2());
    }

    public List<Riepilogo> getallriepiloghiindaterange(DateRange range, int page, int size){
        List<Fabbisogno> listainrange = fabbisognoService.getindaterange(range);
        creariepiloghimancanti(listainrange);
        Pageable pageable =  PageRequest.of(page, size);
        return riepilogoRepository.findAllByFabbisogno_DataBetween(range.getData1(),range.getData2(), pageable);
    }


    public List<Riepilogo> getallbycliente(Cliente cliente){

        List<PuntoVendita> listapuntivendita = clienteService.getpuntivenditadiattivita(cliente.getIdcliente());
        List<Riepilogo> result = new LinkedList<>();

        for(PuntoVendita current : listapuntivendita){
            result.addAll(riepilogoRepository.findAllByFabbisogno_PuntoVendita(current));
        }

        return result;
    }

    public boolean salvariepilogo(RiepilogoPerFrontend riepilogo){
        try {
            if(riepilogo.getDas() == null || riepilogo.getDas().isEmpty()) {
            } else {
                Fabbisogno fabbisogno = riepilogo.getFabbisogno();
                fabbisogno.setSmaltito(true);
                fabbisognoService.aggiungifabbisogno(fabbisogno);
            }
            riepilogoRepository.save(riepilogo.toriepilogo());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    private void creariepiloghimancanti(List<Fabbisogno> trovati){
        for(Fabbisogno curr: trovati){

            if(!curr.isSmaltito()) {
                Riepilogo riep = riepilogoRepository.findByFabbisogno(curr);
                if (riep == null) {
                    creariepilogodafabbisogno(curr);
                }
            }

        }
    }


    private boolean creariepilogodafabbisogno(Fabbisogno fabbisogno){
        Preventivo preventivo = preventivoService.getpreventivofromfabbisogno(fabbisogno);
        Trasporto trasporto = trasportoService.getbyfabbisogno(fabbisogno);
        if(preventivo == null || trasporto == null) return false;
        Riepilogo dacreare = new Riepilogo(fabbisogno,trasporto,preventivo);
        try{
            riepilogoRepository.save(dacreare);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancellariepilogodafabbisogno(Fabbisogno fabbisogno){
        Riepilogo riepilogo = riepilogoRepository.findByFabbisogno(fabbisogno);
        return cancellariepilogo(riepilogo);
    }


    public boolean cancellariepilogo(Preventivo preventivo){
        Riepilogo riepilogo = riepilogoRepository.findByPreventivo(preventivo);
        if(riepilogo == null) return false;
        return cancellariepilogo(riepilogo);
    }


    public boolean cancellariepilogo(Riepilogo riepilogo){
        try{
            riepilogoRepository.delete(riepilogo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean aggiungibonifico(Bonifico bonifico, long idriepilogo){
        Riepilogo riepilogo = findriepilogobyid(idriepilogo);
        try{
           riepilogo.getListabonifici().add(bonifico);
           riepilogoRepository.save(riepilogo);
           return true;
        }catch(Exception e){
           e.printStackTrace();
           return false;
        }
    }


    public boolean rimuovibonifico(Bonifico bonifico, long idriepilogo){
        Riepilogo riepilogo = findriepilogobyid(idriepilogo);
        try{
            riepilogo.getListabonifici().remove(bonifico);
            riepilogoRepository.save(riepilogo);
            bonificoRepository.delete(bonifico);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    // per i file

    @Autowired
    RiepilogoFileService fileService;

    public boolean aggiungifile(RiepilogoFile file, long idriepilogo){
        Riepilogo riepilogo = findriepilogobyid(idriepilogo);
        try{
            riepilogo.getFiles().add(file);
            riepilogoRepository.save(riepilogo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuovifile(long idriepilogo, long idfile){
        RiepilogoFile file = fileService.getFile(idfile);
        Riepilogo riepilogo = findriepilogobyid(idriepilogo);
        try{
            riepilogo.getFiles().remove(file);
            riepilogoRepository.save(riepilogo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Stream<RiepilogoFile> getTuttiFileDiRiepilogo(long idriepilogo){
        return this.riepilogoRepository.findById(idriepilogo).getFiles().stream();
    }





    // METODO DI UTILITY
    public String cambiatuttiiriepiloghiconlistabonifici(){
        StringBuilder result = new StringBuilder();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<Riepilogo> tuttiriepiloghi = riepilogoRepository.findAll();
        for(Riepilogo curr : tuttiriepiloghi){
            try{
                Bonifico nuovo = new Bonifico();
                nuovo.setImportobonifico(curr.getImportobonifico());
                nuovo.setDescrizione(format.format(curr.getDatabonifico()));
                curr.getListabonifici().add(nuovo);
                riepilogoRepository.save(curr);
                result.append("AGGIUNTO");
                result.append("\n");
            }catch(Exception e){
                result.append(e.getMessage());
                e.printStackTrace();
            }
        }
        return result.toString();
    }

}
