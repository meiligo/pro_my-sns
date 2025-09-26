package com.example.mysns;

import lombok.Data;

@Data
public class FeedVO {
    private int no;
    private String content;
    private String userId;
    private String createdAt;
}
