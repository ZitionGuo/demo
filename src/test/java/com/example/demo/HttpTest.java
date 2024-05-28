package com.example.demo;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author guozixuan
 * @date 2022/6/1 14:12
 */
@Slf4j
public class HttpTest {

    @Test
    public void test1() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("shopId", "9010");
        paramMap.put("firstCategoryId", "31007305");
        paramMap.put("secondCategoryId", "31022991");

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("开始调用");
                    String result = HttpUtil.post("https://management.yonghuivip.com/product-rest/discount/getskucodelist", paramMap, 1000);
                    System.out.println(JSON.toJSONString(result));
                } catch (Exception e) {
                    System.out.println("======此次调用错误, error:" + e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("等待异常");
        }
        System.out.println("运行结束");
    }

    @Test
    public void test2() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("shopId", "WTT095");

        ExecutorService executorService = Executors.newFixedThreadPool(40);
        CountDownLatch countDownLatch = new CountDownLatch(40);
        for (int i = 0; i < 40; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("开始调用");
                    String result = HttpUtil.post("https://management.yonghuivip.com/product-rest/discount/getcategoryinfo", paramMap, 9000);
                    System.out.println(JSON.toJSONString(result));
                } catch (Exception e) {
                    System.out.println("======此次调用错误, error:" + e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("等待异常");
        }
        System.out.println("运行结束");
    }

    @Test
    public void testData() {
        String json = "[\n" +
                "    {\n" +
                "        \"shopId\": \"3T01\",\n" +
                "        \"skuCode\": \"R-1005445\"\n" +
                "    }\n" +
                "]";
        String result = HttpUtil.post("https://hc-pre.yonghuivip.com/app/api/sku-hc-hub/skuStore/updateBatchPicking", json, 9000);

    }

    @Test
    public void testPost() throws InterruptedException {
        for (String item : Constant.INFO_LIST) {
            String[] array = item.split("_");
            String skuCode = array[0];
            String shopId = array[1];
            String str = "10.247.177.124:12300/categoryindexmanage/reindexsku?shopId=" + shopId + "&skuCode=" + skuCode;
            System.out.println(str);
            Thread.sleep(100);
            String result = HttpUtil.post(str, "");
            System.out.println(result);
        }
    }

    @Test
    public void testReIndexShopSku() throws InterruptedException {
        // 门店
        for (String shopId : Constant.INFO_LIST) {
            String str = "10.247.177.124:12300/categoryindexmanage/reindexshopsku?shopId=" + shopId;
            HttpUtil.post(str, "");
            System.out.println("门店重建, shopId:" + shopId);
            TimeUnit.SECONDS.sleep(45);
        }
    }

    @Test
    public void testMap() {
        List<Map<Long, Set<String>>> list = Lists.newArrayList();
        Map<Long, Set<String>> discountCategoryInfoMap = Maps.newConcurrentMap();
        Map<Long, Set<String>> sellingPriceCategoryInfoMap = Maps.newConcurrentMap();
        discountCategoryInfoMap.put(10L, Sets.newHashSet("1"));
        discountCategoryInfoMap.put(20L, Sets.newHashSet("1"));
        sellingPriceCategoryInfoMap.put(20L, Sets.newHashSet("1"));
        list.add(discountCategoryInfoMap);
        list.add(sellingPriceCategoryInfoMap);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testUtils() {
        long l = Long.parseLong("1234");
        System.out.println(l);
    }


}
