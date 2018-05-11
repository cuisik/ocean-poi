import io.sssd.ocean.poi.core.Context;
import io.sssd.ocean.poi.core.HSSFHandler;
import io.sssd.ocean.poi.core.SheetBox;
import io.sssd.ocean.poi.open.i.CycleFiller;
import io.sssd.ocean.poi.open.model.Templet;
import io.sssd.ocean.poi.open.model.TempletPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class Test {


    @org.junit.Test
    public void test1() throws IOException, GeneralSecurityException, InvalidFormatException {

        Templet templet = new Templet();
        TempletPart tp = new TempletPart();
        tp.setValue("aaaa", "sdsd");

        tp.setContentFiller(new CycleFiller() {
            public void addRows(SheetBox sheetBox, Context context) {
                String s = (String) context.getTempletPart().getValue("aaaa");
                Row row = sheetBox.nextRow();
                row.createCell(0).setCellValue(s);
                sheetBox.nextRow().createCell(3).setCellValue("1231");
            }
        });
        templet.setTempletParts(tp);

        HSSFHandler hssfHandler = new HSSFHandler();
        File file = new File("D:\\goods.xls");

        hssfHandler.dataToCryptoExcel(new FileOutputStream(file), "123",templet);

    }


}
