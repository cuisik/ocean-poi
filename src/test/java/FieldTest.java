import io.sssd.ocean.poi.core.Context;
import io.sssd.ocean.poi.core.SheetBox;
import io.sssd.ocean.poi.core.i.CycleRowFIll;
import io.sssd.ocean.poi.model.Field;
import org.junit.Test;

/**
 * Created by MIAOM on 2018/4/22.
 */
public class FieldTest {


    @Test
    public void test1() {

        Field aaa = new Field("aaa", "aaa");
        Field bbb = new Field("bbb");
        Field ccc = new Field("ccc");
        Field ddd = new Field("ddd");

        Field eee = new Field("eee", new Field[]{aaa, bbb, ccc, ddd});

        Field fff = new Field("fff");
        Field ggg = new Field("ggg");

        Field hhh = new Field("hhh", new Field[]{ggg, fff, eee});

        Field jjj = new Field("jjj", new Field[]{hhh});

        Field kkk = new Field("kkk", new Field[]{jjj});
        System.out.println(hhh.toString());
        System.out.println(kkk.toString());

    }

    @Test
    public void test2() {

        String sss = "aaa";

        new CycleRowFIll() {
            @Override
            public void addRows(SheetBox sheetBox, Context context) {

            }
        };
    }
}
