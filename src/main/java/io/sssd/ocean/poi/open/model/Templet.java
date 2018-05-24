package io.sssd.ocean.poi.open.model;


import io.sssd.ocean.poi.open.i.CycleFiller;
import io.sssd.ocean.poi.open.use.CommonFillers;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class Templet {

    private String sheetName;

    private TempletItem[] TempletItems;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public TempletItem[] getTempletItems() {
        return TempletItems;
    }

    public void setTempletItems(TempletItem... TempletItems) {
        this.TempletItems = TempletItems;
    }


    private String header;
    private String footer;

    private CycleFiller headerFiller = CommonFillers._HEADER;
    private CycleFiller spaceFiller = CommonFillers._SPACE;
    private CycleFiller footerFiller = CommonFillers._FOOTER;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }


    public CycleFiller getHeaderFiller() {
        return headerFiller;
    }

    public void setHeaderFiller(CycleFiller headerFiller) {
        this.headerFiller = headerFiller;
    }

    public CycleFiller getSpaceFiller() {
        return spaceFiller;
    }

    public void setSpaceFiller(CycleFiller spaceFiller) {
        this.spaceFiller = spaceFiller;
    }

    public CycleFiller getFooterFiller() {
        return footerFiller;
    }

    public void setFooterFiller(CycleFiller footerFiller) {
        this.footerFiller = footerFiller;
    }
}
