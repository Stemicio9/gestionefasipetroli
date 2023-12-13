package com.petroli.gestionefasipetroli.utils;

import com.petroli.gestionefasipetroli.dto.RiepilogoPerFrontend;
import com.petroli.gestionefasipetroli.entities.QuotazioneGiornaliera;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.List;

public class GeneraExcelPrezziFornitori {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<QuotazioneGiornaliera> listariepilogo;
    private String nomefornitore;


    public GeneraExcelPrezziFornitori(List<QuotazioneGiornaliera> quotazione, String nomefornitore) {
        this.nomefornitore = nomefornitore;
        this.listariepilogo = quotazione;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Quotazioni " + nomefornitore);

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Data", style);
        createCell(row, 1, "Prezzo Gasolio", style);
        createCell(row, 2, "Prezzo Benzina", style);
        createCell(row, 3, "Prezzo Supreme", style);
        createCell(row, 4, "Prezzo GPL", style);

    }


    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
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
        int rowCount = 1;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        CellStyle currencyStyle;
        XSSFDataFormat cf = workbook.createDataFormat();
        currencyStyle = workbook.createCellStyle();
        currencyStyle.setDataFormat(cf.getFormat("€#,##0.00_);[Red]€#,##0.00)"));

        for (QuotazioneGiornaliera riep : listariepilogo) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, sdf.format(riep.getData()), style);
            createCell(row, columnCount++, riep.getPrezzogasolio(), currencyStyle);
            createCell(row, columnCount++, riep.getPrezzobenzina(), currencyStyle);
            createCell(row, columnCount++, riep.getPrezzosupreme(), currencyStyle);
            createCell(row, columnCount++, riep.getPrezzogpl(), currencyStyle);
        }
    }


    public XSSFWorkbook export() {
        writeHeaderLine();
        writeDataLines();
        return workbook;

    }

}
