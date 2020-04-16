package com.cuci.enticement.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.mob.MobSDK;
import com.mob.secverify.datatype.LandUiSettings;
import com.mob.secverify.datatype.UiSettings;

import com.mob.secverify.ui.AgreementPage;
import com.mob.tools.utils.ResHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomizeUtils {
	private static String url;

    public static UiSettings customizeUi(){
        return new UiSettings.Builder()
                .setAgreementUncheckHintType(0)
                /** 标题栏 */
                // 标题栏背景色资源ID
            //    .setNavColorId(R.color.sec_verify_demo_text_color_blue)
                //标题栏是否透明
                .setNavTransparent(true)
                //标题栏是否隐藏
                .setNavHidden(false)
                //设置背景图片
//				.setBackgroundImgId(R.drawable.sec_verify_background_demo)
                //设置背景是否点击关闭页面
//				.setBackgroundClickClose(false)
                // 标题栏标题文字资源ID
//				.setNavTextId(R.string.sec_verify_demo_verify)
                // 标题栏文字颜色资源ID
//				.setNavTextColorId(R.color.sec_verify_demo_text_color_common_black)
                // 标题栏左侧关闭按钮图片资源ID
           //     .setNavCloseImgId(R.drawable.sec_verify_demo_close)
                //标题栏返回按钮是否隐藏
                .setNavCloseImgHidden(false)
                /** Logo */
                // Logo图片资源ID，默认使用应用图标
                .setLogoImgId(R.drawable.logo)
                //logo是否隐藏
                .setLogoHidden(false)
//				//logo宽度
             //   .setLogoWidth(R.dimen.sec_verify_demo_logo_width)
                //logo高度
            //    .setLogoHeight(R.dimen.sec_verify_demo_logo_height)
                //logo x轴偏移量
//				.setLogoOffsetX(R.dimen.sec_verify_demo_logo_offset_x)
                //logo y轴偏移量
//				.setLogoOffsetY(R.dimen.sec_verify_demo_logo_offset_y)
                /** 手机号 */
                // 脱敏手机号字体颜色资源ID
                .setNumberColorId(R.color.hei)
                // 脱敏手机号字体大小资源ID
               // .setNumberSizeId(R.dimen.sec_verify_demo_text_size_m)
                //脱敏手机号 x轴偏移量
//				.setNumberOffsetX(R.dimen.sec_verify_demo_number_field_offset_x)
                //脱敏手机号 y轴偏移量
//				.setNumberOffsetY(R.dimen.sec_verify_demo_number_field_offset_y)
                /** 切换帐号 */
                // 切换账号字体颜色资源ID
                .setSwitchAccColorId(R.color.hei)
                .setSwitchAccText( "其他方式登录")
                //切换账号 字体大小
            //    .setSwitchAccTextSize(R.dimen.sec_verify_demo_text_size_s)
                // 切换账号是否显示，默认显示
                .setSwitchAccHidden(false)
                //切换账号 x轴偏移量
//				.setSwitchAccOffsetX(R.dimen.sec_verify_demo_switch_acc_offset_x)
                //切换账号 y轴偏移量
				.setSwitchAccOffsetY(R.dimen.sec_verify_demo_switch_acc_offset_y)

                /** 登录按钮 */
                // 登录按钮背景图资源ID，建议使用shape
                .setLoginBtnImgId(R.drawable.sec_verify_demo_shape_rectangle)
                // 登录按钮文字资源ID
                .setLoginBtnTextId(R.string.sec_verify_demo_login)
                // 登录按钮字体颜色资源ID
             //   .setLoginBtnTextColorId(R.color.sec_verify_demo_text_color_common_white)
                //登录按钮字体大小
             //   .setLoginBtnTextSize(R.dimen.sec_verify_demo_text_size_s)
                //登录按钮 width
//				.setLoginBtnWidth(R.dimen.sec_verify_demo_login_btn_width)
                //登录按钮 height
				.setLoginBtnHeight(R.dimen.sec_verify_demo_login_btn_height)
                //登录按钮 x轴偏移
//				.setLoginBtnOffsetX(R.dimen.sec_verify_demo_login_btn_offset_x)
                //登录按钮 y轴偏移
//				.setLoginBtnOffsetY(R.dimen.sec_verify_demo_login_btn_offset_y)
                /** 隐私协议 */
                //是否隐藏复选框(设置此属性true时setCheckboxDefaultState不会生效)
                .setCheckboxHidden(true)
                // 隐私协议复选框背景图资源ID，建议使用selector
              //  .setCheckboxImgId(R.drawable.sec_verify_demo_customized_checkbox_selector)
                // 隐私协议复选框默认状态，默认为“选中”
				.setCheckboxDefaultState(true)
                // 隐私协议字体颜色资源ID（自定义隐私协议的字体颜色也受该值影响）
//				.setAgreementColorId(R.color.sec_verify_demo_main_color)
                // 自定义隐私协议一文字资源ID
            //    .setCusAgreementNameId1(R.string.sec_verify_demo_customize_agreement_name_1)
                // 自定义隐私协议一URL
            //    .setCusAgreementUrl1("http://www.baidu.com")
//				自定义隐私协议一颜色
           //     .setCusAgreementColor1(R.color.sec_verify_demo_main_color)
                // 自定义隐私协议二文字资源ID
           //     .setCusAgreementNameId2(R.string.sec_verify_demo_customize_agreement_name_2)
                // 自定义隐私协议二URL
           //     .setCusAgreementUrl2("https://www.jianshu.com")
//				自定义隐私协议二颜色
         //       .setCusAgreementColor2(R.color.sec_verify_demo_main_color)
         //       .setCusAgreementNameId3("自有服务策略")
         //       .setCusAgreementUrl3("http://www.baidu.com")
          //      .setCusAgreementColor3(R.color.sec_verify_demo_main_color)
        //        .setAgreementTextAnd3("&")
                //隐私协议是否左对齐，默认居中
        //        .setAgreementGravityLeft(true)
                //隐私协议其他文字颜色
//				.setAgreementBaseTextColorId(R.color.sec_verify_demo_text_color_common_black)
                //隐私协议 x轴偏移量，默认30dp
          //      .setAgreementOffsetX(R.dimen.sec_verify_demo_agreement_offset_x)
                //隐私协议 rightMargin右偏移量，默认30dp
          //      .setAgreementOffsetRightX(R.dimen.sec_verify_demo_agreement_offset_x)
                //隐私协议 y轴偏移量
//				.setAgreementOffsetY(R.dimen.sec_verify_demo_agreement_offset_y)
                //隐私协议 底部y轴偏移量
				.setAgreementOffsetBottomY(R.dimen.sec_verify_demo_agreement_offset_bottom_y)
                /** slogan */
                //slogan文字大小
          //      .setSloganTextSize(R.dimen.sec_verify_demo_text_size_xs)
                //slogan文字颜色
          //      .setSloganTextColor(R.color.sec_verify_demo_text_color_common_gray)
                //slogan x轴偏移量
//				.setSloganOffsetX(R.dimen.sec_verify_demo_slogan_offset_x)
                //slogan y轴偏移量
//				.setSloganOffsetY(R.dimen.sec_verify_demo_slogan_offset_y)
                //slogan 底部y轴偏移量(设置此属性时，setSloganOffsetY不生效)
//				.setSloganOffsetBottomY(R.dimen.sec_verify_demo_slogan_offset_bottom_y)
                //设置状态栏为透明状态栏，5.0以上生效
                .setImmersiveTheme(false)
                //设置状态栏文字颜色为黑色，只在6.0以上生效
                .setImmersiveStatusTextColorBlack(false)
                .setSloganHidden(true)
                //使用平移动画
//				.setTranslateAnim(true)
           //     .setStartActivityTransitionAnim(R.anim.translate_in,R.anim.translate_out)
           //     .setFinishActivityTransitionAnim(R.anim.translate_in,R.anim.translate_out)
                //设置隐私协议文字起始
//				.setAgreementTextStart(R.string.sec_verify_demo_agreement_text_start)
//				//设置隐私协议文字连接
//				.setAgreementTextAnd1(R.string.sec_verify_demo_agreement_text_and1)
//				//设置隐私协议文字连接
//				.setAgreementTextAnd2(R.string.sec_verify_demo_agreement_text_and2)
//				//设置隐私协议文字结束
//				.setAgreementTextEnd(R.string.sec_verify_demo_agreement_text_end)
//				//设置移动隐私协议文字
//				.setAgreementCmccText(R.string.sec_verify_demo_agreement_text_cmcc)
//				//设置联通隐私协议文字
//				.setAgreementCuccText(R.string.sec_verify_demo_agreement_text_cucc)
//				//设置电信隐私协议文字
//				.setAgreementCtccText(R.string.sec_verify_demo_agreement_text_ctcc)
                .setAgreementText(buildSpanString())
//				.setAgreementUncheckHintText(R.string.ct_account_brand_text)
                .setAgreementUncheckHintText("请阅读并勾选隐私协议")
                .build();
    }



	private static SpannableString buildSpanString() {
		String operatorText = "";
		if (OperatorUtils.getCellularOperatorType() == 1){
			operatorText = "《中国移动认证服务条款》";
			url = "https://wap.cmpassport.com/resources/html/contract.html";
		} else if (OperatorUtils.getCellularOperatorType() == 2){
			operatorText = "《中国联通认证服务条款》";
			url = "https://ms.zzx9.cn/html/oauth/protocol2.html";
		} else  if (OperatorUtils.getCellularOperatorType() == 3){
			operatorText = "《中国电信认证服务条款》";
			url = "https://e.189.cn/sdk/agreement/content.do?type=main&appKey=&hidetop=true&returnUrl=";
		}
		String ageementText = "登录即同意"+operatorText+"及《用户服务协议》和" +
				"《隐私政策》并授权秒验使用本机号码登录";
		String cusPrivacy1 = "《用户服务协议》";
		String cusPrivacy2 = "《隐私政策》";
		String cusPrivacy3 = "";
		int baseColor = MobSDK.getContext().getResources().getColor(R.color.hei);
		int privacyColor = Color.parseColor("#FFE1AD73");
		int cusPrivacyColor1 = Color.parseColor("#FFE1AD73");
		int cusPrivacyColor2 = Color.parseColor("#FFE1AD73");
		int cusPrivacyColor3 = Color.parseColor("#FFE1AD73");
		SpannableString spanStr = new SpannableString(ageementText);
		int privacyIndex = ageementText.indexOf(operatorText);
		spanStr.setSpan(new ForegroundColorSpan(baseColor)
				, 0, ageementText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//设置文字的单击事件
		spanStr.setSpan(new ClickableSpan() {
			@Override
			public void updateDrawState(TextPaint ds) {
				ds.setUnderlineText(false);
			}

			@Override
			public void onClick(View widget) {
				gotoAgreementPage(url,"");
			}
		}, privacyIndex, privacyIndex + operatorText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//设置文字的前景色
		spanStr.setSpan(new ForegroundColorSpan(privacyColor), privacyIndex, privacyIndex + operatorText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		if (!TextUtils.isEmpty(cusPrivacy1)) {
			int privacy1Index = ageementText.indexOf(cusPrivacy1);
			//设置文字的单击事件
			spanStr.setSpan(new ClickableSpan() {
				@Override
				public void updateDrawState(TextPaint ds) {
					ds.setUnderlineText(false);
				}

				@Override
				public void onClick(View widget) {
					gotoAgreementPage("http://www.enticementchina.com/user_agreement.html", null);
				}
			}, privacy1Index, privacy1Index + "《用户服务协议》".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			//设置文字的前景色
			spanStr.setSpan(new ForegroundColorSpan(cusPrivacyColor1), privacy1Index, privacy1Index + cusPrivacy1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		if (!TextUtils.isEmpty(cusPrivacy2)) {
			int privacy2Index = ageementText.lastIndexOf(cusPrivacy2);
			//设置文字的单击事件
			spanStr.setSpan(new ClickableSpan() {
				@Override
				public void updateDrawState(TextPaint ds) {
					ds.setUnderlineText(false);
				}

				@Override
				public void onClick(View widget) {
					gotoAgreementPage("http://web.enticementchina.com/appweb/user_privacy.html", null);
				}
			}, privacy2Index, privacy2Index + cusPrivacy2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			//设置文字的前景色
			spanStr.setSpan(new ForegroundColorSpan(cusPrivacyColor2), privacy2Index, privacy2Index + cusPrivacy2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		if (!TextUtils.isEmpty(cusPrivacy3)) {
			int privacy3Index = ageementText.lastIndexOf(cusPrivacy3);
			//设置文字的单击事件
			spanStr.setSpan(new ClickableSpan() {
				@Override
				public void updateDrawState(TextPaint ds) {
					ds.setUnderlineText(false);
					ds.linkColor = Color.parseColor("#FFFFFF");
				}

				@Override
				public void onClick(View widget) {
					gotoAgreementPage("https://www.baidu.com", null);
				}
			}, privacy3Index, privacy3Index + cusPrivacy3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			//设置文字的前景色
			spanStr.setSpan(new ForegroundColorSpan(cusPrivacyColor3), privacy3Index, privacy3Index + cusPrivacy3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return spanStr;
	}

	private static void gotoAgreementPage(String agreementUrl, String title) {
		if (TextUtils.isEmpty(agreementUrl)) {
			return;
		}
		AgreementPage page = new AgreementPage();
		Intent i = new Intent();
		i.putExtra("extra_agreement_url", agreementUrl);
		if (!TextUtils.isEmpty(title)) {
			i.putExtra("privacy",title);
		}
		page.show(MobSDK.getContext(), i);
	}

}
