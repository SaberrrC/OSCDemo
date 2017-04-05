package com.saberrr.openchina.bean.jumponejump;

import java.util.List;

/**
 * Created by Saberrr on 2017-04-05.
 */

public class SendJumpBean {

    /**
     * code : 1
     * message : SUCCESS
     * result : {"resources":[{"h":181,"href":"https://static.oschina.net/uploads/space/2016/1011/204207_Bpva_231738.jpg","name":"204207_Bpva_231738","thumb":"https://static.oschina.net/uploads/space/2016/1011/204207_Bpva_231738_thumb.jpg","type":"jpg","w":480}],"token":"DB1689BB5F21DB4D"}
     * time : 2016-10-11 20:42:07
     */

    private int        code;
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
         * resources : [{"h":181,"href":"https://static.oschina.net/uploads/space/2016/1011/204207_Bpva_231738.jpg","name":"204207_Bpva_231738","thumb":"https://static.oschina.net/uploads/space/2016/1011/204207_Bpva_231738_thumb.jpg","type":"jpg","w":480}]
         * token : DB1689BB5F21DB4D
         */

        private String              token;
        private List<ResourcesBean> resources;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<ResourcesBean> getResources() {
            return resources;
        }

        public void setResources(List<ResourcesBean> resources) {
            this.resources = resources;
        }

        public static class ResourcesBean {
            /**
             * h : 181
             * href : https://static.oschina.net/uploads/space/2016/1011/204207_Bpva_231738.jpg
             * name : 204207_Bpva_231738
             * thumb : https://static.oschina.net/uploads/space/2016/1011/204207_Bpva_231738_thumb.jpg
             * type : jpg
             * w : 480
             */

            private int    h;
            private String href;
            private String name;
            private String thumb;
            private String type;
            private int    w;

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
