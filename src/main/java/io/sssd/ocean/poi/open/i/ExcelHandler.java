package io.sssd.ocean.poi.open.i;

import io.sssd.ocean.poi.core.FieldMap;
import io.sssd.ocean.poi.open.model.Templet;
import io.sssd.ocean.poi.open.model.TempletItem;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

public interface ExcelHandler {

    /**
     * 数据写入Excel
     *
     * @param outputStream
     * @param templets
     * @throws IOException
     */
    void dataToExcel(OutputStream outputStream, Templet... templets) throws IOException;

    /**
     * 带文件加密的数据写入Excel
     *
     * @param outputStream
     * @param confirm
     * @param templets
     * @throws IOException
     * @throws GeneralSecurityException
     * @throws InvalidFormatException
     */
    void dataToCrypToExcel(OutputStream outputStream, String confirm, Templet... templets) throws IOException, GeneralSecurityException, InvalidFormatException;


    <T> List<T> excelToList(InputStream inputStream, TempletItem templetItem, Class<T> entityClass);

    /**
     * 简单导出
     *
     * @param inputStream
     * @param sheetNum
     * @param fieldNum
     * @param startDataNum
     * @param fieldMap
     * @param entityClass
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> List<T> simpleDataToList(InputStream inputStream, int sheetNum, int fieldNum, int startDataNum, FieldMap fieldMap, Class<T> entityClass) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, ParseException;

}