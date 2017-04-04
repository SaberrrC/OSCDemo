/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.saberrr.openchina.faces;

/**
 * @author kymjs (http://www.kymjs.com)
 */
public class FaceBean {
    public int    resId; // 图片资源地址
    public int    value; // 一个emoji对应唯一一个value
    public String emojiStr; // emoji在互联网传递的字符串
    public String remote;

    public FaceBean(int id, int value, String name, String remote) {
        this.resId = id;
        this.value = value;
        this.emojiStr = name;
        this.remote = remote;
    }

}
