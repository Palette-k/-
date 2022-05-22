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
    public static final String MAP_KEY_LIKED_COUNT = "MAP_KEY_LIKED_COUNT";

    /**
     * 拼接 被点赞的评论id 和 点赞的人的id 以及点赞类型 作为key。格式 222222::333333:1
     * @param likedParentId 被点赞的评论id
     * @param likedPostId 点赞的人的id
     * @param type 点赞类型 点赞1 想看2 看过3
     * @return
     */
    public static String getLikedKey(Long likedParentId, Long likedPostId,Integer type){
        StringBuilder builder = new StringBuilder();
        builder.append(likedParentId);
        builder.append("::");
        builder.append(likedPostId);
        builder.append("::");
        builder.append(type);
        return builder.toString();
    }
}