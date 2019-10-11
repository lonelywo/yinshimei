package com.cuci.enticement.bean;

import java.io.Serializable;
import java.util.List;

public class PKbean3 implements Serializable {


    /**
     * code : 1
     * info : 获取团队直推榜成功！
     * data : {"page":{"limit":20,"total":2429,"pages":122,"current":1},"list":[{"mid":41,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132","nickname":"凯婷","teams":857},{"mid":63,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epibsVrV59b1rt2Kzpw2P6GAsxMexrMswKrLwTtEJNicyhd9xibNx1OO4NdBnXxkYUvUaPkg0z79blmQ/132","nickname":"因诗美 顺子","teams":468},{"mid":393,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep2Kn3iatj72WtIOgSFUAic58dk3dFebpMUOiarxo6qBpMs29UK522fE9z4IyaZBFrbMZ8t2veUA1CQQ/132","nickname":"因诗美 安妮","teams":421},{"mid":5833,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7ia7ca5aQfqxMfcBQdMVL4UZWauQTiaVc6GGeiaDja52XTQr5p53FiaXE9Zzr4ibgwVUF17m860Lo6zg/132","nickname":"千千妈呀®因诗美","teams":372},{"mid":402,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqCM7StAaUUOuVCXS6tECmJQibZZpLR97MFd2x0HiaNcmBmCBQjicgRCHQx47MYLkoVR0ZWunoAVU33A/132","nickname":"因诗美  艳姐","teams":335},{"mid":43,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEL3HXyQ70efQlSRIRicFzuY2X29GfezSod5d0O6yFSxn45R5IpFQeBGibXBicC8Y4w7MEW7e3v7icZHWw/132","nickname":"美彤","teams":314},{"mid":4538,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/MOdHDtqwCDfU2sicYMNh7BnJYmibibfJ36iaN6Ppv3YpBqKJckqot3qzHbibanIicpdEEWmykN0VoNyu0pAxNvFDHyjQ/132","nickname":"安琪","teams":265},{"mid":4603,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/AkTpylLVPjB56ebt9qeMlkh6LYCicZk998DICxURlwg9zp4ib7VxaQhYyPHH162tB9uDvjRia6edzYIt5CCsAUJOA/132","nickname":"黄嘉蔓（腊肉学姐）\\ud83d\\udda4","teams":225},{"mid":3288,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/dPP0YeN7pX4OFVa9OEDjxSZ9qx22hQrmvYCicibicnkp6rofmJQM1mgNhcTr6yhliceEu9DgVyEmda0DMia1Tosuc0g/132","nickname":"玲玲","teams":209},{"mid":4484,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLuI1icaupskarCmrZLeYE29ibmicRDhGn5lacROEYJL5iactnhEqh6921kYRAxics9DTBwOhbOpS48gFA/132","nickname":"因诗美 芷汐\\ud83c\\udde8\\ud83c\\uddf3","teams":208},{"mid":46,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/6Y84eFXS1jx9Q9jfq3NznBAujB8HU9vNBAobJSkpGNHPXXzgDfiafbFibqKt0ATmp0pOSGiaO79pKsQA0Oslmj5jw/132","nickname":"张琳","teams":197},{"mid":4672,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLYYKPFbzZzEL920giapVMWUhibuEdibVGJcS3hnibibtOAyjicnibZwJkDznbzVS5Dr24omhHicffbBm6icxg/132","nickname":"忻总®因诗美","teams":178},{"mid":91,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/AibJ0fYfBtGhquaxKYO2BCUIibzaSu6oJsKe8iaEPKViavZKmOXc0hkEgyPIlWybZ5AwcG9TaqmeXW4Cc7CEEMhUKg/132","nickname":"生怡","teams":178},{"mid":6272,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqOEOOKHmWyQqabYbBriahibKs6vGoIw8NZjYMzj2rfscI59uDVbiaEBBP1kEwlh5Blp2JulWeyJyCVw/132","nickname":"万廷","teams":162},{"mid":660,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLB6bFG2QxVduvbQaqZ4Z7B1p1BwWVACiboP7lWicaCx2fw21mk3mYl3R014p11XXxn6pnbcKe6zxjgA/132","nickname":"因诗美  颖姐","teams":162},{"mid":2494,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqcicY5G4538vnVGgn6V2zIQXTByC0NfB0zcqNZKgEiazcj1Ca7UtIcjicYgko95TGbibgiaYvRbDicfFfA/132","nickname":"田大漂亮","teams":151},{"mid":2622,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erU1drpKpmQJO8PrZ6YfeoYCiaibpMbjQR4eWgoPDlZKbzMjia3pfMFXBr9iaw3jFdjnPLfn4CptSkdyw/132","nickname":"雅爷®因诗美全球运营商","teams":148},{"mid":5432,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqRoZB8yJjXAUgj8q0b2ECsUXAVjOiaMia5jMC5H7FbkVdKjKdKQRnvkZRPhWqk2hjtibjh8wj61BJDQ/132","nickname":"大蹦®本人号","teams":147},{"mid":58,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/uer9ZpeoHIoVlDv0cjELCZYs7FFZEzUP5yra40q1ZWhmiclcHaXdIBaznlDSSDjbBnLaWNpJ3OgvIDiaBRcpF91A/132","nickname":"似水年华彩饰界","teams":139},{"mid":122,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK5HNDibbVG8BOUniaKvCuebEsOFELibDx0DKCicnKpkaNArW0Bbqg39xJrbRg3IDQaLKS9aC8RB4fNXQ/132","nickname":"七彩星空","teams":136}],"myself":{"id":41,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132","nickname":"凯婷","teams":857,"ranking":1}}
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

    public static class DataBean implements Serializable{
        /**
         * page : {"limit":20,"total":2429,"pages":122,"current":1}
         * list : [{"mid":41,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132","nickname":"凯婷","teams":857},{"mid":63,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epibsVrV59b1rt2Kzpw2P6GAsxMexrMswKrLwTtEJNicyhd9xibNx1OO4NdBnXxkYUvUaPkg0z79blmQ/132","nickname":"因诗美 顺子","teams":468},{"mid":393,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep2Kn3iatj72WtIOgSFUAic58dk3dFebpMUOiarxo6qBpMs29UK522fE9z4IyaZBFrbMZ8t2veUA1CQQ/132","nickname":"因诗美 安妮","teams":421},{"mid":5833,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep7ia7ca5aQfqxMfcBQdMVL4UZWauQTiaVc6GGeiaDja52XTQr5p53FiaXE9Zzr4ibgwVUF17m860Lo6zg/132","nickname":"千千妈呀®因诗美","teams":372},{"mid":402,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqCM7StAaUUOuVCXS6tECmJQibZZpLR97MFd2x0HiaNcmBmCBQjicgRCHQx47MYLkoVR0ZWunoAVU33A/132","nickname":"因诗美  艳姐","teams":335},{"mid":43,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEL3HXyQ70efQlSRIRicFzuY2X29GfezSod5d0O6yFSxn45R5IpFQeBGibXBicC8Y4w7MEW7e3v7icZHWw/132","nickname":"美彤","teams":314},{"mid":4538,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/MOdHDtqwCDfU2sicYMNh7BnJYmibibfJ36iaN6Ppv3YpBqKJckqot3qzHbibanIicpdEEWmykN0VoNyu0pAxNvFDHyjQ/132","nickname":"安琪","teams":265},{"mid":4603,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/AkTpylLVPjB56ebt9qeMlkh6LYCicZk998DICxURlwg9zp4ib7VxaQhYyPHH162tB9uDvjRia6edzYIt5CCsAUJOA/132","nickname":"黄嘉蔓（腊肉学姐）\\ud83d\\udda4","teams":225},{"mid":3288,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/dPP0YeN7pX4OFVa9OEDjxSZ9qx22hQrmvYCicibicnkp6rofmJQM1mgNhcTr6yhliceEu9DgVyEmda0DMia1Tosuc0g/132","nickname":"玲玲","teams":209},{"mid":4484,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLuI1icaupskarCmrZLeYE29ibmicRDhGn5lacROEYJL5iactnhEqh6921kYRAxics9DTBwOhbOpS48gFA/132","nickname":"因诗美 芷汐\\ud83c\\udde8\\ud83c\\uddf3","teams":208},{"mid":46,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/6Y84eFXS1jx9Q9jfq3NznBAujB8HU9vNBAobJSkpGNHPXXzgDfiafbFibqKt0ATmp0pOSGiaO79pKsQA0Oslmj5jw/132","nickname":"张琳","teams":197},{"mid":4672,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLYYKPFbzZzEL920giapVMWUhibuEdibVGJcS3hnibibtOAyjicnibZwJkDznbzVS5Dr24omhHicffbBm6icxg/132","nickname":"忻总®因诗美","teams":178},{"mid":91,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/AibJ0fYfBtGhquaxKYO2BCUIibzaSu6oJsKe8iaEPKViavZKmOXc0hkEgyPIlWybZ5AwcG9TaqmeXW4Cc7CEEMhUKg/132","nickname":"生怡","teams":178},{"mid":6272,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqOEOOKHmWyQqabYbBriahibKs6vGoIw8NZjYMzj2rfscI59uDVbiaEBBP1kEwlh5Blp2JulWeyJyCVw/132","nickname":"万廷","teams":162},{"mid":660,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLB6bFG2QxVduvbQaqZ4Z7B1p1BwWVACiboP7lWicaCx2fw21mk3mYl3R014p11XXxn6pnbcKe6zxjgA/132","nickname":"因诗美  颖姐","teams":162},{"mid":2494,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqcicY5G4538vnVGgn6V2zIQXTByC0NfB0zcqNZKgEiazcj1Ca7UtIcjicYgko95TGbibgiaYvRbDicfFfA/132","nickname":"田大漂亮","teams":151},{"mid":2622,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erU1drpKpmQJO8PrZ6YfeoYCiaibpMbjQR4eWgoPDlZKbzMjia3pfMFXBr9iaw3jFdjnPLfn4CptSkdyw/132","nickname":"雅爷®因诗美全球运营商","teams":148},{"mid":5432,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqRoZB8yJjXAUgj8q0b2ECsUXAVjOiaMia5jMC5H7FbkVdKjKdKQRnvkZRPhWqk2hjtibjh8wj61BJDQ/132","nickname":"大蹦®本人号","teams":147},{"mid":58,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/uer9ZpeoHIoVlDv0cjELCZYs7FFZEzUP5yra40q1ZWhmiclcHaXdIBaznlDSSDjbBnLaWNpJ3OgvIDiaBRcpF91A/132","nickname":"似水年华彩饰界","teams":139},{"mid":122,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK5HNDibbVG8BOUniaKvCuebEsOFELibDx0DKCicnKpkaNArW0Bbqg39xJrbRg3IDQaLKS9aC8RB4fNXQ/132","nickname":"七彩星空","teams":136}]
         * myself : {"id":41,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132","nickname":"凯婷","teams":857,"ranking":1}
         */

        private PageBean page;
        private MyselfBean myself;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public MyselfBean getMyself() {
            return myself;
        }

        public void setMyself(MyselfBean myself) {
            this.myself = myself;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean implements Serializable{
            /**
             * limit : 20
             * total : 2429
             * pages : 122
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

        public static class MyselfBean implements Serializable{
            /**
             * id : 41
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132
             * nickname : 凯婷
             * teams : 857
             * ranking : 1
             */

            private int id;
            private String headimg;
            private String nickname;
            private int teams;
            private int ranking;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getTeams() {
                return teams;
            }

            public void setTeams(int teams) {
                this.teams = teams;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }
        }

        public static class ListBean implements Serializable{
            /**
             * mid : 41
             * headimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZGu5BWDoZKiallib0WgaV3VkLvARwf6ZLUl3N81n6N1QMCfhCdJ9ia5iaq7nvCvrl7JFnHQp2cKKfgQ/132
             * nickname : 凯婷
             * teams : 857
             */

            private int mid;
            private String headimg;
            private String nickname;
            private int teams;

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getTeams() {
                return teams;
            }

            public void setTeams(int teams) {
                this.teams = teams;
            }
        }
    }
}
