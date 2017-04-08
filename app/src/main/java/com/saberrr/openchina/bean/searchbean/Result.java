package com.saberrr.openchina.bean.searchbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/7.
 */

/**
 * <objid>83636</objid>
 * <type>news</type>
 * <title>
 * <![CDATA[ 深度操作系统 15.4 RC2 发布 ]]>
 * </title>
 * <description>
 * <![CDATA[ 深度操作系统是一个致力于为全球用户提供美观易用、安全可靠的Linux发行... ]]>
 * </description>
 * <url>
 * <![CDATA[
 * https://www.oschina.net/news/83636/deepin-15-4-rc2-released
 * ]]>
 * </url>
 * <pubDate>2017-04-07 18:42:30</pubDate>
 * <author>
 * <![CDATA[ 局长 ]]>
 * </author>
 */
@XStreamAlias("result")
public class Result {
    @XStreamAlias("type")
    private String type;
    @XStreamAlias("title")
    private String title;
    @XStreamAlias("description")
    private String description;
    @XStreamAlias("url")
    private String url;

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XStreamAlias("pubDate")

    private String pubDate;
    @XStreamAlias("author")
    private String author;
}
