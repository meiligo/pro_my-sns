package com.example.mysns;

import lombok.Data;

import java.util.List;

@Data
public class FeedVO {
    private int no;
    private String content;
    private String userId;
    private String createdAt;

    private List<Integer> feedImgNos;
}
