import io.sssd.ocean.poi.open.i.EntityCheck;

import java.util.Date;

public class TestObj implements EntityCheck {

    private int a;

    private String b;

    private String c;

    private Date d;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
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
        if (b == "") {
            System.out.println(" 如果b为kong 则丢失这个对象"
            );
            return true;
        }
        return false;
    }
}