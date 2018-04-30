package io.sssd.ocean.poi.core.open.i;

import io.sssd.ocean.poi.model.Templet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

/**
 * Created by MIAOM on 2018/4/28.
 */
public interface WorkbookAdapter {

    /**
     * 文件创建，读取，加密解密 start
     */

    HSSFWorkbook creaetWorkbook2003();

    XSSFWorkbook creaetWorkbook2007();

    OutputStream encryptWorkbookStream(OutputStream outputStream, Workbook workbook, String confirm) throws IOException, InvalidFormatException, GeneralSecurityException;

    HSSFWorkbook fetchWorkbook2003(InputStream inputStream) throws IOException;

    XSSFWorkbook fetchWorkbook2007(InputStream inputStream) throws IOException;

    InputStream decryptWorkbookStream(InputStream inputStream, String confirm) throws IOException, GeneralSecurityException;


    /**
     * 文件创建，读取，加密解密 end
     */

    /**
     * Sheet 写入
     */
    public void fillSheet(Sheet sheet, Templet templet);


}
