package io.sssd.ocean.poi.core;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Created by MIAOM on 2018/4/28.
 */
public class SheetBox {

    private Sheet sheet;

    private int rowNum;

    protected SheetBox(Sheet sheet, int rowNum) {
        this.sheet = sheet;
        this.rowNum = rowNum;
    }

    public Row nextRow() {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        rowNum++;
        return row;
    }


    public Row skipRow() {
        return skipRow(1);
    }

    public Row skipRow(int num) {
        rowNum = rowNum + num;
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        rowNum++;
        return row;
    }

    public int skipNum() {
        return skipNum(1);
    }

    // 返回跳过后的行号
    public int skipNum(int num) {
        int rn;
        rowNum = rowNum + num;
        rn = rowNum;
        rowNum++;
        return rn;
    }

}
