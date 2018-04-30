package io.sssd.ocean.poi.core.open.i;

import io.sssd.ocean.poi.core.Context;
import io.sssd.ocean.poi.core.SheetBox;

/**
 * Created by MIAOM on 2018/4/28.
 * 用于解析模板时做填充
 * <p>
 * 在模板首部 尾部 模板part中间
 */
public interface CycleRowFIll {
    void addRows(SheetBox sheetBox, Context context);
}
