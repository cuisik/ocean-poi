package io.sssd.ocean.poi.core;

import io.sssd.ocean.poi.open.i.ExcelHandler;
import io.sssd.ocean.poi.open.model.Templet;
import io.sssd.ocean.poi.open.model.TempletItem;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HSSFHandler implements ExcelHandler {


    public void dataToExcel(OutputStream outputStream, Templet... templets) throws IOException{
        try {
            dataToCrypToExcel(outputStream, null, templets);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }


    public void dataToCrypToExcel(OutputStream outputStream, String confirm, Templet... templets) throws IOException, GeneralSecurityException, InvalidFormatException {
        HSSFWorkbook workbook = new HSSFWorkbook();

        for (Templet templet : templets) {
            String sheetName = templet.getSheetName();
            HSSFSheet sheet;

            if (StringTool.isNotEmpty(sheetName)) {
                sheet = workbook.createSheet(sheetName);
            } else {
                sheet = workbook.createSheet();
            }
            SheetBox sheetBox = new SheetBox(sheet, 0);
            Context context = new Context();
            context.setTemplet(templet);
            context.setHeader(templet.getHeader());
            templet.getFooterFiller().addRows(sheetBox, context);

            TempletItem[] parts = templet.getTempletItems();
            boolean addSpace = false;
            for (TempletItem part : parts) {
                context.setTempletItem(part);
                part.getContentFiller().addRows(sheetBox, context);
                if (addSpace) {
                    templet.getSpaceFiller().addRows(sheetBox, context);
                }
                addSpace = true;
            }
            context.setFooter(templet.getFooter());
            templet.getFooterFiller().addRows(sheetBox, context);
        }
        if(StringTool.isNotEmpty(confirm)){
            outputStream = CryptoTool.encryptWorkbookStream(outputStream, workbook, confirm);
        }
        workbook.write(outputStream);
        workbook.close();
    }

    public <T> List<T> excelToList(InputStream inputStream, TempletItem templetItem, Class<T> entityClass) {
        List<T> reList = new ArrayList<T>();
        return reList;
    }

    public <T> List<T> simpleDataToList(InputStream inputStream, int fieldRow, int startDataRow, Map<String, String> fieldMap, Class<T> entityClass) throws IOException {
        List<T> reList = new ArrayList<T>();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        HSSFSheet hssfSheet = workbook.getSheetAt(0);


        return reList;
    }

}
