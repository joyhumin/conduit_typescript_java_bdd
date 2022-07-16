package nz.co.joyhu.acceptance.domain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class Article {

    private String author;
    private OffsetDateTime createDate;
    private Integer favoritedCount;
    private String content;
    private List<Tag> tagList;
    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getFavoritedCount() {
        return favoritedCount;
    }

    public void setFavoritedCount(Integer favoritedCount) {
        this.favoritedCount = favoritedCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(author, article.author) && Objects.equals(createDate, article.createDate) && Objects.equals(favoritedCount, article.favoritedCount) && Objects.equals(content, article.content) && Objects.equals(tagList, article.tagList) && Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, createDate, favoritedCount, content, tagList, title);
    }

    @Override
    public String toString() {
        return "Article{" +
            "author='" + author + '\'' +
            ", createDate=" + createDate +
            ", favoritedCount=" + favoritedCount +
            ", content='" + content + '\'' +
            ", tagList=" + tagList +
            ", title='" + title + '\'' +
            '}';
    }
}
