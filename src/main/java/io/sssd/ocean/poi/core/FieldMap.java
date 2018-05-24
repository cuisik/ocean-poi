package io.sssd.ocean.poi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldMap {

    // 存放有角标的字段
    private List<FieldCellMap> list;
    // 存放无角标的的字段
    private Map<String, String> map;

    public FieldMap() {
        list = new ArrayList<>();
        map = new HashMap();
    }

    public FieldMap put(int index, String columnName, String fieldName) {
        list.add(new FieldCellMap(index, columnName, fieldName));
        return this;
    }

    public FieldMap put(String columnName, String fieldName) {
        map.put(columnName, fieldName);
        return this;
    }

    protected List<FieldCellMap> getList() {
        return list;
    }

    protected Map<String, String> getMap() {
        return map;
    }

    protected boolean completionIndex() {
        return !map.isEmpty();
    }

}
