package com.example.demo;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author guozixuan
 * @date 2024/4/8 19:12
 */
public class PlainTest {

    public static final List<String> skuCodeList = Lists.newArrayList(
            "R-1654597", "R-1727237", "R-1082548", "R-797062", "R-1194915", "R-822923", "R-1599012", "R-1177174", "R-976342", "R-851744", "R-1023211", "R-842735", "R-791981", "R-1607859", "R-1248784", "R-1237574", "R-1179291", "R-1257145", "R-910762", "R-834280", "R-1257148", "R-1308053", "R-1116451", "R-1593269", "R-1604217", "R-1613335", "R-1612503", "R-829771", "R-1088804", "R-910767", "R-910763", "R-1638474", "R-1144123", "R-1635151", "R-1148171", "R-1257147", "R-1627128", "R-1627129", "R-1603026", "R-787322", "R-1363484", "R-1202572", "R-977469", "R-1516911", "R-1081946", "R-1060133", "R-1226078", "R-1099571", "R-1081947", "R-1094840", "R-CB61183408", "R-1060162", "R-1082333", "R-1390350", "R-1611883", "R-1068765", "R-835129", "R-1150775", "R-976359", "R-954529", "R-1441952", "R-973820", "R-1561831", "R-1195493", "R-1199626", "R-1282357", "R-1438431", "R-1352217", "R-1196247", "R-1082278", "R-1021284", "R-1438425", "R-1438424", "R-1676552", "R-1060149", "R-1144211", "R-1577827", "R-1623266", "R-829530", "R-734353", "R-1520263", "R-839323", "R-797142", "R-1194971", "R-1451571", "R-1060164", "R-1231627", "R-844876", "R-949308", "R-1194935", "R-1248786", "R-839432", "R-1097577", "R-1124153", "R-847981", "R-1227344", "R-1240748", "R-874904", "R-844780", "R-1124110", "R-949494", "R-1194926", "R-1148188", "R-1262878", "R-1099970", "R-1115655", "R-1082252", "R-869222", "R-915311", "R-789429", "R-1595951", "R-1578114", "R-1082249", "R-1220169", "R-1195517", "R-1225166", "R-1143658", "R-1441961", "R-1060132", "R-1082513", "R-931285", "R-1115602", "R-1217103", "R-1118538", "R-1180453", "R-1225438", "R-1194901", "R-1194923", "R-1082044", "R-1231733", "R-1260894", "R-1226098", "R-781289", "R-1697941", "R-1695797", "R-1594387", "R-1196245", "R-1177173", "R-1594431", "R-927299", "R-1520260", "R-1520229", "R-1219073", "R-1579155", "R-1081922", "R-908417", "R-822952", "R-1082456", "R-1060145", "R-1525040", "R-1594269", "R-1216061", "R-651853", "R-1602689", "R-1602720", "R-1216063", "R-747352", "R-839350", "R-1209902", "R-833188", "R-1144437", "R-1082363", "R-1227464", "R-1725065", "R-1725068", "R-1668608", "R-1060168", "R-1115658", "R-1390349", "R-1251092", "R-1202592", "R-1701701", "R-974530", "R-943274", "R-1226024", "R-833753", "R-976467", "R-1199538", "R-1390328", "R-911983", "R-844771", "R-1605751", "R-1215215", "R-839377", "R-1143804", "R-978245", "R-1099941", "R-822969", "R-1099911", "R-1060170", "R-1199568", "R-1216060", "R-1624869", "R-1602686", "R-1621726", "R-961051", "R-1633152", "R-1083612", "R-961005", "R-933363", "R-871670", "R-1120923", "R-976402", "R-949223", "R-1191997", "R-981626", "R-1463617", "R-1656166", "R-949235", "R-976413", "R-1166772", "R-1633057", "R-1064282", "R-1041092", "R-1635184", "R-1693557", "R-1149988", "R-1248218", "R-1321221", "R-910385", "R-1041080", "R-1125482", "R-1149973", "R-871718", "R-1633672", "R-1099543", "R-1656113", "R-1190689", "R-457198", "R-971869", "R-1292894", "R-932552", "R-1292874", "R-986177", "R-949231", "R-1149968", "R-1510579", "R-1456742", "R-1344563", "R-976419", "R-961030", "R-1202553", "R-1633229", "R-1166777", "R-956745", "R-1473937", "R-1213612", "R-961008", "R-871559", "R-1414161", "R-1317727", "R-735661", "R-961026", "R-1598258", "R-839522", "R-949226", "R-805253", "R-1216108", "R-1195226", "R-961021", "R-949240", "R-1061052", "R-949227", "R-1177675", "R-1414150", "R-1578863", "R-1005445", "R-1344552", "R-1112676", "R-1225845", "R-977968", "R-1047828", "R-1047825", "R-1047822", "R-1324893", "R-1364659", "R-908245", "R-1324896", "R-1240944", "R-1114612", "R-1324889", "R-1219005", "R-1324891", "R-976369", "R-961027", "R-1227471", "R-949219", "R-1148868", "R-1008160", "R-933886", "R-1143900", "R-1414214", "R-1609126", "R-1008171", "R-933893", "R-1008162", "R-1609122", "R-1143894", "R-916054", "R-1143633", "R-1170800", "R-1170795", "R-1170768", "R-1170814", "R-1170825", "R-1170805", "R-916054", "R-933358", "R-961022", "R-1669040", "R-1218979", "R-1194456", "R-1143633", "R-976392", "R-1218997", "R-805258", "R-976408", "R-968508", "R-1219018", "R-1147149", "R-1506188", "R-1693556", "R-1219017", "R-1071379", "R-878254", "R-792992", "R-1176595", "R-961011", "R-960983", "R-841632", "R-1149999", "R-1635182", "R-643255", "R-1633055", "R-1633058", "R-1199314", "R-1084769", "R-961050", "R-977094", "R-1633227", "R-949920", "R-1248192", "R-961037", "R-1314470", "R-960987", "R-916033", "R-976397", "R-1322229", "R-960968", "R-961024", "R-1120906", "R-768614", "R-968524", "R-1633153", "R-960966", "R-1248199", "R-1227079", "R-1038771", "R-1292877", "R-1060699", "R-1414165", "R-961047", "R-822541", "R-1473934", "R-779549"
    );

    @Test
    public void testHttp() {
        String json = "[\n" +
                "    {\n" +
                "        \"shopId\": \"9014\",\n" +
                "        \"skuCode\": \"R-1005445\"\n" +
                "    }\n" +
                "]";
        String result = HttpUtil.post("https://hc-pre.yonghuivip.com/app/api/sku-hc-hub/skuStore/updateBatchPicking", json, 9000);
        System.out.println(result);
    }

    @Test
    public void testHttp2() throws IOException, InterruptedException {
        UpdateBatchPickingParams updateBatchPickingParams = new UpdateBatchPickingParams();
        updateBatchPickingParams.setType(1);
        ArrayList<String> shopIdList = Lists.newArrayList(
                 "9M7E", "9M4U", "9M7X", "9M7W", "9M7H", "9M7T", "9M6N", "9M7S", "9M7I", "9M6A", "9M5M", "9M7F", "9M6D", "9M7L", "9M8F", "9M7P", "9M7M", "9M7R", "9M6H", "9M6I", "9M0K", "9M7J", "9M7Y", "9M7U", "9M8D", "9M8H", "9M8J", "9M8I", "9M8L", "9M8G", "9M8K", "9014", "9M7Q", "9M98", "9M4M", "9M8A", "9M4V", "9M79"
        );
        System.out.println(shopIdList.size());
        for (String shopId : shopIdList) {
            List<List<String>> partitions = Lists.partition(skuCodeList, 200);
            for (List<String> partition : partitions) {
                ArrayList<UpdateBatchPickingParams.BatchPickingItem> items = Lists.newArrayList();
                for (String skuCode : partition) {
                    UpdateBatchPickingParams.BatchPickingItem batchPickingItem = new UpdateBatchPickingParams.BatchPickingItem();
                    batchPickingItem.setShopId(shopId);
                    batchPickingItem.setSkuCode(skuCode);
                    items.add(batchPickingItem);
                }
                updateBatchPickingParams.setItemList(items);
                System.out.println(JSON.toJSONString(updateBatchPickingParams));
                callApiInBatches(updateBatchPickingParams);
                TimeUnit.SECONDS.sleep(10);
                items.clear();
            }
        }


    }

    private void callApiInBatches(UpdateBatchPickingParams updateBatchPickingParams) {
        try {
            // 设置要发送的数据
            String postData = JSON.toJSONString(updateBatchPickingParams);
            String url = "https://hc-pre.yonghuivip.com/app/api/sku-hc-hub/skuStore/updateBatchPicking?type=1";
            String cookie = "";
            // 创建URL对象
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // 设置请求方法
            con.setRequestMethod("POST");

            // 添加请求头
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Cookie", cookie);
//            con.setRequestProperty("Cookie", "SECKEY_ABVK=TyotJUbYbKnQylLM3CFARjeawTzdk3tCu5r8cBUKUao%3D; BMAP_SECKEY=1FwOtgb-jhLk4761lIYl_OLcZDWJmir_MVs_wemRI0Z7W0ijpaRh87TVr-9OmmbSgGzqnEzLKC7vqzrh0XSxag0afa9K_ianLZywZvYx62HxwI2zx3cKRurEdsii2h4saoKi8XmpItOCvU085VYIQ9uV8BolpHk59ODh3IDJPNiDl-pMJ6lPaCs9kdbnFAJL; sensorsdata2015jssdkchannel=%7B%22prop%22%3A%7B%22_sa_channel_landing_url%22%3A%22%22%7D%7D; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218d3e79b2dbd65-0808a380bcc96e-1f525637-3686400-18d3e79b2dcfa0%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkM2U3OWIyZGJkNjUtMDgwOGEzODBiY2M5NmUtMWY1MjU2MzctMzY4NjQwMC0xOGQzZTc5YjJkY2ZhMCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218d3e79b2dbd65-0808a380bcc96e-1f525637-3686400-18d3e79b2dcfa0%22%7D; APP_SPA_AI={%22username%22:%22Aj1dr2GBoxJSadnucWLhoA==%22%2C%22password%22:%22E8UhSN/0IL0hoBs+jXuh2A==%22}; SECKEY_ABVK=TyotJUbYbKnQylLM3CFARknLHS9c0Fs/bZ1n/nzh/DQ%3D; BMAP_SECKEY=1FwOtgb-jhLk4761lIYl_KNjb0dv71GXvwNxDa3R_7Sb0rxbkmKB1nAalGKvzVWJYgLjml6uNX6ZGG9QOMl5OkfQbgiGAG7PX0XUSw4k75bhk4Jw04iityubv-FNfvHQcYrUX_avb4_007aNwvz_CBcAsFKJOIrm5FmdrYmTSbna8a7yHaPvufAgWOMZYKNL; appliedwebsid=3cfa950f-3842-44f7-931d-b7d7ac94eb4a; uploadticket=GaXvEMXbsAsLWKIJ__M0kn2YX-quh7jsr08Zp7zdufc=");
            con.setRequestProperty("Origin", "https://hc-pre.yonghuivip.com");
            con.setRequestProperty("Pragma", "no-cache");
            con.setRequestProperty("Referer", "https://hc-pre.yonghuivip.com/app/sku-hc-web");
            con.setRequestProperty("Sec-Fetch-Dest", "empty");
            con.setRequestProperty("Sec-Fetch-Mode", "cors");
            con.setRequestProperty("Sec-Fetch-Site", "same-origin");
            con.setRequestProperty("X-YH-APP", "SKU");

            // 开启输出流
            con.setDoOutput(true);

            // 发送请求数据
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // 获取响应代码
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // 读取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 输出响应内容
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
