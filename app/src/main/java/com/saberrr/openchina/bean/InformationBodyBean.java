package com.saberrr.openchina.bean;

import java.util.List;

/**
 * Created by 丁银晨 on 2017/4/2.
 */

public class InformationBodyBean {

    /**
     * code : 1
     * message : success
     * result : {"items":[{"author":"局长","body":"软件周刊（03.26 \u2014 04.01）：本周热门软件更新 \u2014 IntelliJ IDEA 2017.1.1 EAP 发布；DragonFly BSD 4.8.0 发布，提高了内核性能；renren-securi...","commentCount":0,"href":"https://my.oschina.net/editorial-story/blog/872463","id":872463,"pubDate":"2017-04-02 08:13:40","recommend":true,"title":"软件周刊 | Chrome 58 测试版支持 PWA 沉浸式全屏体验","type":3,"viewCount":193},{"author":"oschina","body":"百合词典，使用 C++ 开发，图形界面框架库采用 Qt5 QML，无其他任何依赖库。","commentCount":0,"href":"https://git.oschina.net/lieefu/lilydict","id":83483,"pubDate":"2017-04-02 07:48:52","recommend":true,"title":"码云推荐 | 开源跨平台桌面版词典软件","type":0,"viewCount":3},{"author":"oschina","body":"当编写网络爬虫或下载器时，在 Java 中实现 URL 编码和解码是一个很常见的要求。本文的重点是创建用于对所传递的 URL 进行编码和解码的模块。...","commentCount":0,"href":"https://www.oschina.net/translate/url-encoding-and-decoding-using-java","id":10003794,"pubDate":"2017-04-02 07:47:49","recommend":true,"title":"协作翻译 | Java 实现的 URL 编码和解码技术","type":4,"viewCount":286},{"author":"oschina","body":"本文从Python模块的制作、导入、查看和使用入手，重点介绍在Windows环境下如何处理和使用Python模块。最后一部分介绍一些Python常用的模块供读者参...","commentCount":0,"href":"https://my.oschina.net/SamYjy/blog/872413","id":872413,"pubDate":"2017-04-02 07:45:17","recommend":true,"title":"每日一博 | Python 进阶篇学习笔记二：Python 模块","type":3,"viewCount":195},{"author":"oschina","body":"有以下特性：向APNS（Apple Push Notification Service）发送推送通知非常容易（完全不需要配置）；支持错误响应代码；自动检测开发/生产环境；支持...","commentCount":0,"href":"https://www.oschina.net/p/knuff","id":45002,"pubDate":"2017-04-02 07:42:21","recommend":true,"title":"Knuff \u2014\u2014 苹果信息推送服务的调试应用","type":1,"viewCount":38},{"author":"小小编辑","body":"OSC愚人节的特别节目，网站首页增加了弹幕效果，但有人考虑过小小编辑写乱弹的感受么？满屏幕挤满了奇怪动弹，\u201c拥挤到怀孕。\u201d小小编辑划拉了一个...","commentCount":16,"href":"https://my.oschina.net/xxiaobian/blog/872530","id":872530,"pubDate":"2017-04-02 07:37:19","recommend":false,"title":"OSChina 周日乱弹 \u2014\u2014 大白，我对这个世界好失望","type":3,"viewCount":240},{"author":"局长","body":"微软一直在通过各种手段来敦促用户升级至 Windows 10 系统，但实际上用户并不买账。 根据 StatCounter 三月份最新统计数据表明，Windows 7 仍然是全...","commentCount":7,"href":"https://www.oschina.net/news/83478/nearly-half-of-people-still-use-windows7","id":83478,"pubDate":"2017-04-02 07:22:54","recommend":false,"title":"数据显示，近半数人依然坚持在用 Windows 7","type":6,"viewCount":142},{"author":"局长","body":"NetMarketShare 之前关于台式机浏览器市场份额的报告表示，Google Chrome 市场份额正在快速上升，而 Edge 浏览器市场份额正在以蜗牛的速度前进。而...","commentCount":0,"href":"https://www.oschina.net/news/83477/desktop-browsers-market-share-unchanged-this-month","id":83477,"pubDate":"2017-04-02 07:20:22","recommend":false,"title":"NetMarketShare:本月桌面浏览器市场份额几乎没有变化","type":6,"viewCount":63},{"author":"局长","body":"此前，谷歌方面宣布因为在最终测试中发现了 bug，导致外界期待已久的 Android Wear 2.0 系统更新推送不得不进行延期，但第一批推送的三款智能手表 ...","commentCount":0,"href":"https://www.oschina.net/news/83476/android-wear-2-0-update-continue-to-push-today","id":83476,"pubDate":"2017-04-02 07:17:39","recommend":false,"title":"Bug 已被消灭：Android Wear 2.0 更新推送继续","type":6,"viewCount":68},{"author":"王练","body":"Kodi v17.0 Candidate3 发布了，该版本完成的修复如下： 更新 Estuary 并修正错误 恢复某些文件导致视频播放问题的更改 修复鼠标无法到达 OSX 屏幕...","commentCount":0,"href":"https://www.oschina.net/news/83475/kodi-17-0-candidate3","id":83475,"pubDate":"2017-04-02 05:36:04","recommend":false,"title":"Kodi v17.0 Candidate3 发布，v18 命名 \u201cLeia\u201d ","type":6,"viewCount":84},{"author":"王练","body":"Muse-UI 是基于 Vue2.0 的 Material Design UI 框架。 Muse-UI 2.0.1 更新内容如下： Bugs 修复在app-bar中为flat-button增加href属性后按钮会发生...","commentCount":1,"href":"https://www.oschina.net/news/83474/muse-ui-2-0-1","id":83474,"pubDate":"2017-04-02 05:28:29","recommend":false,"title":"Muse-UI 2.0.1 发布，Material Design UI 框架","type":6,"viewCount":223},{"author":"王练","body":"Logstash 是一个开源的服务端数据处理流程，可同时从多个源中获取数据，将其转换，然后将其发送到\u201c收藏\u201d中，目前拥有超过 200 个插件。它能集中、...","commentCount":0,"href":"https://www.oschina.net/news/83473/logstash-5-3-0","id":83473,"pubDate":"2017-04-02 05:25:12","recommend":false,"title":"Logstash 5.3.0 发布，开源服务端数据处理流程","type":6,"viewCount":82},{"author":"王练","body":"Eclipse Che 是一个高性能的基于浏览器的集成开发环境，通过提供结构化的工作区、项目输入、模块化扩展插件来支持 Codenvy 的引擎。Eclipse Che 采...","commentCount":1,"href":"https://www.oschina.net/news/83472/eclipse-che-5-6-0","id":83472,"pubDate":"2017-04-02 05:24:31","recommend":false,"title":"Eclipse Che 5.6.0 发布，在线集成开发环境","type":6,"viewCount":216},{"author":"王练","body":"Material UI 是一组实现 Google Material Design 规范的 react 组件,是一个前端 js 框架，主要用在 web 领域。 Material UI 1.0.0 alpha9 的发布，...","commentCount":1,"href":"https://www.oschina.net/news/83471/material-ui-1-0-0-alpha9","id":83471,"pubDate":"2017-04-02 05:23:41","recommend":false,"title":"Material UI 1.0.0-alpha9 发布，前端 js 框架","type":6,"viewCount":114},{"author":"王练","body":"Apache Geode 1.1.1 发布了，Apache Geode 是一个数据管理平台，提供实时的、一致的、贯穿整个云架构地访问数据关键型应用。 本次更新内容如下： ...","commentCount":0,"href":"https://www.oschina.net/news/83470/apache-geode-1-1-1","id":83470,"pubDate":"2017-04-02 05:18:08","recommend":false,"title":"Apache Geode 1.1.1 发布，数据管理平台","type":6,"viewCount":78},{"author":"王练","body":"logback 是由 log4j 创始人设计的又一个开源日志组件。 logback 当前分成三个模块：logback-core、logback- classic 和 logback-access。logback-c...","commentCount":0,"href":"https://www.oschina.net/news/83469/logback-1-2-3","id":83469,"pubDate":"2017-04-02 05:12:28","recommend":false,"title":"logback 1.2.3 发布，Java 日志框架","type":6,"viewCount":86},{"author":"王练","body":"Next.js 是一个用于在服务端渲染 React 应用程序的简单框架。 Next.js 2.0.1 更新内容： 删除 master vs README 告警: 296478b 删除额外的分号....","commentCount":0,"href":"https://www.oschina.net/news/83468/nextjs-2-0-1","id":83468,"pubDate":"2017-04-02 05:11:38","recommend":false,"title":"Next.js 2.0.1 发布，React 应用的后端渲染框架","type":6,"viewCount":69},{"author":"王练","body":"QUnit 是一个功能强大、易于使用的 JavaScript 单元测试框架。它被 jQuery 项目用于测试代码和插件，它能够测试任何通用的 JavaScript 代码（甚至能...","commentCount":0,"href":"https://www.oschina.net/news/83467/qunit-2-3-0","id":83467,"pubDate":"2017-04-02 05:09:56","recommend":false,"title":"QUnit 2.3.0 发布，易用的 JavaScript 单元测试框架","type":6,"viewCount":52},{"author":"王练","body":"OnsenUI 是一个跨平台的 HTML5 移动应用框架，可帮助使用 Javascript 为 Android 和 iOS 创建漂亮的混合和移动 Web 应用。 OnsenUI 2.21 更新内容如...","commentCount":0,"href":"https://www.oschina.net/news/83466/onsenui-2-21","id":83466,"pubDate":"2017-04-02 05:09:00","recommend":false,"title":"OnsenUI 2.21 发布，跨平台 HTML5 移动应用框架","type":6,"viewCount":104},{"author":"wstmall","body":"愚人节里不愚人 ，wstmart新一轮的更新又来了，这次给大家带来了全民砍价，还有店铺DIY的功能，是不是很给力咧。。。 本次版本要新增以下功能： 1....","commentCount":3,"href":"https://www.oschina.net/news/83465/wstmart-update-4-1","id":83465,"pubDate":"2017-04-01 17:16:31","recommend":false,"title":"愚人节里不愚人 wstmart 多用户商城发布全民砍价","type":6,"viewCount":118}],"nextPageToken":"DBA816934CD0AA59","prevPageToken":"0997C855C600E421","requestCount":20,"responseCount":20,"totalResults":76747}
     * time : 2017-04-02 09:52:57
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
         * items : [{"author":"局长","body":"软件周刊（03.26 \u2014 04.01）：本周热门软件更新 \u2014 IntelliJ IDEA 2017.1.1 EAP 发布；DragonFly BSD 4.8.0 发布，提高了内核性能；renren-securi...","commentCount":0,"href":"https://my.oschina.net/editorial-story/blog/872463","id":872463,"pubDate":"2017-04-02 08:13:40","recommend":true,"title":"软件周刊 | Chrome 58 测试版支持 PWA 沉浸式全屏体验","type":3,"viewCount":193},{"author":"oschina","body":"百合词典，使用 C++ 开发，图形界面框架库采用 Qt5 QML，无其他任何依赖库。","commentCount":0,"href":"https://git.oschina.net/lieefu/lilydict","id":83483,"pubDate":"2017-04-02 07:48:52","recommend":true,"title":"码云推荐 | 开源跨平台桌面版词典软件","type":0,"viewCount":3},{"author":"oschina","body":"当编写网络爬虫或下载器时，在 Java 中实现 URL 编码和解码是一个很常见的要求。本文的重点是创建用于对所传递的 URL 进行编码和解码的模块。...","commentCount":0,"href":"https://www.oschina.net/translate/url-encoding-and-decoding-using-java","id":10003794,"pubDate":"2017-04-02 07:47:49","recommend":true,"title":"协作翻译 | Java 实现的 URL 编码和解码技术","type":4,"viewCount":286},{"author":"oschina","body":"本文从Python模块的制作、导入、查看和使用入手，重点介绍在Windows环境下如何处理和使用Python模块。最后一部分介绍一些Python常用的模块供读者参...","commentCount":0,"href":"https://my.oschina.net/SamYjy/blog/872413","id":872413,"pubDate":"2017-04-02 07:45:17","recommend":true,"title":"每日一博 | Python 进阶篇学习笔记二：Python 模块","type":3,"viewCount":195},{"author":"oschina","body":"有以下特性：向APNS（Apple Push Notification Service）发送推送通知非常容易（完全不需要配置）；支持错误响应代码；自动检测开发/生产环境；支持...","commentCount":0,"href":"https://www.oschina.net/p/knuff","id":45002,"pubDate":"2017-04-02 07:42:21","recommend":true,"title":"Knuff \u2014\u2014 苹果信息推送服务的调试应用","type":1,"viewCount":38},{"author":"小小编辑","body":"OSC愚人节的特别节目，网站首页增加了弹幕效果，但有人考虑过小小编辑写乱弹的感受么？满屏幕挤满了奇怪动弹，\u201c拥挤到怀孕。\u201d小小编辑划拉了一个...","commentCount":16,"href":"https://my.oschina.net/xxiaobian/blog/872530","id":872530,"pubDate":"2017-04-02 07:37:19","recommend":false,"title":"OSChina 周日乱弹 \u2014\u2014 大白，我对这个世界好失望","type":3,"viewCount":240},{"author":"局长","body":"微软一直在通过各种手段来敦促用户升级至 Windows 10 系统，但实际上用户并不买账。 根据 StatCounter 三月份最新统计数据表明，Windows 7 仍然是全...","commentCount":7,"href":"https://www.oschina.net/news/83478/nearly-half-of-people-still-use-windows7","id":83478,"pubDate":"2017-04-02 07:22:54","recommend":false,"title":"数据显示，近半数人依然坚持在用 Windows 7","type":6,"viewCount":142},{"author":"局长","body":"NetMarketShare 之前关于台式机浏览器市场份额的报告表示，Google Chrome 市场份额正在快速上升，而 Edge 浏览器市场份额正在以蜗牛的速度前进。而...","commentCount":0,"href":"https://www.oschina.net/news/83477/desktop-browsers-market-share-unchanged-this-month","id":83477,"pubDate":"2017-04-02 07:20:22","recommend":false,"title":"NetMarketShare:本月桌面浏览器市场份额几乎没有变化","type":6,"viewCount":63},{"author":"局长","body":"此前，谷歌方面宣布因为在最终测试中发现了 bug，导致外界期待已久的 Android Wear 2.0 系统更新推送不得不进行延期，但第一批推送的三款智能手表 ...","commentCount":0,"href":"https://www.oschina.net/news/83476/android-wear-2-0-update-continue-to-push-today","id":83476,"pubDate":"2017-04-02 07:17:39","recommend":false,"title":"Bug 已被消灭：Android Wear 2.0 更新推送继续","type":6,"viewCount":68},{"author":"王练","body":"Kodi v17.0 Candidate3 发布了，该版本完成的修复如下： 更新 Estuary 并修正错误 恢复某些文件导致视频播放问题的更改 修复鼠标无法到达 OSX 屏幕...","commentCount":0,"href":"https://www.oschina.net/news/83475/kodi-17-0-candidate3","id":83475,"pubDate":"2017-04-02 05:36:04","recommend":false,"title":"Kodi v17.0 Candidate3 发布，v18 命名 \u201cLeia\u201d ","type":6,"viewCount":84},{"author":"王练","body":"Muse-UI 是基于 Vue2.0 的 Material Design UI 框架。 Muse-UI 2.0.1 更新内容如下： Bugs 修复在app-bar中为flat-button增加href属性后按钮会发生...","commentCount":1,"href":"https://www.oschina.net/news/83474/muse-ui-2-0-1","id":83474,"pubDate":"2017-04-02 05:28:29","recommend":false,"title":"Muse-UI 2.0.1 发布，Material Design UI 框架","type":6,"viewCount":223},{"author":"王练","body":"Logstash 是一个开源的服务端数据处理流程，可同时从多个源中获取数据，将其转换，然后将其发送到\u201c收藏\u201d中，目前拥有超过 200 个插件。它能集中、...","commentCount":0,"href":"https://www.oschina.net/news/83473/logstash-5-3-0","id":83473,"pubDate":"2017-04-02 05:25:12","recommend":false,"title":"Logstash 5.3.0 发布，开源服务端数据处理流程","type":6,"viewCount":82},{"author":"王练","body":"Eclipse Che 是一个高性能的基于浏览器的集成开发环境，通过提供结构化的工作区、项目输入、模块化扩展插件来支持 Codenvy 的引擎。Eclipse Che 采...","commentCount":1,"href":"https://www.oschina.net/news/83472/eclipse-che-5-6-0","id":83472,"pubDate":"2017-04-02 05:24:31","recommend":false,"title":"Eclipse Che 5.6.0 发布，在线集成开发环境","type":6,"viewCount":216},{"author":"王练","body":"Material UI 是一组实现 Google Material Design 规范的 react 组件,是一个前端 js 框架，主要用在 web 领域。 Material UI 1.0.0 alpha9 的发布，...","commentCount":1,"href":"https://www.oschina.net/news/83471/material-ui-1-0-0-alpha9","id":83471,"pubDate":"2017-04-02 05:23:41","recommend":false,"title":"Material UI 1.0.0-alpha9 发布，前端 js 框架","type":6,"viewCount":114},{"author":"王练","body":"Apache Geode 1.1.1 发布了，Apache Geode 是一个数据管理平台，提供实时的、一致的、贯穿整个云架构地访问数据关键型应用。 本次更新内容如下： ...","commentCount":0,"href":"https://www.oschina.net/news/83470/apache-geode-1-1-1","id":83470,"pubDate":"2017-04-02 05:18:08","recommend":false,"title":"Apache Geode 1.1.1 发布，数据管理平台","type":6,"viewCount":78},{"author":"王练","body":"logback 是由 log4j 创始人设计的又一个开源日志组件。 logback 当前分成三个模块：logback-core、logback- classic 和 logback-access。logback-c...","commentCount":0,"href":"https://www.oschina.net/news/83469/logback-1-2-3","id":83469,"pubDate":"2017-04-02 05:12:28","recommend":false,"title":"logback 1.2.3 发布，Java 日志框架","type":6,"viewCount":86},{"author":"王练","body":"Next.js 是一个用于在服务端渲染 React 应用程序的简单框架。 Next.js 2.0.1 更新内容： 删除 master vs README 告警: 296478b 删除额外的分号....","commentCount":0,"href":"https://www.oschina.net/news/83468/nextjs-2-0-1","id":83468,"pubDate":"2017-04-02 05:11:38","recommend":false,"title":"Next.js 2.0.1 发布，React 应用的后端渲染框架","type":6,"viewCount":69},{"author":"王练","body":"QUnit 是一个功能强大、易于使用的 JavaScript 单元测试框架。它被 jQuery 项目用于测试代码和插件，它能够测试任何通用的 JavaScript 代码（甚至能...","commentCount":0,"href":"https://www.oschina.net/news/83467/qunit-2-3-0","id":83467,"pubDate":"2017-04-02 05:09:56","recommend":false,"title":"QUnit 2.3.0 发布，易用的 JavaScript 单元测试框架","type":6,"viewCount":52},{"author":"王练","body":"OnsenUI 是一个跨平台的 HTML5 移动应用框架，可帮助使用 Javascript 为 Android 和 iOS 创建漂亮的混合和移动 Web 应用。 OnsenUI 2.21 更新内容如...","commentCount":0,"href":"https://www.oschina.net/news/83466/onsenui-2-21","id":83466,"pubDate":"2017-04-02 05:09:00","recommend":false,"title":"OnsenUI 2.21 发布，跨平台 HTML5 移动应用框架","type":6,"viewCount":104},{"author":"wstmall","body":"愚人节里不愚人 ，wstmart新一轮的更新又来了，这次给大家带来了全民砍价，还有店铺DIY的功能，是不是很给力咧。。。 本次版本要新增以下功能： 1....","commentCount":3,"href":"https://www.oschina.net/news/83465/wstmart-update-4-1","id":83465,"pubDate":"2017-04-01 17:16:31","recommend":false,"title":"愚人节里不愚人 wstmart 多用户商城发布全民砍价","type":6,"viewCount":118}]
         * nextPageToken : DBA816934CD0AA59
         * prevPageToken : 0997C855C600E421
         * requestCount : 20
         * responseCount : 20
         * totalResults : 76747
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

        public static class ItemsBean  {
            /**
             * author : 局长
             * body : 软件周刊（03.26 — 04.01）：本周热门软件更新 — IntelliJ IDEA 2017.1.1 EAP 发布；DragonFly BSD 4.8.0 发布，提高了内核性能；renren-securi...
             * commentCount : 0
             * href : https://my.oschina.net/editorial-story/blog/872463
             * id : 872463
             * pubDate : 2017-04-02 08:13:40
             * recommend : true
             * title : 软件周刊 | Chrome 58 测试版支持 PWA 沉浸式全屏体验
             * type : 3
             * viewCount : 193
             */

            private String author;
            private String  body;
            private int     commentCount;
            private String  href;
            private int     id;
            private String  pubDate;
            private boolean recommend;
            private String  title;
            private int     type;
            private int     viewCount;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
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

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public boolean isRecommend() {
                return recommend;
            }

            public void setRecommend(boolean recommend) {
                this.recommend = recommend;
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
        }
    }
}
