import cn.hutool.core.convert.Convert;

import java.nio.charset.StandardCharsets;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 14:49
 */
public class SeataTest {
    public static void main(String[] args) {
//        DateTime t1 = DateUtil.parse("2021-1-10", DatePattern.NORM_DATE_PATTERN);
//        Date t2 = new Date();
//        //计算两天相差数....
//        long day = DateUtil.betweenDay(t1, t2, false);
//        int [] s = {60,90,120,150,180};
//        HashMap<String, Boolean> map = new HashMap<>();
//        map.put("1", false);
//        map.put("2", false);
//        map.put("3", false);
//        map.put("4", false);
//        map.put("5", false);
//        for (int i = 0;i < s.length;i++) {
//            if (day > s[i] ) {
//                if (map.get((i+1+""))) {
//                    System.out.println("已领取");
//                }else {
//                    System.out.println("未领取");
//                }
//            }else {
//                System.out.println("领取条件未到");
//            }
//        }
        System.out.println(Convert.hexToStr("e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2" , StandardCharsets.UTF_8));
    }
}
