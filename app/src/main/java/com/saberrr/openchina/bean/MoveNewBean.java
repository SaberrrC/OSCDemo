package com.saberrr.openchina.bean;

import java.util.List;

/**
 * Created by tt on 2017/4/1.
 */

public class MoveNewBean {


    private int code;
    private String message;
    private ResultBean result;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ResultBean {
        /**
         * items : [{"appClient":1,"author":{"id":72953,"identity":{"officialMember":false,"softwareAuthor":false},"name":"啤酒屋里的猫","portrait":"http://static.oschina.net/uploads/user/36/72953_50.jpg","relation":0},"commentCount":0,"content":"祝公司青明节快乐 还在上班的我","href":"https://my.oschina.net/denglaben/tweet/12770130","id":12770130,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:45:47","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":3,"author":{"id":2339571,"identity":{"officialMember":false,"softwareAuthor":false},"name":"奔跑先生","portrait":"http://static.oschina.net/uploads/user/1169/2339571_50.jpg?t=1471517502000","relation":0},"commentCount":0,"content":"凤凰岭走起走起走起走起走起走起。。。","href":"https://my.oschina.net/kevin1992/tweet/12770127","id":12770127,"likeCount":1,"liked":false,"pubDate":"2017-04-02 08:45:07","statistics":{"comment":0,"favCount":0,"like":1,"transmit":0,"view":0}},{"appClient":4,"author":{"id":567557,"identity":{"officialMember":false,"softwareAuthor":false},"name":"走在路上","portrait":"http://static.oschina.net/uploads/user/283/567557_50.jpg?t=1386948341000","relation":0},"commentCount":0,"content":"人是看不到自己的，想看清自己的脸，需要一面镜子，想看清自己的心，需要另一个人","href":"https://my.oschina.net/567557/tweet/12770123","id":12770123,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:41:39","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":3,"author":{"id":2738788,"identity":{"officialMember":false,"softwareAuthor":false},"name":"口可呵","portrait":"http://static.oschina.net/uploads/user/1369/2738788_50.jpg?t=1486991249000","relation":0},"commentCount":0,"content":"去上班[流泪]","href":"https://my.oschina.net/cylfc/tweet/12770104","id":12770104,"likeCount":2,"liked":false,"pubDate":"2017-04-02 08:22:46","statistics":{"comment":0,"favCount":0,"like":2,"transmit":0,"view":0}},{"appClient":4,"author":{"id":2564723,"identity":{"officialMember":false,"softwareAuthor":false},"name":"假装搞IT","portrait":"http://static.oschina.net/uploads/user/1282/2564723_50.jpg?t=1457870058000","relation":0},"commentCount":4,"content":"对特么共享单车已经绝望了！一堆的故障车有人处理没有啊！！！","href":"https://my.oschina.net/xslzero/tweet/12770100","id":12770100,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:21:20","statistics":{"comment":4,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":3,"author":{"id":2445220,"identity":{"officialMember":true,"softwareAuthor":false},"name":"黄海彬","portrait":"http://static.oschina.net/uploads/user/1222/2445220_50.jpg?t=1488123449000","relation":0},"commentCount":0,"content":"我永远都忘不了那天女神对我说过的那句话：借过一下\u2026","href":"https://my.oschina.net/huanghaibin/tweet/12770079","id":12770079,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:07:01","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":1,"author":{"id":2611368,"identity":{"officialMember":false,"softwareAuthor":false},"name":"panshengZ","portrait":"http://static.oschina.net/uploads/user/1305/2611368_50.jpg?t=1465445056000","relation":0},"commentCount":1,"content":"osc啥时候换成https？","href":"https://my.oschina.net/u/2611368/tweet/12770078","id":12770078,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:06:54","statistics":{"comment":1,"favCount":0,"like":0,"transmit":0,"view":0}},{"about":{"commentCount":30,"content":"        Java 已经22岁了，依靠强大的功能、庞大的开发社区和无人能及的生态系统，长期占据世界编程语言排行榜首，成为当之无愧的业界之王。本人在大学时期被这种很有艺术性的开发语言所吸引，果断抛弃C，学习方式很简单，只看JDK API源码，直到现在都是如此。刚毕业就一直从事Java开发方面的工作，至今也有十来年了。从JSP、WebWork到Struts、JSF，从JDBC、Hibernate到TopLink、JPA。从NIO、Mina到Netty、Grizzly。很多...","href":"https://my.oschina.net/redkale/blog/868369","id":868369,"statistics":{"comment":30,"favCount":0,"like":0,"transmit":5,"view":1518},"title":"Redkale 让你重新认识Java","type":3,"viewCount":1518},"appClient":3,"author":{"id":3105261,"identity":{"officialMember":false,"softwareAuthor":false},"name":"BlankCat","portrait":"http://www.oschina.net/img/portrait.gif","relation":0},"commentCount":0,"content":"。。。","href":"https://my.oschina.net/u/3105261/tweet/12770076","id":12770076,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:06:21","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":3,"author":{"id":156558,"identity":{"officialMember":true,"softwareAuthor":true},"name":"英强","portrait":"http://static.oschina.net/uploads/user/78/156558_50.jpg?t=1449724406000","relation":0},"commentCount":0,"content":"睡了个假觉","href":"https://my.oschina.net/cevin15/tweet/12770069","id":12770069,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:03:01","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":0,"author":{"id":2554571,"identity":{"officialMember":false,"softwareAuthor":false},"name":"夜雨孤独客2","portrait":"http://www.oschina.net/img/portrait.gif","relation":0},"commentCount":0,"content":"能支持jwt的用户认证方式吗？现在后台分布式微服务架构都不太赞成用session。 <a href='https://www.oschina.net/p/faygo' class='project' target='_blank' title='Go Web API 利器Faygo'>#Faygo#<\/a>","href":"https://my.oschina.net/u/2554571/tweet/12770062","id":12770062,"likeCount":0,"liked":false,"pubDate":"2017-04-02 08:00:13","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":1,"author":{"id":1452648,"identity":{"officialMember":false,"softwareAuthor":false},"name":"Adpex","portrait":"http://static.oschina.net/uploads/user/726/1452648_50.jpeg?t=1487563390000","relation":0},"commentCount":1,"content":"上班26天制的公司，明天才能放假、、并且才两天。","href":"https://my.oschina.net/sumingxiaoyao/tweet/12770061","id":12770061,"likeCount":0,"liked":false,"pubDate":"2017-04-02 07:59:46","statistics":{"comment":1,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":3,"author":{"id":12,"identity":{"officialMember":true,"softwareAuthor":true},"name":"红薯","portrait":"http://static.oschina.net/uploads/user/0/12_50.jpg?t=1421200584000","relation":0},"commentCount":0,"content":"祝大家清明节快乐","href":"https://my.oschina.net/javayou/tweet/12770052","id":12770052,"likeCount":4,"liked":false,"pubDate":"2017-04-02 07:51:21","statistics":{"comment":0,"favCount":0,"like":4,"transmit":0,"view":0}},{"appClient":4,"author":{"id":2670457,"identity":{"officialMember":true,"softwareAuthor":false},"name":"空格郑","portrait":"http://static.oschina.net/uploads/user/1335/2670457_50.jpg?t=1464973128000","relation":0},"commentCount":0,"content":"你们好早起哦","href":"https://my.oschina.net/zhengyingyu/tweet/12770049","id":12770049,"likeCount":0,"liked":false,"pubDate":"2017-04-02 07:50:17","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":1,"author":{"id":2318770,"identity":{"officialMember":false,"softwareAuthor":false},"name":"gznovice","portrait":"http://static.oschina.net/uploads/user/1159/2318770_50.jpg?t=1423983221000","relation":0},"commentCount":0,"content":"5km 26分26秒，自我感觉良好","href":"https://my.oschina.net/u/2318770/tweet/12770046","id":12770046,"likeCount":0,"liked":false,"pubDate":"2017-04-02 07:45:58","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":3,"author":{"id":2425521,"identity":{"officialMember":true,"softwareAuthor":false},"name":"youngiiii","portrait":"http://static.oschina.net/uploads/user/1212/2425521_50.jpg?t=1438824576000","relation":0},"commentCount":0,"content":"浪去～","href":"https://my.oschina.net/youngiii/tweet/12770044","id":12770044,"likeCount":0,"liked":false,"pubDate":"2017-04-02 07:45:09","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":4,"author":{"id":1028150,"identity":{"officialMember":true,"softwareAuthor":true},"name":"成熟的毛毛虫","portrait":"http://static.oschina.net/uploads/user/514/1028150_50.jpeg?t=1475119864000","relation":0},"commentCount":1,"content":"堵塞，再美的风景也无心欣赏","href":"https://my.oschina.net/benhaile/tweet/12770042","id":12770042,"images":[{"h":1104,"href":"http://static.oschina.net/uploads/space/2017/0402/074255_Q3eX_1028150.png","name":"074255_Q3eX_1028150","thumb":"http://static.oschina.net/uploads/space/2017/0402/074255_Q3eX_1028150_thumb.png","type":"png","w":828}],"likeCount":0,"liked":false,"pubDate":"2017-04-02 07:42:56","statistics":{"comment":1,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":1,"author":{"id":139664,"identity":{"officialMember":true,"softwareAuthor":false},"name":"Force武装卫队","portrait":"http://static.oschina.net/uploads/user/69/139664_50.jpg?t=1445211569000","relation":0},"commentCount":1,"content":"Visual C++ 将支持  WebAssembly","href":"https://my.oschina.net/GIIoOS/tweet/12770040","id":12770040,"likeCount":0,"liked":false,"pubDate":"2017-04-02 07:41:47","statistics":{"comment":1,"favCount":0,"like":0,"transmit":0,"view":0}},{"appClient":1,"author":{"id":87567,"identity":{"officialMember":false,"softwareAuthor":false},"name":"蓝桥书生","portrait":"http://static.oschina.net/uploads/user/43/87567_50.jpg?t=1371652265000","relation":0},"commentCount":0,"content":"一会就可以回家了","href":"https://my.oschina.net/hopeMan/tweet/12770033","id":12770033,"likeCount":0,"liked":false,"pubDate":"2017-04-02 07:35:56","statistics":{"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}},{"about":{"commentCount":3,"content":"猜猜这是什么，好么？","href":"https://my.oschina.net/qiujuer/tweet/12766769","id":12766769,"images":[{"h":1920,"href":"http://static.oschina.net/uploads/space/2017/0401/180914_NmLf_1377710.jpg","name":"180914_NmLf_1377710","thumb":"http://static.oschina.net/uploads/space/2017/0401/180914_NmLf_1377710_thumb.jpg","type":"jpg","w":1080},{"h":1280,"href":"http://static.oschina.net/uploads/space/2017/0401/180915_PoWd_1377710.jpg","name":"180915_PoWd_1377710","thumb":"http://static.oschina.net/uploads/space/2017/0401/180915_PoWd_1377710_thumb.jpg","type":"jpg","w":720},{"h":1920,"href":"http://static.oschina.net/uploads/space/2017/0401/180916_fcsp_1377710.jpg","name":"180916_fcsp_1377710","thumb":"http://static.oschina.net/uploads/space/2017/0401/180916_fcsp_1377710_thumb.jpg","type":"jpg","w":1080},{"h":1920,"href":"http://static.oschina.net/uploads/space/2017/0401/180917_Iiuo_1377710.jpg","name":"180917_Iiuo_1377710","thumb":"http://static.oschina.net/uploads/space/2017/0401/180917_Iiuo_1377710_thumb.jpg","type":"jpg","w":1080},{"h":1920,"href":"http://static.oschina.net/uploads/space/2017/0401/180918_R2XW_1377710.jpg","name":"180918_R2XW_1377710","thumb":"http://static.oschina.net/uploads/space/2017/0401/180918_R2XW_1377710_thumb.jpg","type":"jpg","w":1080}],"statistics":{"comment":3,"favCount":0,"like":4,"transmit":1,"view":0},"title":"Qiujuer","type":100,"viewCount":0},"appClient":3,"author":{"id":1377710,"identity":{"officialMember":true,"softwareAuthor":false},"name":"Qiujuer","portrait":"http://static.oschina.net/uploads/user/688/1377710_50.jpg?t=1484905232000","relation":0},"commentCount":4,"content":"祝大家节日快乐，送上一发。","href":"https://my.oschina.net/qiujuer/tweet/12770031","id":12770031,"likeCount":4,"liked":false,"pubDate":"2017-04-02 07:35:29","statistics":{"comment":4,"favCount":0,"like":4,"transmit":0,"view":0}},{"appClient":1,"author":{"id":1053201,"identity":{"officialMember":false,"softwareAuthor":false},"name":"zheng_chao","portrait":"http://static.oschina.net/uploads/user/526/1053201_50.gif?t=1441856325000","relation":0},"commentCount":0,"content":"清明时节雨纷纷，路上行人欲断魂。","href":"https://my.oschina.net/u/1053201/tweet/12770028","id":12770028,"likeCount":1,"liked":false,"pubDate":"2017-04-02 07:34:40","statistics":{"comment":0,"favCount":0,"like":1,"transmit":0,"view":0}}]
         * nextPageToken : 1535A0F08550D5A54AD6659CDD5740BA
         * prevPageToken : A1177B82BD7EBA599A47EC1668BC550B
         * requestCount : 20
         * responseCount : 20
         * totalResults : 1000
         */

        private String nextPageToken;
        private String prevPageToken;
        private int requestCount;
        private int responseCount;
        private int totalResults;
        private List<ItemsBean> items;

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public String getPrevPageToken() {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken) {
            this.prevPageToken = prevPageToken;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }

        public int getResponseCount() {
            return responseCount;
        }

        public void setResponseCount(int responseCount) {
            this.responseCount = responseCount;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * appClient : 1
             * author : {"id":72953,"identity":{"officialMember":false,"softwareAuthor":false},"name":"啤酒屋里的猫","portrait":"http://static.oschina.net/uploads/user/36/72953_50.jpg","relation":0}
             * commentCount : 0
             * content : 祝公司青明节快乐 还在上班的我
             * href : https://my.oschina.net/denglaben/tweet/12770130
             * id : 12770130
             * likeCount : 0
             * liked : false
             * pubDate : 2017-04-02 08:45:47
             * statistics : {"comment":0,"favCount":0,"like":0,"transmit":0,"view":0}
             * about : {"commentCount":30,"content":"        Java 已经22岁了，依靠强大的功能、庞大的开发社区和无人能及的生态系统，长期占据世界编程语言排行榜首，成为当之无愧的业界之王。本人在大学时期被这种很有艺术性的开发语言所吸引，果断抛弃C，学习方式很简单，只看JDK API源码，直到现在都是如此。刚毕业就一直从事Java开发方面的工作，至今也有十来年了。从JSP、WebWork到Struts、JSF，从JDBC、Hibernate到TopLink、JPA。从NIO、Mina到Netty、Grizzly。很多...","href":"https://my.oschina.net/redkale/blog/868369","id":868369,"statistics":{"comment":30,"favCount":0,"like":0,"transmit":5,"view":1518},"title":"Redkale 让你重新认识Java","type":3,"viewCount":1518}
             * images : [{"h":1104,"href":"http://static.oschina.net/uploads/space/2017/0402/074255_Q3eX_1028150.png","name":"074255_Q3eX_1028150","thumb":"http://static.oschina.net/uploads/space/2017/0402/074255_Q3eX_1028150_thumb.png","type":"png","w":828}]
             */

            private int appClient;
            private AuthorBean author;
            private int commentCount;
            private String content;
            private String href;
            private int id;
            private int likeCount;
            private boolean liked;
            private String pubDate;
            private StatisticsBean statistics;
            private AboutBean about;
            private List<ImagesBean> images;

            public int getAppClient() {
                return appClient;
            }

            public void setAppClient(int appClient) {
                this.appClient = appClient;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public boolean isLiked() {
                return liked;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public StatisticsBean getStatistics() {
                return statistics;
            }

            public void setStatistics(StatisticsBean statistics) {
                this.statistics = statistics;
            }

            public AboutBean getAbout() {
                return about;
            }

            public void setAbout(AboutBean about) {
                this.about = about;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class AuthorBean {
                /**
                 * id : 72953
                 * identity : {"officialMember":false,"softwareAuthor":false}
                 * name : 啤酒屋里的猫
                 * portrait : http://static.oschina.net/uploads/user/36/72953_50.jpg
                 * relation : 0
                 */

                private int id;
                private IdentityBean identity;
                private String name;
                private String portrait;
                private int relation;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public IdentityBean getIdentity() {
                    return identity;
                }

                public void setIdentity(IdentityBean identity) {
                    this.identity = identity;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPortrait() {
                    return portrait;
                }

                public void setPortrait(String portrait) {
                    this.portrait = portrait;
                }

                public int getRelation() {
                    return relation;
                }

                public void setRelation(int relation) {
                    this.relation = relation;
                }

                public static class IdentityBean {
                    /**
                     * officialMember : false
                     * softwareAuthor : false
                     */

                    private boolean officialMember;
                    private boolean softwareAuthor;

                    public boolean isOfficialMember() {
                        return officialMember;
                    }

                    public void setOfficialMember(boolean officialMember) {
                        this.officialMember = officialMember;
                    }

                    public boolean isSoftwareAuthor() {
                        return softwareAuthor;
                    }

                    public void setSoftwareAuthor(boolean softwareAuthor) {
                        this.softwareAuthor = softwareAuthor;
                    }
                }
            }

            public static class StatisticsBean {
                /**
                 * comment : 0
                 * favCount : 0
                 * like : 0
                 * transmit : 0
                 * view : 0
                 */

                private int comment;
                private int favCount;
                private int like;
                private int transmit;
                private int view;

                public int getComment() {
                    return comment;
                }

                public void setComment(int comment) {
                    this.comment = comment;
                }

                public int getFavCount() {
                    return favCount;
                }

                public void setFavCount(int favCount) {
                    this.favCount = favCount;
                }

                public int getLike() {
                    return like;
                }

                public void setLike(int like) {
                    this.like = like;
                }

                public int getTransmit() {
                    return transmit;
                }

                public void setTransmit(int transmit) {
                    this.transmit = transmit;
                }

                public int getView() {
                    return view;
                }

                public void setView(int view) {
                    this.view = view;
                }
            }

            public static class AboutBean {
                /**
                 * commentCount : 30
                 * content :         Java 已经22岁了，依靠强大的功能、庞大的开发社区和无人能及的生态系统，长期占据世界编程语言排行榜首，成为当之无愧的业界之王。本人在大学时期被这种很有艺术性的开发语言所吸引，果断抛弃C，学习方式很简单，只看JDK API源码，直到现在都是如此。刚毕业就一直从事Java开发方面的工作，至今也有十来年了。从JSP、WebWork到Struts、JSF，从JDBC、Hibernate到TopLink、JPA。从NIO、Mina到Netty、Grizzly。很多...
                 * href : https://my.oschina.net/redkale/blog/868369
                 * id : 868369
                 * statistics : {"comment":30,"favCount":0,"like":0,"transmit":5,"view":1518}
                 * title : Redkale 让你重新认识Java
                 * type : 3
                 * viewCount : 1518
                 */

                private int commentCount;
                private String content;
                private String href;
                private int id;
                private StatisticsBeanX statistics;
                private String title;
                private int type;
                private int viewCount;

                public int getCommentCount() {
                    return commentCount;
                }

                public void setCommentCount(int commentCount) {
                    this.commentCount = commentCount;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public StatisticsBeanX getStatistics() {
                    return statistics;
                }

                public void setStatistics(StatisticsBeanX statistics) {
                    this.statistics = statistics;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getViewCount() {
                    return viewCount;
                }

                public void setViewCount(int viewCount) {
                    this.viewCount = viewCount;
                }

                public static class StatisticsBeanX {
                    /**
                     * comment : 30
                     * favCount : 0
                     * like : 0
                     * transmit : 5
                     * view : 1518
                     */

                    private int comment;
                    private int favCount;
                    private int like;
                    private int transmit;
                    private int view;

                    public int getComment() {
                        return comment;
                    }

                    public void setComment(int comment) {
                        this.comment = comment;
                    }

                    public int getFavCount() {
                        return favCount;
                    }

                    public void setFavCount(int favCount) {
                        this.favCount = favCount;
                    }

                    public int getLike() {
                        return like;
                    }

                    public void setLike(int like) {
                        this.like = like;
                    }

                    public int getTransmit() {
                        return transmit;
                    }

                    public void setTransmit(int transmit) {
                        this.transmit = transmit;
                    }

                    public int getView() {
                        return view;
                    }

                    public void setView(int view) {
                        this.view = view;
                    }
                }
            }

            public static class ImagesBean {
                /**
                 * h : 1104
                 * href : http://static.oschina.net/uploads/space/2017/0402/074255_Q3eX_1028150.png
                 * name : 074255_Q3eX_1028150
                 * thumb : http://static.oschina.net/uploads/space/2017/0402/074255_Q3eX_1028150_thumb.png
                 * type : png
                 * w : 828
                 */

                private int h;
                private String href;
                private String name;
                private String thumb;
                private String type;
                private int w;

                public int getH() {
                    return h;
                }

                public void setH(int h) {
                    this.h = h;
                }

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getW() {
                    return w;
                }

                public void setW(int w) {
                    this.w = w;
                }
            }
        }
    }
}
