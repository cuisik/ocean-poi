package io.sssd.ocean.poi.open.use;

import io.sssd.ocean.poi.core.Context;
import io.sssd.ocean.poi.core.SheetBox;
import io.sssd.ocean.poi.core.StringTool;
import io.sssd.ocean.poi.open.i.CycleFiller;
import io.sssd.ocean.poi.open.model.TempletItem;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by MIAOM on 2018/4/30.
 */
public enum CommonFillers implements CycleFiller {

    _TITLE() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            TempletItem TempletItem = context.getTempletItem();
        }
    },

    _CONTENT() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            TempletItem TempletItem = context.getTempletItem();
        }
    },

    _HEADER() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            String header = context.getHeader();
            if (StringTool.isNotEmpty(header)) {
                Row row = sheetBox.nextRow();
                row.getCell(0).setCellValue(header);
            }
        }
    },
    _FOOTER() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            String footer = context.getFooter();
            if (StringTool.isNotEmpty(footer)) {
                Row row = sheetBox.nextRow();
                row.getCell(0).setCellValue(footer);
            }
        }
    },

    _SPACE() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            super.addRows(sheetBox, context);
        }
    };


    public void addRows(SheetBox sheetBox, Context context) {
        sheetBox.skipRow();
    }
}
