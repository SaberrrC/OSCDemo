package com.saberrr.openchina.bean.rockbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/6.
 */

@XStreamAlias("oschina")
public class RockBean {
    @XStreamAlias("randomtype")
    private int randomtype;
    @XStreamAlias("id")
    private String id;
    @XStreamAlias("title")
    private String title;
    @XStreamAlias("detail")
    private String detail;
    @XStreamAlias("author")
    private String author;
    @XStreamAlias("authorid")
    private String authorid;
    @XStreamAlias("image")
    private String image;
    @XStreamAlias("pubDate")
    private String pubDate;
    @XStreamAlias("commentCount")
    private int commentCount;
    @XStreamAlias("url")
    private String url;

    public int getRandomtype() {
        return randomtype;
    }

    public void setRandomtype(int randomtype) {
        this.randomtype = randomtype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
