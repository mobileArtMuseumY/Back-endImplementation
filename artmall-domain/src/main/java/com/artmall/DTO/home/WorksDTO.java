package com.artmall.DTO.home;

/** 发现作品的主页
 * @author mllove
 * @create 2018-09-27 20:37
 **/

public class WorksDTO {
    private Long id;
    private String workName;
    //收藏这个作品的人数
    private Long favoriteCount;
    private String attachmentShowName;

    private String attachmentShowPath;

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentShowName() {
        return attachmentShowName;
    }

    public void setAttachmentShowName(String attachmentShowName) {
        this.attachmentShowName = attachmentShowName;
    }

    public String getAttachmentShowPath() {
        return attachmentShowPath;
    }

    public void setAttachmentShowPath(String attachmentShowPath) {
        this.attachmentShowPath = attachmentShowPath;
    }

    @Override
    public String toString() {
        return "WorksVO{" +
                "id=" + id +
                ", attachmentShowName='" + attachmentShowName + '\'' +
                ", attachmentShowPath='" + attachmentShowPath + '\'' +
                '}';
    }
}
