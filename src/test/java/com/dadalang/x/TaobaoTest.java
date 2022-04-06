package com.dadalang.x;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/8/17 9:39 上午
 * @desc
 */
@SpringBootTest
public class TaobaoTest {

    private String url = "https://eco.taobao.com/router/rest";
    private String appkey = "28337341";
    private String secret = "d3618658c07ac557fb9e86a16f383b8c";

    // 不用授权的接口

    /**
     * 381
     * 获取淘宝系统时间
     * @throws ApiException
     */
    @Test
    public void taobaoTest() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url,appkey,secret);
        TimeGetRequest req = new TimeGetRequest();
        TimeGetResponse res = client.execute(req);
        System.out.println(res.getBody());
    }

    /**
     * 11655
     * 根据联盟官方渠道获取的淘客推广链接，生成淘口令
     * @throws ApiException
     */
    @Test
    public void createTPWDTest() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url,appkey,secret);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setUrl("https://s.click.taobao.com/YI3Uopu");
        TbkTpwdCreateResponse res = client.execute(req);
        System.out.println(res.getBody());
    }

    /**
     * 15736
     * 淘宝客处罚订单查询
     * @throws ApiException
     */
    @Test
    public void punishQueryTest() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgPunishOrderGetRequest req = new TbkDgPunishOrderGetRequest();
        TbkDgPunishOrderGetRequest.TopApiAfOrderOption obj1 = new TbkDgPunishOrderGetRequest.TopApiAfOrderOption();
        obj1.setSiteId(1277100256L);
        obj1.setSpan(10L);
        obj1.setRelationId(499810109L);
        obj1.setTbTradeId(258897956183171983L);
        obj1.setPageSize(1L);
        obj1.setPageNo(10L);
        obj1.setStartTime(StringUtils.parseDateTime("2021-8-10 00:00:01"));
        obj1.setAdzoneId(109998300449L);
        req.setAfOrderOption(obj1);
        TbkDgPunishOrderGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 16175
     * 淘宝客获取指定时间段订单
     * @throws ApiException
     */
    @Test
    public void ordersTest() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkOrderDetailsGetRequest req = new TbkOrderDetailsGetRequest();
        req.setQueryType(4L);                    // 查询时间类型，1：按照订单淘客创建时间查询，2:按照订单淘客付款时间查询，3:按照订单淘客结算时间查询，4:按照订单更新时间；
//        req.setPositionIndex("2222_334666");     // 位点，除第一页之外，都需要传递；前端原样返回。
        req.setPageSize(20L);                   // 页大小，默认20，1~100
//        req.setMemberType(2L);                  // 推广者角色类型,2:二方，3:三方，不传，表示所有角色
//        req.setTkStatus(12L);                   // 淘客订单状态，12-付款，13-关闭，14-确认收货，3-结算成功;不传，表示所有状态
        req.setEndTime("2021-08-17 17:59:22");  // 订单查询结束时间，订单开始时间至订单结束时间，中间时间段日常要求不超过3个小时，但如618、双11、年货节等大促期间预估时间段不可超过20分钟，超过会提示错误，调用时请务必注意时间段的选择，以保证亲能正常调用！
        req.setStartTime("2021-08-17 16:10:22");// 订单查询开始时间
//        req.setJumpType(1L);                    // 跳转类型，当向前或者向后翻页必须提供,-1: 向前翻页,1：向后翻页
//        req.setPageNo(1L);                      // 	第几页，默认1，1~100
        req.setOrderScene(1L);                  // 场景订单场景类型，1:常规订单，2:渠道订单，3:会员运营订单，默认为1
        TbkOrderDetailsGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 16175
     * 获取指定时间段的维权、退款的订单
     */
    @Test
    public void refundOrdersTest() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkRelationRefundRequest req = new TbkRelationRefundRequest();
        TbkRelationRefundRequest.TopApiRefundRptOption obj1 = new TbkRelationRefundRequest.TopApiRefundRptOption();
        obj1.setPageSize(1L);
        obj1.setSearchType(1L);                     // 1-维权发起时间，2-订单结算时间（正向订单），3-维权完成时间，4-订单创建时间，5-订单更新时间
        obj1.setRefundType(1L);                     // 	1 表示2方，2表示3方，0表示不限
        obj1.setStartTime(StringUtils.parseDateTime("2021-08-16 09:00:00"));
        obj1.setPageNo(1L);
        obj1.setBizType(1L);                        // 1代表渠道关系id，2代表会员关系id
        req.setSearchOption(obj1);
        TbkRelationRefundResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 18294
     * 官方活动转链
     * @throws ApiException
     */
    @Test
    public void officeActivityUrl() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkActivityInfoGetRequest req = new TbkActivityInfoGetRequest();
        req.setAdzoneId(111602400158L);                             // mm_xxx_xxx_xxx的第三位
//        req.setSubPid("mm_1_2_3");                                  // mm_xxx_xxx_xxx 仅三方分成场景使用
//        req.setRelationId(123L);                                    // 渠道关系id
        req.setActivityMaterialId("20150318020006566");             // 官方活动会场ID，从淘宝客后台“我要推广-活动推广”中获取
        req.setUnionId("demo");                                     // 自定义输入串，英文和数字组成，长度不能大于12个字符，区分不同的推广渠道
        TbkActivityInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 16292
     * 输入卖家id，提供和该店铺相关联的店铺推荐
     * @throws ApiException
     */
    @Test
    public void shopRecommandTest() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkShopRecommendGetRequest req = new TbkShopRecommendGetRequest();
        req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");    // 需返回的字段列表
        req.setUserId(836608779L);                                                            // 卖家Id
        req.setCount(20L);                                                              // 返回数量，默认20，最大值40
        req.setPlatform(1L);                                                            // 链接形式：1：PC，2：无线，默认：１
        TbkShopRecommendGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 12340
     * 长链转短链,转链，只支持uland.taobao.com，s.click.taobao.com， ai.taobao.com，temai.taobao.com的域名转换，否则判错
     * @throws ApiException
     */
    @Test
    public void convertingLinke() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url,appkey, secret);
        TbkSpreadGetRequest req = new TbkSpreadGetRequest();
        List<TbkSpreadGetRequest.TbkSpreadRequest> links = new ArrayList<TbkSpreadGetRequest.TbkSpreadRequest>();
        TbkSpreadGetRequest.TbkSpreadRequest link = new TbkSpreadGetRequest.TbkSpreadRequest();
        link.setUrl("https://uland.taobao.com/coupon/edetail?spm=a2e1u.13363363.35064267.1.635a7466e7TQSr&e=ygVBrTW9YggNfLV8niU3RxrSI%2FOabn6qNg4Gqf8CT4BnmB%2Fzds2ljZMnqo7WmWH3THwwIuwFb2cAbAN3NcqP1djiLvKBoQ2vzOJz0K1X%2B2qTJ6qO1plh9%2FeZ%2BUrwahJSOALG7aKXGrWJyvn11oba1MlMTjSQsK4pnUqGHaBBNog%3D&app_pvid=59590_33.39.130.249_649_1629266673151&ptl=floorId%3A23919%3Bapp_pvid%3A59590_33.39.130.249_649_1629266673151%3Btpp_pvid%3A7447e6a3-a0e4-4496-ab32-7070f445f4b4&union_lens=lensId%3AOPT%401629266673%407447e6a3-a0e4-4496-ab32-7070f445f4b4_607443931487%401%3Brecoveryid%3A201_11.8.100.95_1046503_1629266672825%3Bprepvid%3A201_11.8.100.95_1046503_1629266672825&pid=mm_10011550_0_0");
        links.add(link);
        req.setRequests(links);
        TbkSpreadGetResponse res = client.execute(req);
        System.out.println(res.getBody());
    }

    /**
     * 16518
     * 物料精选：支持入参对应的“推广位”和官方提供的“物料id”，获取指定物料信息和推广链接，还可入参用户信息提供智能推荐（需智能推荐请先前协议https://pub.alimama.com/fourth/protocol/common.htm?key=hangye_laxin）
     * @throws ApiException
     */
    @Test
    public void getMaterial() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();

        req.setPageNo(1L);
        req.setPageSize(20L);

        req.setAdzoneId(111602400158L);                 // mm_499810109_1277100256_111602400158的第三位
        req.setMaterialId(44441L);                      // 官方的物料Id(详细物料id见：https://market.m.taobao.com/app/qn/toutiao-new/index-pc.html#/detail/10628875?_k=gpov9a)
//        req.setDeviceValue("xxx");                      // 智能匹配-设备号加密后的值（MD5加密需32位小写），类型为OAID时传原始OAID值
//        req.setDeviceEncrypt("MD5");                    // 智能匹配-设备号加密类型：MD5，类型为OAID时不传
//        req.setDeviceType("IMEI");                      // 智能匹配-设备号类型：IMEI，或者IDFA，或者UTDID（UTDID不支持MD5加密），或者OAID
//        req.setContentId(323L);                         // 内容专用-内容详情ID
//        req.setContentSource("xxx");                    // 内容专用-内容渠道信息
//        req.setItemId(33243L);                          // 商品ID，用于相似商品推荐
//        req.setFavoritesId("123445");                   // 选品库投放id

        TbkDgOptimusMaterialResponse res = client.execute(req);
        System.out.println(res.getBody());
    }

    /**
     * 16518：返回数据未空
     * 推广者使用。支持入参推广者对应的“推广位”和官方提供的“权益物料id”，获取指定权益物料。
     */
    @Test
    public void getPromotionMaterial() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgOptimusPromotionRequest req = new TbkDgOptimusPromotionRequest();
        req.setPageSize(10L);
        req.setPageNum(1L);
        req.setAdzoneId(111602400158L);            // mm_xxx_xxx_xxx的第3段数字
        req.setPromotionId(37104L);                 // 官方提供的权益物料Id。有价券-37104、大额店铺券-37116，更多权益物料id敬请期待！
        TbkDgOptimusPromotionResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 16516
     * 物料搜索，根据关键词搜索店铺及相关信息
     */
    @Test
    public void searchShop() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkShopGetRequest req = new TbkShopGetRequest();
        req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");    // 返回字段
        req.setQ("女装");                                                                // 查询词
//        req.setSort("commission_rate_des");                                             // 排序_des（降序），排序_asc（升序），佣金比率（commission_rate）， 商品数量（auction_count），销售总数量（total_auction）
        req.setIsTmall(true);                                                           // 是否商城的店铺，设置为true表示该是属于淘宝商城的店铺，设置为false或不设置表示不判断这个属性
//        req.setStartCredit(1L);                                                         // 信用等级下限，1~20
//        req.setEndCredit(20L);                                                          // 信用等级上限，1~20
//        req.setStartCommissionRate(2000L);                                              // 淘客佣金比率下限，1~10000
//        req.setEndCommissionRate(123L);                                                 // 淘客佣金比率上限，1~10000
//        req.setStartTotalAction(1L);                                                    // 店铺商品总数下限
//        req.setEndTotalAction(100L);                                                    // 店铺商品总数上限
//        req.setStartAuctionCount(123L);                                                 // 累计推广商品下限
//        req.setEndAuctionCount(200L);                                                   // 累计推广商品上限
//        req.setPlatform(1L);                                                            // 链接形式：1：PC，2：无线，默认：１
        req.setPageNo(1L);
        req.setPageSize(20L);
        TbkShopGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
    /**
     * 16516
     * 物料搜索，根据关键词搜索物料及相关信息
     */
    @Test
    public void searchMaterial() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPageSize(20L);
        req.setPageNo(1L);

//        req.setStartDsr(10L);           // 商品筛选(特定媒体支持)-店铺dsr评分。筛选大于等于当前设置的店铺dsr评分的商品0-50000之间
//        req.setPlatform(1L);            // 	链接形式：1：PC，2：无线，默认：１
//        req.setEndTkRate(1234L);        // 商品筛选-淘客佣金比率上限。如：1234表示12.34%
//        req.setStartTkRate(1234L);      // 商品筛选-淘客佣金比率下限。如：1234表示12.34%
//        req.setEndPrice(10L);
//        req.setStartPrice(10L);
//        req.setIsOverseas(false);       // 商品筛选-是否海外商品。true表示属于海外商品，false或不设置表示不限
//        req.setIsTmall(false);          // 商品筛选-是否天猫商品。true表示属于天猫商品，false或不设置表示不限
//        req.setSort("tk_rate_des");     // 排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price），匹配分（match）
//        req.setItemloc("杭州");          // 商品筛选-所在地
//        req.setCat("16,18");            // 商品筛选-后台类目ID。用,分割，最大10个，该ID可以通过taobao.itemcats.get接口获取到
        req.setQ("女装");                // 	商品筛选-查询词
//        req.setMaterialId(2836L);       // 	不传时默认物料id=2836；如果直接对消费者投放，可使用官方个性化算法优化的搜索物料id=17004
//        req.setHasCoupon(false);        // 优惠券筛选-是否有优惠券。true表示该商品有优惠券，false或不设置表示不限
//        req.setIp("13.2.33.4");         // ip参数影响邮费获取，如果不传或者传入不准确，邮费无法精准提供
        req.setAdzoneId(111602400158L);   // mm_xxx_xxx_12345678三段式的最后一段数字
//        req.setNeedFreeShipment(true);  // 商品筛选-是否包邮。true表示包邮，false或不设置表示不限
//        req.setNeedPrepay(true);        // 商品筛选-是否加入消费者保障。true表示加入，false或不设置表示不限
//        req.setIncludePayRate30(true);  // 商品筛选(特定媒体支持)-成交转化是否高于行业均值。True表示大于等于，false或不设置表示不限
//        req.setIncludeGoodRate(true);   // 商品筛选-好评率是否高于行业均值。True表示大于等于，false或不设置表示不限
//        req.setIncludeRfdRate(true);    // 商品筛选(特定媒体支持)-退款率是否低于行业均值。True表示大于等于，false或不设置表示不限
//        req.setNpxLevel(2L);            // 商品筛选-牛皮癣程度。取值：1不限，2无，3轻微
//        req.setEndKaTkRate(1234L);      // 商品筛选-KA媒体淘客佣金比率上限。如：1234表示12.34%
//        req.setStartKaTkRate(1234L);    // 	商品筛选-KA媒体淘客佣金比率下限。如：1234表示12.34%
//        req.setDeviceEncrypt("MD5");    // 智能匹配-设备号加密类型：MD5
//        req.setDeviceValue("xxx");      // 智能匹配-设备号加密后的值（MD5加密需32位小写）
//        req.setDeviceType("IMEI");      // 智能匹配-设备号类型：IMEI，或者IDFA，或者UTDID（UTDID不支持MD5加密），或者OAID
//        req.setLockRateEndTime(1567440000000L);
//        req.setLockRateStartTime(1567440000000L);
//        req.setLongitude("121.473701"); // 本地化业务入参-LBS信息-经度
//        req.setLatitude("31.230370");   // 本地化业务入参-LBS信息-纬度
//        req.setCityCode("310000");      // 本地化业务入参-LBS信息-国标城市码，仅支持单个请求，请求饿了么卡券物料时，该字段必填。 （详细城市ID见：https://mo.m.taobao.com/page_2020010315120200508）
//        req.setSellerIds("1,2,3,4");    // 商家id，仅支持饿了么卡券商家ID，支持批量请求1-100以内，多个商家ID使用英文逗号分隔
//        req.setSpecialId("2323");       // 	会员运营ID
//        req.setRelationId("3243");      // 渠道关系ID，仅适用于渠道推广场景
//        req.setPageResultKey("abcdef"); // 本地化业务入参-分页唯一标识，非首页的请求必传，值为上一页返回结果中的page_result_key字段值
//        req.setUcrowdId(1L);            // 人群ID，仅适用于物料评估场景material_id=41377
//        req.setGetTopnRate(0L);         // 	是否获取前N件佣金信息 0否，1是，其他值否
//
//        List<TbkDgMaterialOptionalRequest.Ucrowdrankitems> list2 = new ArrayList<TbkDgMaterialOptionalRequest.Ucrowdrankitems>();
//        TbkDgMaterialOptionalRequest.Ucrowdrankitems obj3 = new TbkDgMaterialOptionalRequest.Ucrowdrankitems();
//        list2.add(obj3);
//        obj3.setCommirate(1234L);
//        obj3.setPrice("10.12");
//        obj3.setItemId("542808901898");
//        req.setUcrowdRankItems(list2);

        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 16189
     * 淘宝客商品详情查询（简版）
     * @throws ApiException
     */
    @Test
    public void detailOfGoods() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids("616642879260");                          // 商品ID串，用,分割，最大40个
//        req.setPlatform(1L);                                // 链接形式：1：PC，2：无线，默认：１
//        req.setIp("11.22.33.43");                           // ip地址，影响邮费获取，如果不传或者传入不准确，邮费无法精准提供
        TbkItemInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 16189    没有数据
     * 传入商品ID+券ID(券ID已知情况下)，或者传入me参数，均可查询阿里妈妈推广券详细信息。
     */
    @Test
    public void couponOfDetail() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkCouponGetRequest req = new TbkCouponGetRequest();
//        req.setMe("nfr%2BYTo2k1PX18gaNN%2BIPkIG2PadNYbBnwEsv6mRavWieOoOE3L9OdmbDSSyHbGxBAXjHpLKvZbL1320ML%2BCF5FRtW7N7yJ056Lgym4X01A%3D");  // 带券ID与商品ID的加密串
        req.setItemId(616642879260L);                                                                                                         // 商品ID
//        req.setItemId(123L);
//        req.setActivityId("ee1cf2d12917611da39c6836ec2606ad");                                                                                                  // 券ID
        TbkCouponGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
}
