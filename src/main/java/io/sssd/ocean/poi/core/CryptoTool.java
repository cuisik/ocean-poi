package io.sssd.ocean.poi.core;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.security.GeneralSecurityException;

public class CryptoTool {


    public static OutputStream encryptWorkbookStream(OutputStream outputStream, Workbook workbook, String confirm) throws IOException, InvalidFormatException, GeneralSecurityException {
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


    private static ByteArrayInputStream parseStream(OutputStream out) {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream;
    }

}
