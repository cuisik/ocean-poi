package io.sssd.ocean.poi.core;

import io.sssd.ocean.poi.open.model.Templet;
import io.sssd.ocean.poi.open.model.TempletPart;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

public class HSSFHandler {


    public void dataToExcel(OutputStream outputStream, Templet... templets) throws IOException{
        try {
            dataToCryptoExcel(outputStream,null,templets);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }


    public void dataToCryptoExcel(OutputStream outputStream, String confirm, Templet... templets) throws IOException, GeneralSecurityException, InvalidFormatException {
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

            TempletPart[] parts = templet.getTempletParts();
            boolean addSpace = false;
            for (TempletPart part : parts) {
                context.setTempletPart(part);
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


}
