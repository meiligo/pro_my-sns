package com.example.mysns;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MysnsApplicationTests {

	@Autowired
	private FeedDAO feedDAO;

	@Test
	void contextLoads() {
	}

	FeedVO insertSample() {
		FeedVO feed = new FeedVO();
		feed.setContent("test");
		feed.setUserId("testUser");
		feed.setCreatedAt(LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		feedDAO.insertFeed(feed);

		return feed;
	}

	@Test
	void FeedInsertTest() {
		FeedVO feed = insertSample();
		assertNotNull(feed.getNo(), "Insert 후 FeedNo가 Null이면 안됨");
	}

	@Test
	void selectAllFeedTest() {
		FeedInsertTest();

		List<FeedVO> feeds = feedDAO.selectAllFeed();

		assertNotNull(feeds);
		assertFalse(feeds.isEmpty());
	}

	@Test
	void selectFeedTest() {
		FeedVO feed = insertSample();

		FeedVO feed2 = feedDAO.selectFeed(feed.getNo());
		assertEquals(feed.getNo(), feed2.getNo());
	}


	@Test
	void deleteFeedTest(){
		FeedVO feed = insertSample();

		feedDAO.deleteFeed(feed.getNo());

		FeedVO feed2 = feedDAO.selectFeed(feed.getNo());
		assertNull(feed2, "삭제 후 NULL이어야 합니다");
	}

}
