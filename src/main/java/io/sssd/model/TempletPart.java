package io.sssd.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class TempletPart {


    private String header;

    private Field[] fields;

    private Collection data;

    private String footer;

    private boolean showTitle = true;

    public TempletPart header(String header) {
        this.header = header;
        return this;
    }


    private Integer breath;

    private Integer depth;

    private HashMap fieldMap;

    public TempletPart fields(Field[] fields) {
        this.fields = fields;
        int breath = 0;
        int depth = 0;
        for (Field field : fields) {
            breath = field.getBreath() + breath;
            depth = depth > field.getDepth() ? depth : field.getDepth();
        }
        this.breath = breath;
        this.depth = depth;
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

    private TempletPart() {
    }

    public TempletPart(String key, Class entityClass) {
        this.key = key;
        this.entityClass = entityClass;
    }

    private Integer realRowNum;

    private Integer headerRowNum;

    private Integer opTitleRowNum;

    private Integer edTitleRowNum;

    private Integer opDataRowNum;

    private Integer edDataRowNum;

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

    public Integer getRealRowNum() {
        return realRowNum;
    }

    public Integer getHeaderRowNum() {
        return headerRowNum;
    }

    public Integer getOpTitleRowNum() {
        return opTitleRowNum;
    }

    public Integer getEdTitleRowNum() {
        return edTitleRowNum;
    }

    public Integer getOpDataRowNum() {
        return opDataRowNum;
    }

    public Integer getEdDataRowNum() {
        return edDataRowNum;
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
                int mergeRow = this.depth;
                int mergeRank = field.getBreath();
                Integer[] is = new Integer[]{initRow, initRank, mergeRow, mergeRank};
                hashMap.put(field.getFieldName(), is);
                initRank = mergeRank;
            }else {


            }
        }
        return hashMap;
    }
}
