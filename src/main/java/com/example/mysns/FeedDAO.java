package com.example.mysns;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FeedDAO {
    void insertFeed(FeedVO feed);

    void insertFeedImg(FeedImgVO feedImg);

    @Select("SELECT no, feed_no, image FROM feed_img WHERE no = #{no}")
    @Results({
            @Result(property = "no", column = "no"),
            @Result(property = "feedNo", column = "feed_no"),
            @Result(property = "image", column = "image", javaType = byte[].class, jdbcType = JdbcType.BLOB)
    })
    FeedImgVO selectFeedImg(@Param("no") int no);

    List<FeedVO> selectAllFeed();

    FeedVO selectFeed(@Param("no") int no);

    void deleteFeed(@Param("no") int no);
}
