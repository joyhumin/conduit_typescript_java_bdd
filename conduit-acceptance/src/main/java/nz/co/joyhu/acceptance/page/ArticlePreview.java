package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.Tag;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class ArticlePreview {
    private List<Tag> tagList;
    private OffsetDateTime createDate;
    private int favoritedCount;
    private String preview;
    private String author;
    private String title;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    public int getFavoritedCount() {
        return favoritedCount;
    }

    public void setFavoritedCount(int favoritedCount) {
        this.favoritedCount = favoritedCount;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        ArticlePreview that = (ArticlePreview) o;
        return favoritedCount == that.favoritedCount && Objects.equals(tagList, that.tagList) && Objects.equals(createDate, that.createDate) && Objects.equals(preview, that.preview) && Objects.equals(author, that.author) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagList, createDate, favoritedCount, preview, author, title);
    }

    @Override
    public String toString() {
        return "ArticlePreview{" +
            "tagList=" + tagList +
            ", createDate=" + createDate +
            ", favoritedCount=" + favoritedCount +
            ", preview='" + preview + '\'' +
            ", author='" + author + '\'' +
            ", title='" + title + '\'' +
            '}';
    }
}