package io.sssd.ocean.poi.core.open.i;

import io.sssd.ocean.poi.core.Context;
import io.sssd.ocean.poi.core.SheetBox;
import io.sssd.ocean.poi.model.TempletPart;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by MIAOM on 2018/4/30.
 */
public enum CommonRowFill implements CycleRowFIll {

    TITLE() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            TempletPart templetPart = context.getTempletPart();
        }
    },

    CONTENT() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            TempletPart templetPart = context.getTempletPart();
        }
    },

    TMPL_HEADER() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            String header = context.getTempletPart().getHeader();
            if (header != null) {
                Row row = sheetBox.nextRow();
                row.getCell(0).setCellValue(header);

            }
        }
    },

    TMPL_SPACE() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            super.addRows(sheetBox, context);
        }
    },

    TMPL_FOOTER() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            super.addRows(sheetBox, context);
        }
    },

    TMPL_PT_HEADER() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            super.addRows(sheetBox, context);
        }
    },

    TMPL_PT_SPACE() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            super.addRows(sheetBox, context);
        }
    },

    TMPL_PT_FOOTER() {
        @Override
        public void addRows(SheetBox sheetBox, Context context) {
            super.addRows(sheetBox, context);
        }
    };


    @Override
    public void addRows(SheetBox sheetBox, Context context) {
        sheetBox.skipRow();
    }
}
