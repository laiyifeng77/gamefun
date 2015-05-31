package com.pizidea.framework.services;

import com.pizidea.framework.beans.UserBean;

import retrofit.http.POST;
import retrofit.http.Path;

/**
 * 评论接口
 * Created by yflai on 2015/5/31.
 */
public interface CommentService {

    @POST("/user/{uid}")
    UserBean getUserInfo(@Path("uid") int userId);

    @POST("/user/{uid}")
    boolean modifyUserInfo(@Path("uid") int userId);


}
