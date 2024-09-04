package org.bluesky.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadViewPost {

    @JsonProperty("thread")
    private Thread thread;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "ThreadViewPost{" +
                "thread=" + thread +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thread {

        @JsonProperty("post")
        private Post post;

        @JsonProperty("replies")
        private List<ThreadViewPost> replies;

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }

        public List<ThreadViewPost> getReplies() {
            return replies;
        }

        public void setReplies(List<ThreadViewPost> replies) {
            this.replies = replies;
        }

        @Override
        public String toString() {
            return "Thread{" +
                    "post=" + post +
                    ", replies=" + replies +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Post {

        @JsonProperty("uri")
        private String uri;

        @JsonProperty("cid")
        private String cid;

        @JsonProperty("author")
        private Author author;

        @JsonProperty("record")
        private Record record;

        @JsonProperty("embed")
        private Embed embed;

        @JsonProperty("replyCount")
        private int replyCount;

        @JsonProperty("repostCount")
        private int repostCount;

        @JsonProperty("likeCount")
        private int likeCount;

        @JsonProperty("quoteCount")
        private int quoteCount;

        @JsonProperty("indexedAt")
        private String indexedAt;

        @JsonProperty("labels")
        private List<Object> labels; // Adicionada para corresponder ao JSON

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Record getRecord() {
            return record;
        }

        public void setRecord(Record record) {
            this.record = record;
        }

        public Embed getEmbed() {
            return embed;
        }

        public void setEmbed(Embed embed) {
            this.embed = embed;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getRepostCount() {
            return repostCount;
        }

        public void setRepostCount(int repostCount) {
            this.repostCount = repostCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getQuoteCount() {
            return quoteCount;
        }

        public void setQuoteCount(int quoteCount) {
            this.quoteCount = quoteCount;
        }

        public String getIndexedAt() {
            return indexedAt;
        }

        public void setIndexedAt(String indexedAt) {
            this.indexedAt = indexedAt;
        }

        public List<Object> getLabels() {
            return labels;
        }

        public void setLabels(List<Object> labels) {
            this.labels = labels;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "uri='" + uri + '\'' +
                    ", cid='" + cid + '\'' +
                    ", author=" + author +
                    ", record=" + record +
                    ", embed=" + embed +
                    ", replyCount=" + replyCount +
                    ", repostCount=" + repostCount +
                    ", likeCount=" + likeCount +
                    ", quoteCount=" + quoteCount +
                    ", indexedAt='" + indexedAt + '\'' +
                    ", labels=" + labels +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Author {

        @JsonProperty("did")
        private String did;

        @JsonProperty("handle")
        private String handle;

        @JsonProperty("displayName")
        private String displayName;

        @JsonProperty("avatar")
        private String avatar;

        @JsonProperty("associated")
        private Associated associated;

        @JsonProperty("labels")
        private List<Object> labels; // Adicionada para corresponder ao JSON

        @JsonProperty("createdAt")
        private String createdAt;

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Associated getAssociated() {
            return associated;
        }

        public void setAssociated(Associated associated) {
            this.associated = associated;
        }

        public List<Object> getLabels() {
            return labels;
        }

        public void setLabels(List<Object> labels) {
            this.labels = labels;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "did='" + did + '\'' +
                    ", handle='" + handle + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", associated=" + associated +
                    ", labels=" + labels +
                    ", createdAt='" + createdAt + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Associated {

        @JsonProperty("chat")
        private Chat chat;

        public Chat getChat() {
            return chat;
        }

        public void setChat(Chat chat) {
            this.chat = chat;
        }

        @Override
        public String toString() {
            return "Associated{" +
                    "chat=" + chat +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Chat {

        @JsonProperty("allowIncoming")
        private String allowIncoming;

        public String getAllowIncoming() {
            return allowIncoming;
        }

        public void setAllowIncoming(String allowIncoming) {
            this.allowIncoming = allowIncoming;
        }

        @Override
        public String toString() {
            return "Chat{" +
                    "allowIncoming='" + allowIncoming + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Record {

        @JsonProperty("$type")
        private String type;

        @JsonProperty("createdAt")
        private String createdAt;

        @JsonProperty("embed")
        private Embed embed;

        @JsonProperty("langs")
        private List<String> langs;

        @JsonProperty("text")
        private String text;

        @JsonProperty("reply")
        private Reply reply;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Embed getEmbed() {
            return embed;
        }

        public void setEmbed(Embed embed) {
            this.embed = embed;
        }

        public List<String> getLangs() {
            return langs;
        }

        public void setLangs(List<String> langs) {
            this.langs = langs;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Reply getReply() {
            return reply;
        }

        public void setReply(Reply reply) {
            this.reply = reply;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "type='" + type + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", embed=" + embed +
                    ", langs=" + langs +
                    ", text='" + text + '\'' +
                    ", reply=" + reply +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Reply {

        @JsonProperty("parent")
        private PostReference parent;

        @JsonProperty("root")
        private PostReference root;

        public PostReference getParent() {
            return parent;
        }

        public void setParent(PostReference parent) {
            this.parent = parent;
        }

        public PostReference getRoot() {
            return root;
        }

        public void setRoot(PostReference root) {
            this.root = root;
        }

        @Override
        public String toString() {
            return "Reply{" +
                    "parent=" + parent +
                    ", root=" + root +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PostReference {

        @JsonProperty("uri")
        private String uri;

        @JsonProperty("cid")
        private String cid;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        @Override
        public String toString() {
            return "PostReference{" +
                    "uri='" + uri + '\'' +
                    ", cid='" + cid + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embed {

        @JsonProperty("image")
        private Image image;

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "Embed{" +
                    "image=" + image +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image {

        @JsonProperty("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Image{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }
}
