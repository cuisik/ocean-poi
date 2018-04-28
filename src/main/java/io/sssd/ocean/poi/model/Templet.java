package io.sssd.ocean.poi.model;

import io.sssd.ocean.poi.core.i.CycleRowFIll;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class Templet {

    private String sheetName;

    private TempletPart[] templetParts;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public TempletPart[] getTempletParts() {
        return templetParts;
    }

    public void setTempletParts(TempletPart[] templetParts) {
        this.templetParts = templetParts;
    }


    private CycleRowFIll headerRowFIll;
    private CycleRowFIll spaceRowFIll;
    private CycleRowFIll footerRowFIll;



}
