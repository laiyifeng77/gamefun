/*
 * Copyright 2014 Chris Banes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pizidea.framework.beans.view;

/**
 * 内容展示采用ListView实现，分别为item和section部分
 * @param <T>
 */
public interface ListItem<T> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_SECTION = 1;

    public int getListType();

    public T getListItem();

    public int getListSectionTitle();


}
