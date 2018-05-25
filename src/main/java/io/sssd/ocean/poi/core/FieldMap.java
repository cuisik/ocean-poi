package io.sssd.ocean.poi.core;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldMap {

    // 存放有角标的字段
    private List<FieldCellMap> list;
    // 存放无角标的的字段
    private Map<String, FieldCellMap> map;

    public FieldMap() {
        list = new ArrayList<FieldCellMap>();
        map = new HashMap<String, FieldCellMap>();
    }


    public FieldMap put(int index, String fieldName) {
        list.add(new FieldCellMap(index, fieldName));
        return this;
    }

    public FieldMap put(int index, String fieldName, DateFormat dateFormat) {
        list.add(new FieldCellMap(index, fieldName, dateFormat));
        return this;
    }


    public FieldMap put(String columnName, String fieldName) {
        map.put(columnName, new FieldCellMap(columnName, fieldName));
        return this;
    }

    public FieldMap put(String columnName, String fieldName, DateFormat dateFormat) {
        map.put(columnName, new FieldCellMap(columnName, fieldName, dateFormat));
        return this;
    }

    protected List<FieldCellMap> getList() {
        return list;
    }

    protected Map<String, FieldCellMap> getMap() {
        return map;
    }

    protected boolean completionIndex() {
        return !map.isEmpty();
    }

}
