package com.artmall.DTO.home;

/**
 * 水印图片
 *
 * @author mllove
 * @create 2018-09-27 20:57
 **/

public class WaterPictureDTO {
    private Long id;
    private String attachmentName;
    private String attachmentWatermarkPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentWatermarkPath() {
        return attachmentWatermarkPath;
    }

    public void setAttachmentWatermarkPath(String attachmentWatermarkPath) {
        this.attachmentWatermarkPath = attachmentWatermarkPath;
    }
}
