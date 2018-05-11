package com.demo.chenke.componentthree.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenke on 2018/5/2.
 */

public class FeedArticleData implements Parcelable {
    private String apkLink;
    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private int superChapterId;
    private String superChapterName;
    private long publishTime;
    private String title;
    private int visible;
    private int zan;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.apkLink);
        dest.writeString(this.author);
        dest.writeInt(this.chapterId);
        dest.writeString(this.chapterName);
        dest.writeByte(this.collect ? (byte) 1 : (byte) 0);
        dest.writeInt(this.courseId);
        dest.writeString(this.desc);
        dest.writeString(this.envelopePic);
        dest.writeInt(this.id);
        dest.writeString(this.link);
        dest.writeString(this.niceDate);
        dest.writeString(this.origin);
        dest.writeString(this.projectLink);
        dest.writeInt(this.superChapterId);
        dest.writeString(this.superChapterName);
        dest.writeLong(this.publishTime);
        dest.writeString(this.title);
        dest.writeInt(this.visible);
        dest.writeInt(this.zan);
    }

    public FeedArticleData() {
    }

    protected FeedArticleData(Parcel in) {
        this.apkLink = in.readString();
        this.author = in.readString();
        this.chapterId = in.readInt();
        this.chapterName = in.readString();
        this.collect = in.readByte() != 0;
        this.courseId = in.readInt();
        this.desc = in.readString();
        this.envelopePic = in.readString();
        this.id = in.readInt();
        this.link = in.readString();
        this.niceDate = in.readString();
        this.origin = in.readString();
        this.projectLink = in.readString();
        this.superChapterId = in.readInt();
        this.superChapterName = in.readString();
        this.publishTime = in.readLong();
        this.title = in.readString();
        this.visible = in.readInt();
        this.zan = in.readInt();
    }

    public static final Creator<FeedArticleData> CREATOR = new Creator<FeedArticleData>() {
        @Override
        public FeedArticleData createFromParcel(Parcel source) {
            return new FeedArticleData(source);
        }

        @Override
        public FeedArticleData[] newArray(int size) {
            return new FeedArticleData[size];
        }
    };
}
