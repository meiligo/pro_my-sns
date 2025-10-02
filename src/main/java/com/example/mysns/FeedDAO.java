package com.example.mysns;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedDAO {
    void insertFeed(FeedVO feed);

    void insertFeedImg(FeedImgVO feedImg);

    List<FeedVO> selectAllFeed();

    FeedVO selectFeed(@Param("no") int no);

    void deleteFeed(@Param("no") int no);

    List<Integer> selectFeedImgNos(@Param("no") int feedNo);
}
