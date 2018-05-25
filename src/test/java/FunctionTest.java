import io.sssd.ocean.poi.core.Context;
import io.sssd.ocean.poi.core.FieldMap;
import io.sssd.ocean.poi.core.HSSFHandler;
import io.sssd.ocean.poi.core.SheetBox;
import io.sssd.ocean.poi.open.i.CycleFiller;
import io.sssd.ocean.poi.open.model.Templet;
import io.sssd.ocean.poi.open.model.TempletItem;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class FunctionTest {


    @Test
    public void test1() throws IOException, GeneralSecurityException, InvalidFormatException {

        Templet templet = new Templet();
        TempletItem tp = new TempletItem();
        tp.setValue("aaaa", "sdsd");

        tp.setContentFiller(new CycleFiller() {
            public void addRows(SheetBox sheetBox, Context context) {
                String s = (String) context.getTempletItem().getValue("aaaa");
                Row row = sheetBox.nextRow();
                row.createCell(0).setCellValue(s);
                sheetBox.nextRow().createCell(3).setCellValue("1231");
            }
        });
        templet.setTempletItems(tp);

        HSSFHandler hssfHandler = new HSSFHandler();
        File file = new File("D:\\goods.xls");

        hssfHandler.dataToCrypToExcel(new FileOutputStream(file), "123", templet);
    }

    @Test
    public void test2() throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, ParseException {


        HSSFHandler hssfHandler = new HSSFHandler();

        FileInputStream fileInputStream = new FileInputStream("D://大乐透.xls");
        FieldMap fieldMap = new FieldMap();
        fieldMap.put(0, "a");
        fieldMap.put(1, "d", new SimpleDateFormat("yyyy-MM-dd"));
        fieldMap.put(2, "b");
        fieldMap.put(3, "c");
        List<TestObj> list = hssfHandler.simpleDataToList(fileInputStream, 0, 2, 3, fieldMap, TestObj.class);

        for (TestObj testObj : list) {
            System.out.println(testObj);
        }

    }

    @Test
    public void test3() {
        boolean b = BB.class.isAssignableFrom(AA.class);
        System.out.println(b);
    }


}

