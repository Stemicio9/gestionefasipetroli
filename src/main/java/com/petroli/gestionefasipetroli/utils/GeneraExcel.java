package com.petroli.gestionefasipetroli.utils;

import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.Bonifico;
import com.petroli.gestionefasipetroli.entities.Cliente;
import com.petroli.gestionefasipetroli.services.PuntiVenditaService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.List;

public class GeneraExcel {

 //   private XSSFWorkbook workbook;
 //   private XSSFSheet sheet;

    SXSSFWorkbook work2;
    private SXSSFSheet sheet2;

    private List<RiepilogoPerFrontend> listariepilogo;
    private PuntiVenditaService puntiVenditaService;


    public GeneraExcel(List<RiepilogoPerFrontend> listariepilogo, PuntiVenditaService puntiVenditaService) {
        this.listariepilogo = listariepilogo;
        this.puntiVenditaService = puntiVenditaService;
 //       workbook = new XSSFWorkbook();
        work2 = new SXSSFWorkbook(1000);
    }


    private void writeHeaderLine() {
  //      sheet = workbook.createSheet("Riepiloghi");
        sheet2 = work2.createSheet("Riepilogo");

        Row row = sheet2.createRow(0);

        CellStyle style = work2.createCellStyle();
 /*       XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font); */

        int columncount = 0;

        createCell(row, columncount++, "Data", style);
        createCell(row, columncount++, "Punto Vendita", style);
        createCell(row, columncount++, "Base di carico", style);
        createCell(row, columncount++, "Fornitore", style);
        createCell(row, columncount++, "Gasolio", style);
        createCell(row, columncount++, "Benzina", style);
        createCell(row, columncount++, "Supreme", style);
        createCell(row, columncount++, "Gpl", style);
        createCell(row, columncount++, "Totale volumi", style);
        // fra 9 e 10
        createCell(row, columncount++, "Cali gasolio", style);
        createCell(row, columncount++, "Cali benzina", style);
        createCell(row, columncount++, "Cali supreme", style);
        createCell(row, columncount++, "Cali gpl", style);

        createCell(row, columncount++, "Ultimo scarico", style);

        createCell(row, columncount++, "Vettore", style);
        createCell(row, columncount++, "ATK", style);
        createCell(row, columncount++, "Autista", style);



        createCell(row, columncount++, "DAS", style);
        createCell(row, columncount++, "Prezzo gasolio fornitore", style);
        createCell(row, columncount++, "Prezzo benzina fornitore", style);
        createCell(row, columncount++, "Prezzo supreme fornitore", style);
        createCell(row, columncount++, "Prezzo gpl fornitore", style);


        createCell(row, columncount++, "Numero fattura fornitore", style);
        createCell(row, columncount++, "Importo fattura fornitore", style);


        createCell(row, columncount++, "Numero fattura partenopea", style);
        createCell(row, columncount++, "Importo fattura partenopea", style);



        createCell(row, columncount++, "Importo preventivo", style);
        createCell(row, columncount++, "Residuo da versare", style);
        createCell(row, columncount++, "Prezzo gasolio in fattura al cliente", style);
        createCell(row, columncount++, "Prezzo benzina in fattura al cliente", style);
        createCell(row, columncount++, "Prezzo supreme in fattura al cliente", style);
        createCell(row, columncount++, "Prezzo gpl in fattura al cliente", style);


        for(int i = 1; i<=5; i++){
            createCell(row, columncount++, "Bonifico " + i, style);
            createCell(row, columncount++, "Descrizione bonifico " + i, style);
        }
// 29 celle

    }


    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
    //    sheet2.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private void writeDataLines() {

        StopWatch watch = new StopWatch();
        watch.start();

        int rowCount = 1;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        CellStyle style = work2.createCellStyle();
//       XSSFFont font = workbook.createFont();
//        font.setFontHeight(10);
//        style.setFont(font);

        CellStyle currencyStyle;
          DataFormat cf = work2.createDataFormat();
          currencyStyle = work2.createCellStyle();
          currencyStyle.setDataFormat(cf.getFormat("€#,##0.00_);[Red]€#,##0.00)"));

        for (RiepilogoPerFrontend riep : listariepilogo) {
            Row row = sheet2.createRow(rowCount++);
            int columnCount = 0;

            if(riep.getFabbisogno() != null) {
                createCell(row, columnCount++, sdf.format(riep.getFabbisogno().getData()), style);
                if(riep.getFabbisogno().getPuntoVendita() != null) {
                    createCell(row, columnCount++, riep.getFabbisogno().getPuntoVendita().getNome(), style);
                }else{
                    createCell(row, columnCount++, " ", style);
                }
                if(riep.getFabbisogno().getBasedicarico() != null) {
                    createCell(row, columnCount++, riep.getFabbisogno().getBasedicarico().getNomebasedicarico(), style);
                }else {
                    createCell(row, columnCount++, " ", style);
                }
                if(riep.getFabbisogno().getFornitore() != null) {
                    createCell(row, columnCount++, riep.getFabbisogno().getFornitore().getNomefornitore(), style);
                }else {
                    createCell(row, columnCount++, " ", style);
                }
                createCell(row, columnCount++, riep.getFabbisogno().getGasolio(), style);
                createCell(row, columnCount++, riep.getFabbisogno().getBenzina(), style);
                createCell(row, columnCount++, riep.getFabbisogno().getSupreme(), style);
                createCell(row, columnCount++, riep.getFabbisogno().getGpl(), style);
            } else{
                // Devo riempire 8 celle
                for(int i = 0; i<8; i++){
                    createCell(row, columnCount++, " ", style);
                }
            }
            createCell(row, columnCount++, riep.getTotalevolumicarburantitradizionali(), style);



            createCell(row, columnCount++, riep.getCaligasolio(), style);
            createCell(row, columnCount++, riep.getCalibenzina(), style);
            createCell(row, columnCount++, riep.getCalisupreme(), style);
            createCell(row, columnCount++, riep.getCaligpl(), style);


            createCell(row, columnCount++, riep.getUltimoscarico(), style);


            if(riep.getTrasporto() != null) {
                createCell(row, columnCount++, riep.getTrasporto().getNometrasportatore(), style);
                if(riep.getTrasporto().getAtk() == null){
                    createCell(row, columnCount++, " ", style);
                }else {
                    createCell(row, columnCount++, riep.getTrasporto().getAtk().getTarga(), style);
                }
                if(riep.getTrasporto().getAutista() == null){
                    createCell(row, columnCount++, " ", style);
                }else {
                    createCell(row, columnCount++, riep.getTrasporto().getAutista().getNomeautista(), style);
                }
            }else{
                createCell(row, columnCount++, " ", style);
                createCell(row, columnCount++, " ", style);
                createCell(row, columnCount++, " ", style);
            }

            createCell(row, columnCount++, riep.getDas(), style);


            createCell(row, columnCount++, riep.getPrezzogasoliofornitore(), currencyStyle);
            createCell(row, columnCount++, riep.getPrezzobenzinafornitore(), currencyStyle);
            createCell(row, columnCount++, riep.getPrezzosupremefornitore(), currencyStyle);
            createCell(row, columnCount++, riep.getPrezzogplfornitore(), currencyStyle);


            createCell(row, columnCount++, riep.getNumerofatturafornitore(), style);
            createCell(row, columnCount++, riep.getImportofatturafornitore(), currencyStyle);


            createCell(row, columnCount++, riep.getNumerofatturapartenopea(), style);
            createCell(row, columnCount++, riep.getImportofattura(), currencyStyle);



      /*      if(riep.getDatabonifico() != null) {
                createCell(row, columnCount++, sdf.format(riep.getDatabonifico()), style);
            }else{
                createCell(row, columnCount++, " ", style);
            } */

            createCell(row, columnCount++, riep.getImportopreventivo(), currencyStyle);
            createCell(row, columnCount++, riep.getResiduodaversare(), currencyStyle);

            Cliente cliente = puntiVenditaService.cercaproprietario(riep.getFabbisogno().getPuntoVendita());

            double prezzofinalegasolioself = 0;
            double prezzofinalebenzina = 0;
            double prezzofinalesupreme = 0;
            double prezzofinalegpl = 0;


            if(cliente != null) {

                if (!riep.getFabbisogno().getPuntoVendita().isAllservito()) {
                    prezzofinalegasolioself = riep.getPreventivo().getPrezzoalpubblicogasolioself() / 1.22 - cliente.getMarginegasolioself() * riep.getPreventivo().getMarginecessionegasolio() / 100;
                    prezzofinalebenzina = riep.getPreventivo().getPrezzoalpubblicobenzinaself() / 1.22 - cliente.getMarginebenzinaself() * riep.getPreventivo().getMarginecessionebenzina() / 100;
                    prezzofinalesupreme = riep.getPreventivo().getPrezzoalpubblicosupremeself() / 1.22 - cliente.getMarginesupremeself() * riep.getPreventivo().getMarginecessionesupreme() / 100;
                    prezzofinalegpl = riep.getPreventivo().getPrezzoalpubblicogplservito() / 1.22 - cliente.getMarginegplservito() * riep.getPreventivo().getMarginecessionegpl() / 100;
                } else {
                    prezzofinalegasolioself = riep.getPreventivo().getPrezzoalpubblicogasolioservito() / 1.22 - cliente.getMarginegasolioservito() * riep.getPreventivo().getMarginecessionegasolio() / 100;
                    prezzofinalebenzina = riep.getPreventivo().getPrezzoalpubblicobenzinaservito() / 1.22 - cliente.getMarginebenzinaservito() * riep.getPreventivo().getMarginecessionebenzina() / 100;
                    prezzofinalesupreme = riep.getPreventivo().getPrezzoalpubblicosupremeservito() / 1.22 - cliente.getMarginesupremeservito() * riep.getPreventivo().getMarginecessionesupreme() / 100;
                    prezzofinalegpl = riep.getPreventivo().getPrezzoalpubblicogplservito() / 1.22 - cliente.getMarginegplservito() * riep.getPreventivo().getMarginecessionegpl() / 100;
                }
            }
      //      prezzofinalegasolioself = arrotondaallasecondacifra(prezzofinalegasolioself);
      //      prezzofinalebenzina = arrotondaallasecondacifra(prezzofinalebenzina);
      //      prezzofinalesupreme = arrotondaallasecondacifra(prezzofinalesupreme);
      //      prezzofinalegpl = arrotondaallasecondacifra(prezzofinalegpl);


            createCell(row, columnCount++, prezzofinalegasolioself, currencyStyle);
            createCell(row, columnCount++, prezzofinalebenzina, currencyStyle);
            createCell(row, columnCount++, prezzofinalesupreme, currencyStyle);
            createCell(row, columnCount++, prezzofinalegpl, currencyStyle);


            int numero = 0;
            for(Bonifico b : riep.getListabonifici()){
                createCell(row, columnCount++, b.getImportobonifico(), currencyStyle);
                createCell(row, columnCount++, b.getDescrizione(), style);
                numero++;
            }

            for( ; numero<=5; numero++){
                createCell(row, columnCount++, " ", style);
            }



        }
     watch.stop();
        System.out.println("TOTALE TEMPO ESECUZIONE METODO IN MILLISECONDI: "
                + watch.getTotalTimeMillis());


    }


    public SXSSFWorkbook export() {
        writeHeaderLine();
        writeDataLines();
        return work2;

    }

    public double arrotondaallasecondacifra(double a){
        double roundOff = Math.round(a*100)/100;
        return roundOff;
    }


}
