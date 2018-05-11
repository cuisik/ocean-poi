package io.sssd.ocean.poi.open.model;

import io.sssd.ocean.poi.open.i.CycleFiller;
import io.sssd.ocean.poi.open.use.CommonFillers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class TempletPart {


    private String header;

    private Field[] fields;

    private Collection data;

    private String footer;

    private Map map;

    public TempletPart() {
    }

    public Object getValue(String key) {
        return map.get(key);
    }

    public void setValue(String key, Object value) {
        if (map == null) {
            map = new HashMap();
        }
        map.put(key, value);
    }

    private CycleFiller headerFiller = CommonFillers._HEADER;
    private CycleFiller contentFiller = CommonFillers._CONTENT;
    private CycleFiller footerFiller = CommonFillers._FOOTER;

    private boolean showTitle = true;

    public TempletPart header(String header) {

        this.header = header;
        return this;
    }


    private int allBreath;

    private int allDepth;

    private HashMap fieldMap;

    public TempletPart fields(Field[] fields) {
        this.fields = fields;
        int allBreath = 0;
        int allDepth = 0;
        for (Field field : fields) {
            allBreath = field.getBreath() + allBreath;
            allDepth = allDepth > field.getDepth() ? allDepth : field.getDepth();
        }
        this.allBreath = allBreath;
        this.allDepth = allDepth;
        this.fieldMap = reFieldMap(fields);
        return this;
    }

    public TempletPart data(Collection data) {
        this.data = data;
        this.dataCount = data.size();
        return this;
    }

    public TempletPart footer(String footer) {
        this.footer = footer;
        return this;
    }

    public TempletPart hideTitle() {
        this.showTitle = false;
        return this;
    }

    // key
    private String key;

    private Class entityClass;


    public TempletPart(String key, Class entityClass) {
        this.key = key;
        this.entityClass = entityClass;
    }


    private int dataCount;


    /**
     * get
     */

    public String getHeader() {
        return header;
    }

    public Field[] getFields() {
        return fields;
    }

    public Collection getData() {
        return data;
    }

    public String getFooter() {
        return footer;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public String getKey() {
        return key;
    }

    public Class getEntityClass() {
        return entityClass;
    }


    public int getDataCount() {
        return dataCount;
    }

    private HashMap<String, String> reFieldMap(Field[] fields) {
        LinkedHashMap hashMap = new LinkedHashMap();
        return reFieldMap(fields, hashMap);
    }

    private HashMap<String, String> reFieldMap(Field[] fields, HashMap<String, String> hashMap) {
        for (Field field : fields) {
            if (field.isLeaf()) {
                hashMap.put(field.getFieldName(), field.getLikeName());
            } else {
                HashMap<String, String> hashMapc = this.reFieldMap(field.getChildFiles(), hashMap);
                hashMap.putAll(hashMapc);
            }
        }
        return hashMap;
    }

    private HashMap<String, Integer[]> reMergeMap(Field[] fields, HashMap<String, Integer[]> hashMap, int initRow, int initRank) {
        for (Field field : fields) {
            if (field.isLeaf()) {
                int mergeRank = field.getBreath();
                Integer[] is = new Integer[]{initRow, initRank, field.getOccupyRow() + initRow, field.getOccupyRank()};
                hashMap.put(field.getFieldName(), is);
                initRank = initRank + field.getOccupyRank();
            } else {


            }
        }
        return hashMap;
    }

    public CycleFiller getHeaderFiller() {
        return headerFiller;
    }

    public void setHeaderFiller(CycleFiller headerFiller) {
        this.headerFiller = headerFiller;
    }

    public CycleFiller getFooterFiller() {
        return footerFiller;
    }

    public void setFooterFiller(CycleFiller footerFiller) {
        this.footerFiller = footerFiller;
    }

    public CycleFiller getContentFiller() {
        return contentFiller;
    }

    public void setContentFiller(CycleFiller contentFiller) {
        this.contentFiller = contentFiller;
    }
}
