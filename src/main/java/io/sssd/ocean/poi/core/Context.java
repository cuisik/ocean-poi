package io.sssd.ocean.poi.core;


import io.sssd.ocean.poi.open.model.Templet;
import io.sssd.ocean.poi.open.model.TempletPart;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MIAOM on 2018/4/28.
 * 上下文 不允许自由创建
 */
public class Context {

    private Map<String, Object> map = new HashMap();

    protected Context() {

    }

    public Templet getTemplet() {
        return (Templet) map.get(Type._TEMPLET.name());
    }

    public Context setTemplet(Templet templet) {
        map.put(Type._TEMPLET.name(), templet);
        return this;
    }

    public TempletPart getTempletPart() {
        return (TempletPart) map.get(Type._TEMPLET_PART.name());
    }

    public Context setTempletPart(TempletPart templetPart) {
        map.put(Type._TEMPLET_PART.name(), templetPart);
        return this;
    }

    public String getHeader() {
        return (String) map.get(Type._HEADER.name());
    }

    public Context setHeader(String header) {
        map.put(Type._HEADER.name(), header);
        return this;
    }

    public String getFooter() {
        return (String) map.get(Type._FOOTER.name());
    }

    public Context setFooter(String footer) {
        map.put(Type._FOOTER.name(), footer);
        return this;
    }

    public Object getValue(String key) {
        return map.get(key);
    }

    public Context setValue(String key, Object o) {
        map.put(key, o);
        return this;
    }
}

enum Type {
    _HEADER,
    _FOOTER,
    _TEMPLET,
    _TEMPLET_PART;
}
