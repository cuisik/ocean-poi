package io.sssd.model;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class Field {

    private String fieldName;

    private String likeName;

    private boolean isProtect;

    private String formula;

    public Field(String fieldName, String likeName) {
        this.fieldName = fieldName;
        this.likeName = likeName;
        this.breath = 1;
        this.depth = 1;
    }

    public Field(String fieldName) {
        this.fieldName = fieldName;
        this.likeName = fieldName;
        this.breath = 1;
        this.depth = 1;
    }

    public Field(String fieldName, String likeName, boolean isProtect, String formula) {
        this.fieldName = fieldName;
        this.likeName = likeName;
        this.isProtect = isProtect;
        this.formula = formula;
        this.breath = 1;
        this.depth = 1;
    }

    public Field(String fieldName, boolean isProtect, String formula) {
        this.fieldName = fieldName;
        this.likeName = fieldName;
        this.isProtect = isProtect;
        this.formula = formula;
        this.breath = 1;
        this.depth = 1;
    }

    private Field[] childFiles;

    private int reBreath(Field[] childFiles) {
        int re = 0;
        for (Field field : childFiles) {
            re = re + field.getBreath();
        }
        return re;
    }

    private int reDepth(Field[] childFiles) {
        int re = 0;
        for (Field field : childFiles) {
            re = re > field.getDepth() ? re : field.getDepth();
        }
        return re + 1;
    }


    public Field(String fieldName, String likeName, Field[] childFiles) {
        this.fieldName = fieldName;
        this.likeName = likeName;
        this.childFiles = childFiles;
        this.breath = reBreath(childFiles);
        this.depth = reDepth(childFiles);
    }

    public Field(String fieldName, Field[] childFiles) {
        this.fieldName = fieldName;
        this.likeName = fieldName;
        this.childFiles = childFiles;
        this.breath = reBreath(childFiles);
        this.depth = reDepth(childFiles);
    }

    // 运行时 相关属性
    // 节点的广度 : 有多少个子节点 逐步返回上级
    private int breath;
    // 节点的深度 : 有多少层子节点 逐步返回上级
    private int depth;

    public int getBreath() {
        return breath;
    }

    public int getDepth() {
        return depth;
    }


}
