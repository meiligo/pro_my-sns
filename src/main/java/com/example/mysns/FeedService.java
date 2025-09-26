package com.example.mysns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    private FeedDAO feedDAO;

    public void createFeed(FeedVO feed, MultipartFile[] images) throws IOException {
        // 게시물 등록
        feedDAO.insertFeed(feed);

        // 게시물 이미지 등록
        for (MultipartFile img : images) {
            FeedImgVO feedImg = new FeedImgVO();
            feedImg.setFeedNo(feed.getNo());
            feedImg.setImage(img.getBytes());

            feedDAO.insertFeedImg(feedImg);
        }
    }

    public List<FeedVO> getAllFeeds() {
        return feedDAO.selectAllFeed();
    }

    public FeedImgVO getFeedImg0(int no) {
        return feedDAO.selectFeedImg(no);
    }

    // DB 에서 이미지 파일 select를 위해 직접 구현
    @Autowired
    DataSource dataSource;
    public FeedImgVO getFeedImg(int no) throws SQLException {
        String sql = "SELECT no, feed_no, image FROM feed_img WHERE no = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, no);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FeedImgVO vo = new FeedImgVO();
                vo.setNo(rs.getInt("no"));
                vo.setFeedNo(rs.getInt("feed_no"));
                vo.setImage(rs.getBytes("image")); // getBytes() 사용
                return vo;
            }
        }
        return null;
    }

}
