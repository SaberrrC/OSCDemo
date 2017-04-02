package com.saberrr.openchina.bean;

/**
 * Created by liuqi on 2017/4/2.
 */

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <oschina>
 <softwarecount>43560</softwarecount>
 <softwareTypes>
 <softwareType>
 <name>
 <![CDATA[ 编程语言 ]]>
 </name>
 <tag>1</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ Web应用开发 ]]>
 </name>
 <tag>309</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 手机/移动开发 ]]>
 </name>
 <tag>331</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ iOS代码库 ]]>
 </name>
 <tag>364</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 程序开发 ]]>
 </name>
 <tag>12</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 开发工具 ]]>
 </name>
 <tag>11</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ jQuery 插件 ]]>
 </name>
 <tag>273</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 建站系统 ]]>
 </name>
 <tag>256</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 企业应用 ]]>
 </name>
 <tag>5</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 服务器软件 ]]>
 </name>
 <tag>10</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 数据库相关 ]]>
 </name>
 <tag>6</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 应用工具 ]]>
 </name>
 <tag>8</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 插件和扩展 ]]>
 </name>
 <tag>18</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 游戏/娱乐 ]]>
 </name>
 <tag>7</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 管理和监控 ]]>
 </name>
 <tag>14</tag>
 </softwareType>
 <softwareType>
 <name>
 <![CDATA[ 其他开源 ]]>
 </name>
 <tag>9</tag>
 </softwareType>
 </softwareTypes>
 </oschina>
 */
    @XStreamAlias("oschina")
public class CategoryBean {
    @XStreamAlias("softwarecount")
    public String mSoftwarecount;
    @XStreamAlias("softwareTypes")
    public SoftwareTypes mSoftwareTypes;

    public String getSoftwarecount() {
        return mSoftwarecount;
    }

    public void setSoftwarecount(String softwarecount) {
        mSoftwarecount = softwarecount;
    }

    public SoftwareTypes getSoftwareTypes() {
        return mSoftwareTypes;
    }

    public void setSoftwareTypes(SoftwareTypes softwareTypes) {
        mSoftwareTypes = softwareTypes;
    }
}
