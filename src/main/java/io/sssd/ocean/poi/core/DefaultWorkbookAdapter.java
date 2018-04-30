package io.sssd.ocean.poi.core;

import io.sssd.ocean.poi.core.open.i.WorkbookAdapter;
import io.sssd.ocean.poi.model.Templet;
import io.sssd.ocean.poi.model.TempletPart;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.security.GeneralSecurityException;

/**
 * Created by MIAOM on 2018/4/28.
 */
class DefaultWorkbookAdapter implements WorkbookAdapter {


    /**
     * 文件创建，读取，加密解密 start
     */
    @Override
    public HSSFWorkbook creaetWorkbook2003() {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        return hssfWorkbook;
    }

    @Override
    public XSSFWorkbook creaetWorkbook2007() {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        return xssfWorkbook;
    }


    @Override
    public OutputStream encryptWorkbookStream(OutputStream outputStream, Workbook workbook, String confirm) throws IOException, InvalidFormatException, GeneralSecurityException {
        OutputStream opsTmp = new ByteArrayOutputStream();
        workbook.write(opsTmp);


        EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
        Encryptor enc = info.getEncryptor();
        enc.confirmPassword(confirm);

        InputStream ipsTmp = parseStream(opsTmp);
        opsTmp.close();

        OPCPackage opc = OPCPackage.open(ipsTmp);

        POIFSFileSystem poifsFileSystem = new POIFSFileSystem();
        OutputStream encOpsTmp = enc.getDataStream(poifsFileSystem);
        opc.save(encOpsTmp);
        opc.close();


        poifsFileSystem.writeFilesystem(outputStream);
        poifsFileSystem.close();


        return outputStream;
    }

    @Override
    public HSSFWorkbook fetchWorkbook2003(InputStream inputStream) throws IOException {
        return new HSSFWorkbook(inputStream);
    }

    @Override
    public XSSFWorkbook fetchWorkbook2007(InputStream inputStream) throws IOException {
        return new XSSFWorkbook(inputStream);
    }

    @Override
    public InputStream decryptWorkbookStream(InputStream inputStream, String confirm) throws IOException, GeneralSecurityException {
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
        inputStream.close();
        EncryptionInfo encryptionInfo = new EncryptionInfo(poifsFileSystem);
        Decryptor decryptor = Decryptor.getInstance(encryptionInfo);
        decryptor.verifyPassword(confirm);
        return decryptor.getDataStream(poifsFileSystem);
    }


    /**
     * 文件创建，读取，加密解密 end
     */


    /**
     * 流转换
     *
     * @param out
     * @return
     */

    private static ByteArrayInputStream parseStream(OutputStream out) {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream;
    }


    @Override
    public void fillSheet(Sheet sheet, Templet templet) {

        TempletPart[] parts = templet.getTempletParts();

        SheetBox sheetBox = new SheetBox(sheet,0);
        for (TempletPart part : parts) {



        }


    }


}
