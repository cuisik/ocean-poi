package io.sssd.model;

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
}
