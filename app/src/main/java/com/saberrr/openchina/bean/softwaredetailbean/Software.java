package com.saberrr.openchina.bean.softwaredetailbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liuqi on 2017/4/5.
 */
@XStreamAlias("software")
public class Software {
    @XStreamAlias("id")
    private String id;
    @XStreamAlias("title")
    private String title;
    @XStreamAlias("url")
    private String url;
    @XStreamAlias("extensionTitle")
    private String mExtensionTitle;
    @XStreamAlias("authorid")
    private String authorid;
    @XStreamAlias("Li_Mr")
    private String name;
    @XStreamAlias("recommended")
    private int recommended;
    @XStreamAlias("license")
    private String license;
    @XStreamAlias("body")
    private String mBody;
    @XStreamAlias("homepage")
    private String homepage;
    @XStreamAlias("document")
    private String document;
    @XStreamAlias("download")
    private String download;
    @XStreamAlias("logo")
    private String logo;
    @XStreamAlias("language")
    private String language;
    @XStreamAlias("os")
    private String os;
    @XStreamAlias("recordtime")
    private String recordtime;
    @XStreamAlias("favorite")
    private int favorite;
    @XStreamAlias("tweetCount")
    private int tweetCount;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtensionTitle() {
        return mExtensionTitle;
    }

    public void setExtensionTitle(String extensionTitle) {
        mExtensionTitle = extensionTitle;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }
}
