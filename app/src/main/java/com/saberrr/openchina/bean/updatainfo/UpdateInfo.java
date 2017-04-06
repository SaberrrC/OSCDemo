package com.saberrr.openchina.bean.updatainfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Saberrr on 2017-04-06.
 */
@XStreamAlias("oschina")
public class UpdateInfo {
    /**
     * <oschina>
     * <update>
     * <wp7>1.4</wp7>
     * <ios>v3.7.0 (1606131024)</ios>
     * <android>
     * <versionCode>283</versionCode>
     * <versionName>v2.8.3(1703281011)</versionName>
     * <downloadUrl>
     * https://www.oschina.net/uploads/osc-android-v2.8.3-release.apk
     * </downloadUrl>
     * <updateLog>
     * <![CDATA[
     * v2.8.3 新版提示 <br/>1、新增「官网人员」标识，再也不会被假冒红薯绿薯紫薯什么的欺 骗了~ 2、修复上版本中重复点击刷新数据的问题~ <br><br>大小：14.1M
     * ]]>
     * </updateLog>
     * <coverUpdate>false</coverUpdate>
     * <coverStartDate>2014-03-05</coverStartDate>
     * <coverEndDate>2014-03-05</coverEndDate>
     * <coverURL/>
     * </android>
     * </update>
     * </oschina>
     * <!--
     * Generated by OsChina.NET (init:0[ms],page:1[ms],ip:101.81.195.207)
     * -->
     */
    @XStreamAlias("update")
    private Update mUpdate;

    public Update getUpdate() {
        return mUpdate;
    }

    public void setUpdate(Update update) {
        mUpdate = update;
    }

    @XStreamAlias("update")
    public class Update {

        @XStreamAlias("wp7")
        private String wp7;
        @XStreamAlias("ios")
        private String ios;
        @XStreamAlias("android")
        private AAndroid mAAndroid;

        public String getWp7() {
            return wp7;
        }

        public void setWp7(String wp7) {
            this.wp7 = wp7;
        }

        public String getIos() {
            return ios;
        }

        public void setIos(String ios) {
            this.ios = ios;
        }

        public AAndroid getAAndroid() {
            return mAAndroid;
        }

        public void setAAndroid(AAndroid AAndroid) {
            mAAndroid = AAndroid;
        }

        @XStreamAlias("android")
        public class AAndroid {
            @XStreamAlias("versionCode")
            private String versionCode;
            @XStreamAlias("versionName")
            private String versionName;
            @XStreamAlias("downloadUrl")
            private String downloadUrl;
            @XStreamAlias("updateLog")
            private String updateLog;
            @XStreamAlias("coverUpdate")
            private String coverUpdate;
            @XStreamAlias("coverStartDate")
            private String coverStartDate;
            @XStreamAlias("coverEndDate")
            private String coverEndDate;

            public String getVersionCode() {
                return versionCode;
            }

            public void setVersionCode(String versionCode) {
                this.versionCode = versionCode;
            }

            public String getVersionName() {
                return versionName;
            }

            public void setVersionName(String versionName) {
                this.versionName = versionName;
            }

            public String getDownloadUrl() {
                return downloadUrl;
            }

            public void setDownloadUrl(String downloadUrl) {
                this.downloadUrl = downloadUrl;
            }

            public String getUpdateLog() {
                return updateLog;
            }

            public void setUpdateLog(String updateLog) {
                this.updateLog = updateLog;
            }

            public String getCoverUpdate() {
                return coverUpdate;
            }

            public void setCoverUpdate(String coverUpdate) {
                this.coverUpdate = coverUpdate;
            }

            public String getCoverStartDate() {
                return coverStartDate;
            }

            public void setCoverStartDate(String coverStartDate) {
                this.coverStartDate = coverStartDate;
            }

            public String getCoverEndDate() {
                return coverEndDate;
            }

            public void setCoverEndDate(String coverEndDate) {
                this.coverEndDate = coverEndDate;
            }
        }

        @Override
        public String toString() {
            return "Update{" +
                    "wp7='" + wp7 + '\'' +
                    ", ios='" + ios + '\'' +
                    ", mAAndroid=" + mAAndroid +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "mUpdate=" + mUpdate +
                '}';
    }
}
