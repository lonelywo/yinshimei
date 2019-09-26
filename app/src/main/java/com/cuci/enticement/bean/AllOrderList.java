package com.cuci.enticement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AllOrderList implements Serializable {


    /**
     * code : 1
     * info : 获取订单列表成功！
     * data : {"page":{"limit":20,"total":14,"pages":1,"current":1},"list":[{"id":40746,"mid":18281,"type":1,"order_no":669079056302,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":0,"cancel_at":null,"cancel_desc":"","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11628,"express_name":"Qdubd","express_phone":"18588564260","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"形成细节问题","express_desc":"","status":2,"is_deleted":0,"create_at":"2019-09-21 23:17:36","goods_count":1,"list":[{"id":41186,"mid":18281,"type":1,"order_no":669079056302,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-21 23:17:36"}]},{"id":40213,"mid":18281,"type":1,"order_no":668470017206,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-15 10:09:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11476,"express_name":"严实","express_phone":"18588564258","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"时间久了苦痛","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-14 22:06:58","goods_count":1,"list":[{"id":40582,"mid":18281,"type":1,"order_no":668470017206,"goods_id":6672588222,"goods_title":"新零售礼包B方案","goods_logo":"http://qiniu.cdn.enticementchina.com/ce62bb5fa9fdace8/f71348a7db4928df.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*40，温柔高保湿洁面乳(30g)*25，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-14 22:06:58"}]},{"id":40093,"mid":18281,"type":1,"order_no":668304348596,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-13 12:06:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-13 00:05:48","goods_count":1,"list":[{"id":40460,"mid":18281,"type":1,"order_no":668304348596,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-13 00:05:48"}]},{"id":39999,"mid":18281,"type":1,"order_no":668217106262,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:54:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:51:46","goods_count":1,"list":[{"id":40350,"mid":18281,"type":1,"order_no":668217106262,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:51:46"}]},{"id":39998,"mid":18281,"type":1,"order_no":668217092091,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:54:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:51:32","goods_count":1,"list":[{"id":40349,"mid":18281,"type":1,"order_no":668217092091,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:51:32"}]},{"id":39997,"mid":18281,"type":1,"order_no":668217087920,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:54:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:51:27","goods_count":1,"list":[{"id":40348,"mid":18281,"type":1,"order_no":668217087920,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:51:27"}]},{"id":39996,"mid":18281,"type":1,"order_no":668216862815,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:48:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:47:42","goods_count":1,"list":[{"id":40347,"mid":18281,"type":1,"order_no":668216862815,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:47:42"}]},{"id":39975,"mid":18281,"type":1,"order_no":668196085203,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-11 23:41:04","cancel_desc":"用户主动取消订单！","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11477,"express_name":"严j","express_phone":"18588564285","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"fhjjjkkkk","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 18:01:25","goods_count":1,"list":[{"id":40326,"mid":18281,"type":1,"order_no":668196085203,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 18:01:25"}]},{"id":39849,"mid":18281,"type":1,"order_no":668114055490,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-11 07:15:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11477,"express_name":"严j","express_phone":"18588564285","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"fhjjjkkkk","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-10 19:14:15","goods_count":1,"list":[{"id":40185,"mid":18281,"type":1,"order_no":668114055490,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-10 19:14:15"}]},{"id":39823,"mid":18281,"type":1,"order_no":668099078534,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-11 03:06:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-10 15:04:38","goods_count":1,"list":[{"id":40159,"mid":18281,"type":1,"order_no":668099078534,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-10 15:04:38"}]},{"id":39745,"mid":18281,"type":1,"order_no":668052563173,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-10 14:12:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-10 02:09:23","goods_count":1,"list":[{"id":40073,"mid":18281,"type":1,"order_no":668052563173,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-10 02:09:23"}]},{"id":39675,"mid":18281,"type":1,"order_no":668015264018,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-10 03:48:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-09 15:47:44","goods_count":1,"list":[{"id":40002,"mid":18281,"type":1,"order_no":668015264018,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:L","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-09 15:47:44"}]},{"id":39226,"mid":18281,"type":1,"order_no":667760329424,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-07 05:00:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11477,"express_name":"严完","express_phone":"18588564285","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"too哦监控","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-06 16:58:49","goods_count":1,"list":[{"id":39508,"mid":18281,"type":1,"order_no":667760329424,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-06 16:58:49"}]},{"id":39222,"mid":18281,"type":1,"order_no":667760210579,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-07 04:57:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-06 16:56:50","goods_count":1,"list":[{"id":39504,"mid":18281,"type":1,"order_no":667760210579,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-06 16:56:50"}]}]}
     */

    private int code;
    private String info;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * page : {"limit":20,"total":14,"pages":1,"current":1}
         * list : [{"id":40746,"mid":18281,"type":1,"order_no":669079056302,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":0,"cancel_at":null,"cancel_desc":"","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11628,"express_name":"Qdubd","express_phone":"18588564260","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"形成细节问题","express_desc":"","status":2,"is_deleted":0,"create_at":"2019-09-21 23:17:36","goods_count":1,"list":[{"id":41186,"mid":18281,"type":1,"order_no":669079056302,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-21 23:17:36"}]},{"id":40213,"mid":18281,"type":1,"order_no":668470017206,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-15 10:09:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11476,"express_name":"严实","express_phone":"18588564258","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"时间久了苦痛","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-14 22:06:58","goods_count":1,"list":[{"id":40582,"mid":18281,"type":1,"order_no":668470017206,"goods_id":6672588222,"goods_title":"新零售礼包B方案","goods_logo":"http://qiniu.cdn.enticementchina.com/ce62bb5fa9fdace8/f71348a7db4928df.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*40，温柔高保湿洁面乳(30g)*25，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-14 22:06:58"}]},{"id":40093,"mid":18281,"type":1,"order_no":668304348596,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-13 12:06:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-13 00:05:48","goods_count":1,"list":[{"id":40460,"mid":18281,"type":1,"order_no":668304348596,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-13 00:05:48"}]},{"id":39999,"mid":18281,"type":1,"order_no":668217106262,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:54:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:51:46","goods_count":1,"list":[{"id":40350,"mid":18281,"type":1,"order_no":668217106262,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:51:46"}]},{"id":39998,"mid":18281,"type":1,"order_no":668217092091,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:54:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:51:32","goods_count":1,"list":[{"id":40349,"mid":18281,"type":1,"order_no":668217092091,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:51:32"}]},{"id":39997,"mid":18281,"type":1,"order_no":668217087920,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:54:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:51:27","goods_count":1,"list":[{"id":40348,"mid":18281,"type":1,"order_no":668217087920,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:51:27"}]},{"id":39996,"mid":18281,"type":1,"order_no":668216862815,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-12 11:48:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 23:47:42","goods_count":1,"list":[{"id":40347,"mid":18281,"type":1,"order_no":668216862815,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 23:47:42"}]},{"id":39975,"mid":18281,"type":1,"order_no":668196085203,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-11 23:41:04","cancel_desc":"用户主动取消订单！","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11477,"express_name":"严j","express_phone":"18588564285","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"fhjjjkkkk","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-11 18:01:25","goods_count":1,"list":[{"id":40326,"mid":18281,"type":1,"order_no":668196085203,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-11 18:01:25"}]},{"id":39849,"mid":18281,"type":1,"order_no":668114055490,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-11 07:15:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11477,"express_name":"严j","express_phone":"18588564285","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"fhjjjkkkk","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-10 19:14:15","goods_count":1,"list":[{"id":40185,"mid":18281,"type":1,"order_no":668114055490,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-10 19:14:15"}]},{"id":39823,"mid":18281,"type":1,"order_no":668099078534,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-11 03:06:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-10 15:04:38","goods_count":1,"list":[{"id":40159,"mid":18281,"type":1,"order_no":668099078534,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-10 15:04:38"}]},{"id":39745,"mid":18281,"type":1,"order_no":668052563173,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-10 14:12:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-10 02:09:23","goods_count":1,"list":[{"id":40073,"mid":18281,"type":1,"order_no":668052563173,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-10 02:09:23"}]},{"id":39675,"mid":18281,"type":1,"order_no":668015264018,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-10 03:48:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-09 15:47:44","goods_count":1,"list":[{"id":40002,"mid":18281,"type":1,"order_no":668015264018,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:L","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-09 15:47:44"}]},{"id":39226,"mid":18281,"type":1,"order_no":667760329424,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"订单总金额满2000.00元减免全部邮费","express_rule_type":"普通模板","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-07 05:00:01","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":11477,"express_name":"严完","express_phone":"18588564285","express_province":"北京市","express_city":"北京市","express_area":"东城区","express_address":"too哦监控","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-06 16:58:49","goods_count":1,"list":[{"id":39508,"mid":18281,"type":1,"order_no":667760329424,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-06 16:58:49"}]},{"id":39222,"mid":18281,"type":1,"order_no":667760210579,"from_mid":0,"price_total":"3660.00","price_goods":"3660.00","price_express":"0.00","price_service":"0.00","express_rule_desc":"","express_rule_type":"","express_rule_number":1,"pay_state":0,"pay_type":"","pay_price":"0.00","pay_no":"","pay_at":null,"cancel_state":1,"cancel_at":"2019-09-07 04:57:02","cancel_desc":"12小时未完成支付自动取消订单","refund_state":0,"refund_at":null,"refund_no":"","refund_price":"0.00","refund_desc":"","api_order_no":"","api_tracking_no":"","express_state":0,"express_company_code":"","express_company_title":"","express_send_no":"","express_send_at":null,"express_address_id":0,"express_name":"","express_phone":"","express_province":"","express_city":"","express_area":"","express_address":"","express_desc":"","status":0,"is_deleted":0,"create_at":"2019-09-06 16:56:50","goods_count":1,"list":[{"id":39504,"mid":18281,"type":1,"order_no":667760210579,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-06 16:56:50"}]}]
         */

        private PageBean page;
        private List<ListBeanX> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * limit : 20
             * total : 14
             * pages : 1
             * current : 1
             */

            private int limit;
            private int total;
            private int pages;
            private int current;

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }
        }

        public static class ListBeanX implements Serializable {
            /**
             * id : 40746
             * mid : 18281
             * type : 1
             * order_no : 669079056302
             * from_mid : 0
             * price_total : 3660.00
             * price_goods : 3660.00
             * price_express : 0.00
             * price_service : 0.00
             * express_rule_desc : 订单总金额满2000.00元减免全部邮费
             * express_rule_type : 普通模板
             * express_rule_number : 1
             * pay_state : 0
             * pay_type :
             * pay_price : 0.00
             * pay_no :
             * pay_at : null
             * cancel_state : 0
             * cancel_at : null
             * cancel_desc :
             * refund_state : 0
             * refund_at : null
             * refund_no :
             * refund_price : 0.00
             * refund_desc :
             * api_order_no :
             * api_tracking_no :
             * express_state : 0
             * express_company_code :
             * express_company_title :
             * express_send_no :
             * express_send_at : null
             * express_address_id : 11628
             * express_name : Qdubd
             * express_phone : 18588564260
             * express_province : 北京市
             * express_city : 北京市
             * express_area : 东城区
             * express_address : 形成细节问题
             * express_desc :
             * status : 2
             * is_deleted : 0
             * create_at : 2019-09-21 23:17:36
             * goods_count : 1
             * list : [{"id":41186,"mid":18281,"type":1,"order_no":669079056302,"goods_id":6672579002,"goods_title":"新零售礼包A方案","goods_logo":"http://qiniu.cdn.enticementchina.com/4229e1b0bf62661c/913270e1206f32e0.jpg","goods_spec":"默认分组:黑金魅惑蕾丝面膜5片/盒*20，温柔高保湿洁面乳(30g)*20，因诗美菁纯赋活原生水*12，贵族美学*1，油纸*10，标贴*10，手提袋*10;T恤:M","price_real":"3660.00","price_selling":"3660.00","price_market":"3660.00","price_express":"0.00","price_service":"0.00","discount_price":"0.00","discount_desc":"","number_limit":1,"number_express":1,"vip_mod":1,"vip_month":0,"vip_discount":"0","number":1,"create_at":"2019-09-21 23:17:36"}]
             */






            private int id;
            private int mid;
            private int type;
            private long order_no;
            private int from_mid;
            private String price_total;
            private String price_goods;
            private String price_express;
            private String price_service;
            private String express_rule_desc;
            private String express_rule_type;
            private int express_rule_number;
            private int pay_state;
            private String pay_type;
            private String pay_price;
            private String pay_no;
            private Object pay_at;
            private int cancel_state;
            private Object cancel_at;
            private String cancel_desc;
            private int refund_state;
            private Object refund_at;
            private String refund_no;
            private String refund_price;
            private String refund_desc;
            private String api_order_no;
            private String api_tracking_no;
            private int express_state;
            private String express_company_code;
            private String express_company_title;
            private String express_send_no;
            private Object express_send_at;
            private int express_address_id;
            private String express_name;
            private String express_phone;
            private String express_province;
            private String express_city;
            private String express_area;
            private String express_address;
            private String express_desc;
            private int status;
            private int is_deleted;
            private String create_at;
            private int goods_count;
            private List<OrderGoods> list;

            public ListBeanX(){

            }





            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getOrder_no() {
                return order_no;
            }

            public void setOrder_no(long order_no) {
                this.order_no = order_no;
            }

            public int getFrom_mid() {
                return from_mid;
            }

            public void setFrom_mid(int from_mid) {
                this.from_mid = from_mid;
            }

            public String getPrice_total() {
                return price_total;
            }

            public void setPrice_total(String price_total) {
                this.price_total = price_total;
            }

            public String getPrice_goods() {
                return price_goods;
            }

            public void setPrice_goods(String price_goods) {
                this.price_goods = price_goods;
            }

            public String getPrice_express() {
                return price_express;
            }

            public void setPrice_express(String price_express) {
                this.price_express = price_express;
            }

            public String getPrice_service() {
                return price_service;
            }

            public void setPrice_service(String price_service) {
                this.price_service = price_service;
            }

            public String getExpress_rule_desc() {
                return express_rule_desc;
            }

            public void setExpress_rule_desc(String express_rule_desc) {
                this.express_rule_desc = express_rule_desc;
            }

            public String getExpress_rule_type() {
                return express_rule_type;
            }

            public void setExpress_rule_type(String express_rule_type) {
                this.express_rule_type = express_rule_type;
            }

            public int getExpress_rule_number() {
                return express_rule_number;
            }

            public void setExpress_rule_number(int express_rule_number) {
                this.express_rule_number = express_rule_number;
            }

            public int getPay_state() {
                return pay_state;
            }

            public void setPay_state(int pay_state) {
                this.pay_state = pay_state;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getPay_price() {
                return pay_price;
            }

            public void setPay_price(String pay_price) {
                this.pay_price = pay_price;
            }

            public String getPay_no() {
                return pay_no;
            }

            public void setPay_no(String pay_no) {
                this.pay_no = pay_no;
            }

            public Object getPay_at() {
                return pay_at;
            }

            public void setPay_at(Object pay_at) {
                this.pay_at = pay_at;
            }

            public int getCancel_state() {
                return cancel_state;
            }

            public void setCancel_state(int cancel_state) {
                this.cancel_state = cancel_state;
            }

            public Object getCancel_at() {
                return cancel_at;
            }

            public void setCancel_at(Object cancel_at) {
                this.cancel_at = cancel_at;
            }

            public String getCancel_desc() {
                return cancel_desc;
            }

            public void setCancel_desc(String cancel_desc) {
                this.cancel_desc = cancel_desc;
            }

            public int getRefund_state() {
                return refund_state;
            }

            public void setRefund_state(int refund_state) {
                this.refund_state = refund_state;
            }

            public Object getRefund_at() {
                return refund_at;
            }

            public void setRefund_at(Object refund_at) {
                this.refund_at = refund_at;
            }

            public String getRefund_no() {
                return refund_no;
            }

            public void setRefund_no(String refund_no) {
                this.refund_no = refund_no;
            }

            public String getRefund_price() {
                return refund_price;
            }

            public void setRefund_price(String refund_price) {
                this.refund_price = refund_price;
            }

            public String getRefund_desc() {
                return refund_desc;
            }

            public void setRefund_desc(String refund_desc) {
                this.refund_desc = refund_desc;
            }

            public String getApi_order_no() {
                return api_order_no;
            }

            public void setApi_order_no(String api_order_no) {
                this.api_order_no = api_order_no;
            }

            public String getApi_tracking_no() {
                return api_tracking_no;
            }

            public void setApi_tracking_no(String api_tracking_no) {
                this.api_tracking_no = api_tracking_no;
            }

            public int getExpress_state() {
                return express_state;
            }

            public void setExpress_state(int express_state) {
                this.express_state = express_state;
            }

            public String getExpress_company_code() {
                return express_company_code;
            }

            public void setExpress_company_code(String express_company_code) {
                this.express_company_code = express_company_code;
            }

            public String getExpress_company_title() {
                return express_company_title;
            }

            public void setExpress_company_title(String express_company_title) {
                this.express_company_title = express_company_title;
            }

            public String getExpress_send_no() {
                return express_send_no;
            }

            public void setExpress_send_no(String express_send_no) {
                this.express_send_no = express_send_no;
            }

            public Object getExpress_send_at() {
                return express_send_at;
            }

            public void setExpress_send_at(Object express_send_at) {
                this.express_send_at = express_send_at;
            }

            public int getExpress_address_id() {
                return express_address_id;
            }

            public void setExpress_address_id(int express_address_id) {
                this.express_address_id = express_address_id;
            }

            public String getExpress_name() {
                return express_name;
            }

            public void setExpress_name(String express_name) {
                this.express_name = express_name;
            }

            public String getExpress_phone() {
                return express_phone;
            }

            public void setExpress_phone(String express_phone) {
                this.express_phone = express_phone;
            }

            public String getExpress_province() {
                return express_province;
            }

            public void setExpress_province(String express_province) {
                this.express_province = express_province;
            }

            public String getExpress_city() {
                return express_city;
            }

            public void setExpress_city(String express_city) {
                this.express_city = express_city;
            }

            public String getExpress_area() {
                return express_area;
            }

            public void setExpress_area(String express_area) {
                this.express_area = express_area;
            }

            public String getExpress_address() {
                return express_address;
            }

            public void setExpress_address(String express_address) {
                this.express_address = express_address;
            }

            public String getExpress_desc() {
                return express_desc;
            }

            public void setExpress_desc(String express_desc) {
                this.express_desc = express_desc;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public int getGoods_count() {
                return goods_count;
            }

            public void setGoods_count(int goods_count) {
                this.goods_count = goods_count;
            }

            public List<OrderGoods> getList() {
                return list;
            }

            public void setList(List<OrderGoods> list) {
                this.list = list;
            }


        }
    }
}
