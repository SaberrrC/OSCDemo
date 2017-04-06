package com.saberrr.openchina.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tt on 2017/4/1.
 */

public class MoveNewBean implements Serializable{


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

    public static class ResultBean implements Serializable{


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

        public static class ItemsBean implements Serializable {
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



            public static class AuthorBean implements Serializable{
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

                public static class IdentityBean implements Serializable{
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

            public static class StatisticsBean implements Serializable{
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

            public static class AboutBean implements Serializable{
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

                public static class StatisticsBeanX implements Serializable{
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

            public static class ImagesBean implements Serializable{
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
