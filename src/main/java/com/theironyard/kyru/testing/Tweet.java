package com.theironyard.kyru.testing;

public class Tweet {
    private String userId;
    private String userKey;
    private String createdAt;
    private String createdStr;
    private String retweetCount;
    private String retweeted;
    private String favoriteCount;
    private String text;
    private String tweetId;
    private String source;
    private String hashtags;
    private String expandedUrls;
    private String posted;
    private String mentions;
    private String retweetedStatusId;
    private String inReplyToStatusId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedStr() {
        return createdStr;
    }

    public void setCreatedStr(String createdStr) {
        this.createdStr = createdStr;
    }

    public String getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(String retweetCount) {
        this.retweetCount = retweetCount;
    }

    public String getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(String retweeted) {
        this.retweeted = retweeted;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public String getExpandedUrls() {
        return expandedUrls;
    }

    public void setExpandedUrls(String expandedUrls) {
        this.expandedUrls = expandedUrls;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getMentions() {
        return mentions;
    }

    public void setMentions(String mentions) {
        this.mentions = mentions;
    }

    public String getRetweetedStatusId() {
        return retweetedStatusId;
    }

    public void setRetweetedStatusId(String retweetedStatusId) {
        this.retweetedStatusId = retweetedStatusId;
    }

    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(String inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }
}
