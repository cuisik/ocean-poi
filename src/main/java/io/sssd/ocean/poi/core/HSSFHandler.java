package io.sssd.ocean.poi.core;

import io.sssd.ocean.poi.open.i.EntityCheck;
import io.sssd.ocean.poi.open.i.ExcelHandler;
import io.sssd.ocean.poi.open.model.Templet;
import io.sssd.ocean.poi.open.model.TempletItem;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class HSSFHandler implements ExcelHandler {


    public void dataToExcel(OutputStream outputStream, Templet... templets) throws IOException {
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
        if (StringTool.isNotEmpty(confirm)) {
            outputStream = CryptoTool.encryptWorkbookStream(outputStream, workbook, confirm);
        }
        workbook.write(outputStream);
        workbook.close();
    }

    public <T> List<T> excelToList(InputStream inputStream, TempletItem templetItem, Class<T> entityClass) {
        List<T> reList = new ArrayList<T>();
        return reList;
    }

    public <T> List<T> simpleDataToList(InputStream inputStream, int sheetNum, int fieldNum, int startDataNum, FieldMap fieldMap, Class<T> entityClass) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, ParseException {
        List<T> reList = new ArrayList<T>();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(sheetNum);
        HSSFRow fieldRow = sheet.getRow(fieldNum);

        Iterator<Cell> fieldIterator = fieldRow.cellIterator();

        List<FieldCellMap> list = fieldMap.getList();
        // 需要补全 index
        if (fieldMap.completionIndex()) {
            Map<String, FieldCellMap> map = fieldMap.getMap();
            while (fieldIterator.hasNext()) {
                Cell cell = fieldIterator.next();
                int index = cell.getColumnIndex();
                String value = cell.getStringCellValue();
                if (map.containsKey(value)) {
                    FieldCellMap fieldCellMap = map.remove(value);
                    fieldCellMap.setIndex(index);
                    list.add(fieldCellMap);
                }
            }
            if (!map.isEmpty()) {
                throw new RuntimeException("Excel中有缺失字段");
            }
        }

        boolean openCheck = EntityCheck.class.isAssignableFrom(entityClass);
        // for 循环 填充list
        for (int i = startDataNum; i <= sheet.getLastRowNum(); i++) {
            // 新建要转换的对象
            T entity = entityClass.newInstance();

            HSSFRow row = sheet.getRow(i);

            Map<String, Object> beanMap = new HashMap();
            //拿到全部字段映射信息后 开始获取数据
            for (FieldCellMap fieldCellMap : list) {
                int index = fieldCellMap.getIndex();
                Cell cell = row.getCell(index);
                DateFormat dateFormat = fieldCellMap.getDateFormat();
                if (dateFormat != null) {
                    String fieldName = fieldCellMap.getFieldName();
                    String cellContent = cell.toString();
                    if (cellContent != "") {
                        beanMap.put(fieldName, dateFormat.parse(cellContent));
                    }
                } else {
                    String fieldName = fieldCellMap.getFieldName();
                    String cellContent = cell.toString();
                    beanMap.put(fieldName, cellContent);
                }

            }
            BeanUtils.populate(entity, beanMap);

            if (openCheck) {
                // 强制转型
                EntityCheck check = (EntityCheck) entity;
                check.throwEx();
                if (!check.eliminate()) {
                    reList.add(entity);
                }
            } else {
                reList.add(entity);
            }
        }
        return reList;
    }

}
