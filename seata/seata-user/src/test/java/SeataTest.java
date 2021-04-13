import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.HashMap;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 14:49
 */
public class SeataTest {
    public static void main(String[] args) {
        DateTime t1 = DateUtil.parse("2021-1-10", DatePattern.NORM_DATE_PATTERN);
        Date t2 = new Date();
        long day = DateUtil.betweenDay(t1, t2, false);
        int [] s = {60,90,120,150,180};
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("1", false);
        map.put("2", false);
        map.put("3", false);
        map.put("4", false);
        map.put("5", false);
        for (int i = 0;i < s.length;i++) {
            if (day > s[i] ) {
                if (map.get((i+1+""))) {
                    System.out.println("已领取");
                }else {
                    System.out.println("未领取");
                }
            }else {
                System.out.println("领取条件未到");
            }
        }
    }
}
