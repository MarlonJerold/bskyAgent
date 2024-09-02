package org.bluesky.model;

import java.util.List;

public class Profile {

    private String did;
    private String handle;
    private String displayName;
    private String avatar;
    private String banner;
    private String description;
    private int followersCount;
    private int followsCount;
    private int postsCount;
    private String createdAt;
    private String indexedAt;
    private Associated associated;
    private List<String> labels;

    public Associated getAssociated() {
        return associated;
    }

    public void setAssociated(Associated associated) {
        this.associated = associated;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(int followsCount) {
        this.followsCount = followsCount;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getIndexedAt() {
        return indexedAt;
    }

    public void setIndexedAt(String indexedAt) {
        this.indexedAt = indexedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public static class Associated {

        private int lists;
        private int feedgens;
        private int starterPacks;
        private boolean labeler;
        private Chat chat;

        public Chat getChat() {
            return chat;
        }

        public void setChat(Chat chat) {
            this.chat = chat;
        }

        public int getFeedgens() {
            return feedgens;
        }

        public void setFeedgens(int feedgens) {
            this.feedgens = feedgens;
        }

        public boolean isLabeler() {
            return labeler;
        }

        public void setLabeler(boolean labeler) {
            this.labeler = labeler;
        }

        public int getLists() {
            return lists;
        }

        public void setLists(int lists) {
            this.lists = lists;
        }

        public int getStarterPacks() {
            return starterPacks;
        }

        public void setStarterPacks(int starterPacks) {
            this.starterPacks = starterPacks;
        }


        public static class Chat {
            private String allowIncoming;

            public String getAllowIncoming() {
                return allowIncoming;
            }

            public void setAllowIncoming(String allowIncoming) {
                this.allowIncoming = allowIncoming;
            }
        }
    }
}
