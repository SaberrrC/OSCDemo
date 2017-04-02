package com.saberrr.openchina.bean;

import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/1.
 */

public class InformationHearBean {

    /**
     * code : 1
     * message : success
     * result : {"items":[{"detail":"","href":"https://www.oschina.net/news/83188/2017-april-yuanchuanghui","id":83188,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_0544a761-285d-454a-b9ad-dc08e14cc55b.jpg","name":"源创会 | 武汉、长沙开始报名啦！","pubDate":"2017-03-27 14:06:26","type":6},{"detail":"","href":"https://www.oschina.net/question/2720166_2236230","id":2236230,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_4f8d9027-60a5-4adb-ae2b-464847f53406.jpg","name":"高手问答 | Redis 开发与运维","pubDate":"2017-03-27 18:14:52","type":2},{"detail":"","href":"https://www.oschina.net/news/82966/web-developer-roadmap-in-2017","id":82966,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_9ee782ef-7409-40ac-8dac-56f56fbd6228.jpg","name":"2017 最新 Web 开发者成长路线图","pubDate":"2017-03-20 16:56:54","type":6},{"detail":"","href":"https://www.oschina.net/news/83177/reasons-to-use-linux","id":83177,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_c406f1e6-c1f2-4615-b465-8baf40d58dcc.jpg","name":"为何 Linux 如此深得人心","pubDate":"2017-03-27 14:17:45","type":6},{"detail":"","href":"https://www.oschina.net/news/83164/stack-overflow-developer-survey-results-2017","id":83164,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_fb63a74f-0f17-428a-b958-27a894fed40e.jpg","name":"StackOverflow 2017 开发者调查报告","pubDate":"2017-03-27 18:13:37","type":6}],"nextPageToken":"61AF0C190D6BD629","prevPageToken":"3EA621243546C8A5","requestCount":5,"responseCount":5,"totalResults":5}
     * time : 2017-04-02 09:45:24
     */

    private int code;
    private String     message;
    private ResultBean result;
    private String     time;

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
         * items : [{"detail":"","href":"https://www.oschina.net/news/83188/2017-april-yuanchuanghui","id":83188,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_0544a761-285d-454a-b9ad-dc08e14cc55b.jpg","name":"源创会 | 武汉、长沙开始报名啦！","pubDate":"2017-03-27 14:06:26","type":6},{"detail":"","href":"https://www.oschina.net/question/2720166_2236230","id":2236230,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_4f8d9027-60a5-4adb-ae2b-464847f53406.jpg","name":"高手问答 | Redis 开发与运维","pubDate":"2017-03-27 18:14:52","type":2},{"detail":"","href":"https://www.oschina.net/news/82966/web-developer-roadmap-in-2017","id":82966,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_9ee782ef-7409-40ac-8dac-56f56fbd6228.jpg","name":"2017 最新 Web 开发者成长路线图","pubDate":"2017-03-20 16:56:54","type":6},{"detail":"","href":"https://www.oschina.net/news/83177/reasons-to-use-linux","id":83177,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_c406f1e6-c1f2-4615-b465-8baf40d58dcc.jpg","name":"为何 Linux 如此深得人心","pubDate":"2017-03-27 14:17:45","type":6},{"detail":"","href":"https://www.oschina.net/news/83164/stack-overflow-developer-survey-results-2017","id":83164,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_fb63a74f-0f17-428a-b958-27a894fed40e.jpg","name":"StackOverflow 2017 开发者调查报告","pubDate":"2017-03-27 18:13:37","type":6}]
         * nextPageToken : 61AF0C190D6BD629
         * prevPageToken : 3EA621243546C8A5
         * requestCount : 5
         * responseCount : 5
         * totalResults : 5
         */

        private String nextPageToken;
        private String          prevPageToken;
        private int             requestCount;
        private int             responseCount;
        private int             totalResults;
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
             * detail :
             * href : https://www.oschina.net/news/83188/2017-april-yuanchuanghui
             * id : 83188
             * img : https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_0544a761-285d-454a-b9ad-dc08e14cc55b.jpg
             * name : 源创会 | 武汉、长沙开始报名啦！
             * pubDate : 2017-03-27 14:06:26
             * type : 6
             */

            private String detail;
            private String href;
            private int    id;
            private String img;
            private String name;
            private String pubDate;
            private int    type;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
