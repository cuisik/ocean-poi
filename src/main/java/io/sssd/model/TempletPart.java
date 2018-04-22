package io.sssd.model;


import java.util.Collection;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class TempletPart {


    private String header;

    private Field[] fields;

    private Collection data;

    private boolean showTitle = true;



    // key
    private String key;

    private Class entityClass;

    public TempletPart(String key, Class entityClass) {
        this.key = key;
        this.entityClass = entityClass;
    }

    private Integer titleRowNumber;

    private Integer startRowNumber;

    private Integer endRowNumber;


    private int dataCount;

}
