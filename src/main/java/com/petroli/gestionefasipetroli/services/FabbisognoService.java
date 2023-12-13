package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.dto.DateRange;
import com.petroli.gestionefasipetroli.entities.*;
import com.petroli.gestionefasipetroli.repositories.FabbisognoRepository;
import com.petroli.gestionefasipetroli.repositories.ImpostazioniRepository;
import com.petroli.gestionefasipetroli.utils.EmailComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class FabbisognoService {

    @Autowired
    private FabbisognoRepository fabbisognoRepository;

    @Autowired
    private EmailComponent emailComponent;

    @Autowired
    private AuthenticationService authenticationService;



    @Autowired
    private ImpostazioniRepository impostazioniRepository;




    public List<Fabbisogno> getallfabbisogni(){
        return fabbisognoRepository.findAllBySmaltitoFalse();
    }


    public List<Fabbisogno> getmieifabbisogni(Cliente cliente){
        List<Fabbisogno> result = new LinkedList<>();
        List<PuntoVendita> listapunti = cliente.getListapuntivendita();
        for(PuntoVendita curr : listapunti){
            List<Fabbisogno> tmp = fabbisognoRepository.findAllByPuntoVendita(curr);
            result.addAll(tmp);
        }
        return result;
    }

    public Fabbisogno getfabbisognodaid(long id){
        try {
            return fabbisognoRepository.findById(id);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Fabbisogno> aggiungifabbisogno(Fabbisogno fabbisogno){
        try{
            boolean emaildainviare = true;
            if(fabbisogno.getId() != 0){
                emaildainviare = false;
            }
            if(fabbisognoRepository.findById(fabbisogno.getId()) != null) emaildainviare = false;
            fabbisognoRepository.save(fabbisogno);

            Utente u = authenticationService.getcurrentauthentication();
            Impostazioni emaildestinazione = impostazioniRepository.findByNomeimpostazione("emaildestinazione");

            String[] indirizzi = emaildestinazione.getValoreimpostazione().split(" ");

            if(emaildestinazione != null && emaildainviare) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String data = df.format(fabbisogno.getData()).toString();


                String from = "Partenup";
                String subject = "Fabbisogno per punto vendita " + fabbisogno.getPuntoVendita().getNome() +
                        " aggiunto da " + u.getUsername();
                String gasolio =    fabbisogno.getGasolio() > 0 ? "GASOLIO: " + fabbisogno.getGasolio()+"\n" : "";
                String benzina =    fabbisogno.getBenzina() > 0 ? "BENZINA: " + fabbisogno.getBenzina()+"\n" : "";
                String supreme =    fabbisogno.getSupreme() > 0 ? "SUPREME: " + fabbisogno.getSupreme()+"\n" : "";
                String gpl =    fabbisogno.getGpl() > 0 ? "GPL: " + fabbisogno.getGpl()+"\n" : "";
                String fornitore = fabbisogno.getFornitore() != null ? "FORNITORE : " + fabbisogno.getFornitore().getNomefornitore()+"\n" : "";
                String basedicarico = fabbisogno.getBasedicarico() != null ? "BASE DI CARICO : " + fabbisogno.getBasedicarico().getNomebasedicarico()+"\n" : "";
                String text = "Fabbisogno del " + data + ": " +
                        "\n" + fornitore + basedicarico + gasolio + benzina + supreme +gpl;




                for(String to : indirizzi)
                    emailComponent.sendemail(to,subject,text,from);
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        return getallfabbisogni();
    }



    public List<Fabbisogno> aggiungifabbisognolatocliente(Fabbisogno fabbisogno){
        try{
            boolean emaildainviare = true;
            if(fabbisogno.getId() != 0){
                emaildainviare = false;
            }
            if(fabbisognoRepository.findById(fabbisogno.getId()) != null) emaildainviare = false;
            fabbisognoRepository.save(fabbisogno);

            Utente u = authenticationService.getcurrentauthentication();
            Impostazioni emaildestinazione = impostazioniRepository.findByNomeimpostazione("emaildestinazionelatocliente");

            String[] indirizzi = emaildestinazione.getValoreimpostazione().split(" ");

            if(emaildestinazione != null && emaildainviare) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String data = df.format(fabbisogno.getData()).toString();


                String from = "Partenup";
                String subject = "Fabbisogno per punto vendita " + fabbisogno.getPuntoVendita().getNome() +
                        " aggiunto da " + u.getUsername();
                String gasolio =    fabbisogno.getGasolio() > 0 ? "GASOLIO: " + fabbisogno.getGasolio()+"\n" : "";
                String benzina =    fabbisogno.getBenzina() > 0 ? "BENZINA: " + fabbisogno.getBenzina()+"\n" : "";
                String supreme =    fabbisogno.getSupreme() > 0 ? "SUPREME: " + fabbisogno.getSupreme()+"\n" : "";
                String gpl =    fabbisogno.getGpl() > 0 ? "GPL: " + fabbisogno.getGpl()+"\n" : "";
                String fornitore = fabbisogno.getFornitore() != null ? "FORNITORE : " + fabbisogno.getFornitore().getNomefornitore()+"\n" : "";
                String basedicarico = fabbisogno.getBasedicarico() != null ? "BASE DI CARICO : " + fabbisogno.getBasedicarico().getNomebasedicarico()+"\n" : "";
                String text = "Fabbisogno del " + data + ": " +
                        "\n" + fornitore + basedicarico + gasolio + benzina + supreme +gpl;




                for(String to : indirizzi)
                    emailComponent.sendemail(to,subject,text,from);
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        return getallfabbisogni();
    }

    public List<Fabbisogno> eliminafabbisogno(Fabbisogno fabbisogno){
        try {
            fabbisognoRepository.delete(fabbisogno);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getallfabbisogni();
    }


    public List<Fabbisogno> getindaterange(DateRange dateRange){
        return fabbisognoRepository.findAllByDataBetween(dateRange.getData1(),dateRange.getData2());
    }




}
