package io.sssd.ocean.poi.core;

import io.sssd.ocean.poi.model.Templet;
import io.sssd.ocean.poi.model.TempletPart;

import java.util.HashMap;

/**
 * Created by MIAOM on 2018/4/28.
 */
public class Context {

    private HashMap map = new HashMap();


    private Templet templet;
    private TempletPart templetPart;
    private Integer cellCount;

    public Templet getTemplet() {
        return (Templet) map.get(Type.templet);
    }

    public Context setTemplet(Templet templet) {
        map.put(Type.templet, templet);
        return this;
    }

    public TempletPart getTempletPart() {
        return (TempletPart) map.get(Type.templetPart);
    }

    public Context setTempletPart(TempletPart templetPart) {
        map.put(Type.templetPart, templetPart);
        return this;
    }

    public Integer getCellCount() {
        return (Integer) map.get(Type.cellCount);
    }

    public Context setCellCount(Integer cellCount) {
        map.put(Type.cellCount, cellCount);
        return this;
    }
}

enum Type {
    templet, templetPart, cellCount
}
