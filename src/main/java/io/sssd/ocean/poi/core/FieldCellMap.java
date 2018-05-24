package io.sssd.ocean.poi.core;

class FieldCellMap {

    // cell角标
    private int index;
    // excel 字段名
    private String columnName;
    // 对应的实体类字段名
    private String fieldName;

    public FieldCellMap(int index, String columnName, String fieldName) {
        this.index = index;
        this.columnName = columnName;
        this.fieldName = fieldName;
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
}
