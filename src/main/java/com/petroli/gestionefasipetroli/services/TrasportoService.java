package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.dto.TrasportoFilter;
import com.petroli.gestionefasipetroli.dto.Viaggio;
import com.petroli.gestionefasipetroli.entities.Atk;
import com.petroli.gestionefasipetroli.entities.BaseDiCarico;
import com.petroli.gestionefasipetroli.entities.Fabbisogno;
import com.petroli.gestionefasipetroli.entities.Trasporto;
import com.petroli.gestionefasipetroli.repositories.TrasportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrasportoService {

    @Autowired
    private TrasportoRepository trasportoRepository;

    @Autowired
    private FabbisognoService fabbisognoService;



    public List<Trasporto> getalltrasporti(){
        return trasportoRepository.findAll();
    }

    public Trasporto getbyid(long id){
        return trasportoRepository.findById(id);
    }

    public Trasporto getbyfabbisogno(Fabbisogno fabbisogno){
        return trasportoRepository.findByFabbisogno(fabbisogno);
    }

    public Trasporto getbyfabbisognoid(long idfabbisogno){
        Fabbisogno fabbisogno = fabbisognoService.getfabbisognodaid(idfabbisogno);
        return getbyfabbisogno(fabbisogno);
    }

    public boolean salva(Trasporto trasporto){
        try{
            trasporto.getFabbisogno().setTrasportoesistente(true);
            fabbisognoService.aggiungifabbisogno(trasporto.getFabbisogno());
            trasportoRepository.save(trasporto);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean rimuovi(Trasporto trasporto){
        try{
            trasporto.getFabbisogno().setTrasportoesistente(false);
            trasportoRepository.delete(trasporto);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Set<Viaggio> findbyfilter(TrasportoFilter filter){
        if(filter.getData() == null) return new HashSet<>();
        if(filter.getAtk() == null && filter.getBaseDiCarico() == null) return findallviaggiindata(filter.getData());
        if(filter.getBaseDiCarico() == null) return findallviaggidiatkindata(filter.getAtk(),filter.getData());
        if(filter.getAtk() == null) return findallviaggindataconbasedicarico(filter.getBaseDiCarico(),filter.getData());
        Set<Trasporto> result = new HashSet<>(findallbyatkanddateandbasedicarico(filter.getAtk(),filter.getData(),filter.getBaseDiCarico()));
        Viaggio darestituire = new Viaggio();
        darestituire.setListaviaggi(result);
        Set<Viaggio> res = new HashSet<>();
        if(darestituire.getListaviaggi().size() > 0) {
            res.add(darestituire);
        }
        return res;
    }



    public List<Trasporto> findbyatk(Atk atk){
        return trasportoRepository.findAllByAtk(atk);
    }

    public List<Trasporto> findbydata(Date data){
        return trasportoRepository.findAllByDatadicaricazione(data);
    }


    public List<Trasporto> findbybasedicarico(BaseDiCarico baseDiCarico){
        return trasportoRepository.findAllByFabbisogno_Basedicarico(baseDiCarico);
    }

    public List<Trasporto> findallbyatkanddate(Atk atk, Date data){
        return trasportoRepository.findAllByAtkAndDatadicaricazione(atk,data);
    }

    public List<Trasporto> findallbyatkandbasedicarico(Atk atk, BaseDiCarico baseDiCarico){
        return trasportoRepository.findAllByAtkAndFabbisogno_Basedicarico(atk,baseDiCarico);
    }

    public List<Trasporto> findallbyatkanddateandbasedicarico(Atk atk, Date data, BaseDiCarico baseDiCarico){
        List<Trasporto> result = trasportoRepository.findAllByAtkAndDatadicaricazioneAndFabbisogno_Basedicarico(atk, data, baseDiCarico);
        return result;
    }

    public Set<Viaggio> findallviaggindataconbasedicarico(BaseDiCarico base, Date data){
        Set<Atk> atkdiversi = prendilistadiatkindata(data);
        Set<Viaggio> result = new HashSet<>();
        for(Atk atk : atkdiversi){
            Viaggio daaggiungere = new Viaggio();
            daaggiungere.setListaviaggi(new HashSet<>(findallbyatkanddateandbasedicarico(atk,data,base)));
            if(daaggiungere.getListaviaggi().size() > 0) {
                result.add(daaggiungere);
            }
        }
        return result;
    }


    public Set<Viaggio> findallviaggidiatkindata(Atk atk,Date data){
        Set<BaseDiCarico> basidicarico = prendibasidicaricodiverseindata(data);
        Set<Viaggio> result = new HashSet<>();
        for(BaseDiCarico base : basidicarico){
            Viaggio daaggiungere = new Viaggio();
            daaggiungere.setListaviaggi(new HashSet<>(findallbyatkanddateandbasedicarico(atk,data,base)));
            if(daaggiungere.getListaviaggi().size() > 0) {
                result.add(daaggiungere);
            }
        }
        return result;
    }

    public Set<Viaggio> findallviaggiindata(Date data){
        Set<Atk> atkdiversi = prendilistadiatkindata(data);
        Set<BaseDiCarico> basidicarico = prendibasidicaricodiverseindata(data);

        Set<Viaggio> result = new HashSet<>();

        for(Atk atk : atkdiversi){
            for(BaseDiCarico base : basidicarico){
                Viaggio daaggiungere = new Viaggio();
                daaggiungere.setListaviaggi(new HashSet<>(findallbyatkanddateandbasedicarico(atk,data,base)));
                if(daaggiungere.getListaviaggi().size() > 0) {
                    result.add(daaggiungere);
                }
            }
        }
        return result;
    }

    private Set<BaseDiCarico> prendibasidicaricodiverseindata(Date data) {
        Set<BaseDiCarico> result = new HashSet<>();
        List<Trasporto> listatotale = findbydata(data);

        for(Trasporto t : listatotale){
          result.add(t.getFabbisogno().getBasedicarico());
        }
        return result;
    }

    private Set<Atk> prendilistadiatkindata(Date data) {
        Set<Atk> result = new HashSet<>();
        List<Trasporto> listatotale = findbydata(data);

        for(Trasporto t : listatotale){
            result.add(t.getAtk());
        }
        return result;
    }


}
