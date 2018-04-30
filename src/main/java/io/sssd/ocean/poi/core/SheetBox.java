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
        Row row = sheet.createRow(rowNum);
        rowNum++;
        return row;
    }

    public int skipRow() {
        rowNum++;
        return rowNum;
    }

    public int skipNum(int num) {
        rowNum = rowNum + num;
        return rowNum;
    }
    // 合并 适用范围：当前行
//    public void


}
