package com.gdufe.cs.works.utils;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/25 10:35
 **/

public class RedisKeyUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //保存评论被点赞数量的key
    public static final String MAP_KEY_COMMENT_LIKED_COUNT = "MAP_COMMENT_LIKED_COUNT";

    /**
     * 拼接 被点赞的评论id 和 点赞的人的id 作为key。格式 222222::333333
     * @param likedCommentId 被点赞的评论id
     * @param likedPostId 点赞的人的id
     * @return
     */
    public static String getLikedKey(Long likedCommentId, Long likedPostId){
        StringBuilder builder = new StringBuilder();
        builder.append(likedCommentId);
        builder.append("::");
        builder.append(likedPostId);
        return builder.toString();
    }
}