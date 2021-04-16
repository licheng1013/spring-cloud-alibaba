import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 14:49
 */
@Slf4j
public class SeataTest {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Convert.hexToStr("e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2" , StandardCharsets.UTF_8));
        int n = 60;
        long sleepTime = 6000;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String t = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN);
            int r = RandomUtil.randomInt(1, 1001);
            if  (r <= 6) {
                if (map.get(t) == null) {
                    String v = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN);
                    if (t.equals(v)) {
                        map.put(t, 1);
                    }
                }else {
                    String v = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN);
                    Integer integer = map.get(t);
                    if (t.equals(v)) {
                        map.put(t, integer+1);
                    }
                }
            }
        }
        log.info("随机结果: {}", map);
    }
}
