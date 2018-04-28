package io.sssd.ocean.poi.core;


import io.sssd.ocean.poi.core.i.CycleRowFIll;
import io.sssd.ocean.poi.core.i.WorkbookAdapter;
import io.sssd.ocean.poi.model.Templet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIAOM on 2018/4/28.
 */
public class OceanPoiExcel {

    private static ExcelHandler excelHandler;

    private static ExcelHandler getHandler() {
        if (excelHandler == null) {
            excelHandler = new ExcelHandler(new DefaultWorkbookAdapter());
        }
        return excelHandler;
    }

    public static void dataToExcel() {


    }

    public static List excelToData(Class Clazz) {

        return null;
    }

    public static Templet[] excelToData() {

        return null;
    }


}


class ExcelHandler {
    private WorkbookAdapter workbookAdapter;

    public ExcelHandler(WorkbookAdapter workbookAdapter) {
        this.workbookAdapter = workbookAdapter;
    }

    public void dataT0Excel2003(List list, OutputStream outputStream) {
        HSSFWorkbook hssfWorkbook = workbookAdapter.creaetWorkbook2003();
        hssfWorkbook.sheetIterator();

    }


    public void dataT0Excel2007(List list, OutputStream outputStream) {
        XSSFWorkbook xssfWorkbook = workbookAdapter.creaetWorkbook2007();
        xssfWorkbook.sheetIterator();
        Sheet sheet = xssfWorkbook.sheetIterator().next();
        final List list1 = new ArrayList();
        new CycleRowFIll() {
            @Override
            public void addRows(SheetBox sheetBox, int cellCount) {
                Row row = sheetBox.nextRow();
                System.out.println(list1.size());
            }
        };
    }
}
