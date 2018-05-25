import io.sssd.ocean.poi.open.i.EntityCheck;

import java.util.Date;

public class TestObj implements EntityCheck {

    private Integer a;

    private Integer b;

    private Integer c;

    private Date d;


    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public void throwEx() {
//        if (d == null) {
//            throw new NullPointerException("");
//        }
    }

    public boolean eliminate() {
        // 判断
        if (b == null) {
            System.out.println(" 如果b为kong 则丢失这个对象"
            );
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d=" + d +
                '}';
    }
}