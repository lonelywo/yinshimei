package com.cuci.enticement.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linchen on 2018/1/26.
 * mail: linchen@sogou-inc.com
 */

public class CardMaker {
    public static final String U1 = "https://qiniu.cdn.enticementchina.com/326cf6f33245526f/3b72062acdcad35c.jpg";
    public static final String U2 = "https://qiniu.cdn.enticementchina.com/542c454dca060d53/7803633f0f7bb759.jpg";
    public static final String U3 = "https://qiniu.cdn.enticementchina.com/63b64202f765078f/3cdd200393fc3adf.jpg";
    public static final String U4 = "https://qiniu.cdn.enticementchina.com/176eee51b4c24833/04f995f3e83d6153.jpg";
    public static final String U5 = "https://qiniu.cdn.enticementchina.com/91eb703c9482d61c/20843c66b35334e5.jpg";
    public static final String U6 = "https://qiniu.cdn.enticementchina.com/91eb703c9482d61c/20843c66b35334e5.jpg";
    public static final String U7 = "https://qiniu.cdn.enticementchina.com/03e6d8efcab4c9cc/5f37f5cfacacc077.jpg";

    public static List<CardBean> initCards(QianDaoBean.DataBean.ShareInfoBean share_info) {
        List<CardBean> list = new ArrayList<>();
        CardBean cardBean = new CardBean();
        cardBean.setQrcode(share_info.getQrcode());
        cardBean.setPoster(share_info.getPoster().get(0));
        cardBean.setNickname(share_info.getNickname());
        cardBean.setSlogan(share_info.getSlogan());
        CardBean cardBean1 = new CardBean();
        cardBean1.setQrcode(share_info.getQrcode());
        cardBean1.setPoster(share_info.getPoster().get(1));
        cardBean1.setNickname(share_info.getNickname());
        cardBean1.setSlogan(share_info.getSlogan());
        CardBean cardBean2 = new CardBean();
        cardBean2.setQrcode(share_info.getQrcode());
        cardBean2.setPoster(share_info.getPoster().get(2));
        cardBean2.setNickname(share_info.getNickname());
        cardBean2.setSlogan(share_info.getSlogan());
        CardBean cardBean3 = new CardBean();
        cardBean3.setQrcode(share_info.getQrcode());
        cardBean3.setPoster(share_info.getPoster().get(3));
        cardBean3.setNickname(share_info.getNickname());
        cardBean3.setSlogan(share_info.getSlogan());
        CardBean cardBean4 = new CardBean();
        cardBean4.setQrcode(share_info.getQrcode());
        cardBean4.setPoster(share_info.getPoster().get(4));
        cardBean4.setNickname(share_info.getNickname());
        cardBean4.setSlogan(share_info.getSlogan());
        CardBean cardBean5 = new CardBean();
        cardBean5.setQrcode(share_info.getQrcode());
        cardBean5.setPoster(share_info.getPoster().get(5));
        cardBean5.setNickname(share_info.getNickname());
        cardBean5.setSlogan(share_info.getSlogan());
        CardBean cardBean6 = new CardBean();
        cardBean6.setQrcode(share_info.getQrcode());
        cardBean6.setPoster(share_info.getPoster().get(6));
        cardBean6.setNickname(share_info.getNickname());
        cardBean6.setSlogan(share_info.getSlogan());
        list.add(cardBean);
        list.add(cardBean1);
        list.add(cardBean2);
        list.add(cardBean3);
        list.add(cardBean4);
        list.add(cardBean5);
        list.add(cardBean6);


        return list;
    }
}
