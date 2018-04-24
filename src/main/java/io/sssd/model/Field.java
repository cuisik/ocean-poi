package io.sssd.model;

import java.util.Arrays;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class Field {

    private String fieldName;

    private String likeName;

    protected String getFieldName() {
        return fieldName;
    }

    protected String getLikeName() {
        return likeName;
    }

    private boolean isProtect;

    private String formula;

    private boolean isLeaf = true;

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

    protected Field[] getChildFiles() {
        return childFiles;
    }


    public Field(String fieldName, String likeName, Field[] childFiles) {
        this.fieldName = fieldName;
        this.likeName = likeName;
        if (childFiles.length != 0 && childFiles != null) {
            this.childFiles = childFiles;
            this.isLeaf = false;
        }
    }

    public Field(String fieldName, Field[] childFiles) {
        this.fieldName = fieldName;
        this.likeName = fieldName;
        if (childFiles.length != 0 && childFiles != null) {
            this.childFiles = childFiles;
            this.isLeaf = false;
        }
    }

    // 运行时 相关属性
    // 节点的广度 : 有多少个子节点 逐步返回上级
    // 节点的深度 : 有多少层子节点 逐步返回上级
    private Integer breath, depth;

    // 合并单元格 行 列
    private int mergeRow, mergeRank;


    // 当前节点或者子节点在合并的情况下 真实宽度
    // 当前节点或者子节点在合并的情况下 真实宽度
    private Integer realHigh, realWidth;

    protected int getOccupyRow() {
        return 1 + this.mergeRow;
    }

    protected int getOccupyRank() {
        return 1 + this.mergeRank;
    }

    public Field mergeRow(int mergeRow) {
        this.mergeRow = mergeRow;
        return this;
    }

    public Field mergeRank(int mergeRank) {
        this.mergeRank = mergeRank;
        return this;
    }

    protected Integer getRealHigh() {
        if (this.realHigh == null) {
            if (this.isLeaf) {
                this.realHigh = getOccupyRow();
            } else {
                this.realHigh = reRealHigh(childFiles);
            }
        }
        return realHigh;
    }

    protected Integer getRealWidth() {
        if (this.realWidth == null) {
            if (this.isLeaf) {
                this.realWidth = getOccupyRank();
            } else {
                this.realWidth = reRealHigh(childFiles);
            }
        }
        return realWidth;
    }


    protected Integer getBreath() {
        if (this.breath == null) {
            if (this.isLeaf) {
                this.breath = 1;
            } else {
                this.breath = reBreath(childFiles);
            }
        }
        return breath;
    }

    protected Integer getDepth() {
        if (this.depth == null) {
            if (this.isLeaf) {
                this.depth = 1;
            } else {
                this.depth = reDepth(childFiles);
            }
        }
        return depth;
    }

    protected boolean isLeaf() {
        return isLeaf;
    }


    /**
     * 工具方法区域
     */

    private int reRealWidth(Field[] childFiles) {
        int re = 0;
        for (Field field : childFiles) {
            re = re + field.getRealWidth();
        }
        return re;
    }

    private int reRealHigh(Field[] childFiles) {
        int re = 0;
        for (Field field : childFiles) {
            re = re > field.getRealHigh() ? re : field.getRealHigh();
        }
        return re + 1;
    }

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

    private int[] reBreathAndRealWidth(Field[] childFiles) {
        int reBreath = 0;
        int reRealWidth = 0;
        for (Field field : childFiles) {
            reBreath = reBreath + field.getBreath();
            reRealWidth = reRealWidth + field.getRealWidth();
        }
        return new int[]{reBreath, reRealWidth};
    }

    private int[] reDepthAndRealHigh(Field[] childFiles) {
        int reDepth = 0;
        int reRealHigh = 0;
        for (Field field : childFiles) {
            reDepth = reDepth > field.getDepth() ? reDepth : field.getDepth();
            reRealHigh = reRealHigh > field.getRealHigh() ? reRealHigh : field.getRealHigh();
        }
        reDepth = reDepth + 1;
        reRealHigh = reRealHigh + 1 + mergeRow;
        return new int[]{reDepth, reRealHigh};
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                ", likeName='" + likeName + '\'' +
                ", isProtect=" + isProtect +
                ", formula='" + formula + '\'' +
                ", isLeaf=" + isLeaf +
                ", childFiles=" + Arrays.toString(childFiles) +
                ", breath=" + breath +
                ", depth=" + depth +
                ", mergeRow=" + mergeRow +
                ", mergeRank=" + mergeRank +
                ", realHigh=" + realHigh +
                ", realWidth=" + realWidth +
                '}';
    }
}
