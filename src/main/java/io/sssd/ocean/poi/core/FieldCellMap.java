package io.sssd.ocean.poi.core;

import java.text.DateFormat;

class FieldCellMap {

    // cell角标
    private int index;
    // excel 字段名
    private String columnName;
    // 对应的实体类字段名
    private String fieldName;
    // 日期适配器
    private DateFormat dateFormat;


    public FieldCellMap(String columnName, String fieldName) {
        this.columnName = columnName;
        this.fieldName = fieldName;
    }

    public FieldCellMap(String columnName, String fieldName, DateFormat dateFormat) {
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.dateFormat = dateFormat;
    }

    public FieldCellMap(int index, String fieldName) {
        this.index = index;
        this.fieldName = fieldName;
    }

    public FieldCellMap(int index, String fieldName, DateFormat dateFormat) {
        this.index = index;
        this.fieldName = fieldName;
        this.dateFormat = dateFormat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
}
