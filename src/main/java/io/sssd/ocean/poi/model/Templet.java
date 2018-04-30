package io.sssd.ocean.poi.model;

import io.sssd.ocean.poi.core.open.i.CommonRowFill;
import io.sssd.ocean.poi.core.open.i.CycleRowFIll;

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


    private String header;
    private String space;
    private String footer;

    private CycleRowFIll headerRowFIll = CommonRowFill.TMPL_HEADER;
    private CycleRowFIll spaceRowFIll = CommonRowFill.TMPL_SPACE;
    private CycleRowFIll footerRowFIll = CommonRowFill.TMPL_FOOTER;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }


    public CycleRowFIll getHeaderRowFIll() {
        return headerRowFIll;
    }

    public void setHeaderRowFIll(CycleRowFIll headerRowFIll) {
        this.headerRowFIll = headerRowFIll;
    }

    public CycleRowFIll getSpaceRowFIll() {
        return spaceRowFIll;
    }

    public void setSpaceRowFIll(CycleRowFIll spaceRowFIll) {
        this.spaceRowFIll = spaceRowFIll;
    }

    public CycleRowFIll getFooterRowFIll() {
        return footerRowFIll;
    }

    public void setFooterRowFIll(CycleRowFIll footerRowFIll) {
        this.footerRowFIll = footerRowFIll;
    }
}
