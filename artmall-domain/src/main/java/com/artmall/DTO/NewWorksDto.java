package com.artmall.DTO;

import com.artmall.DO.WorksAttachment;

import java.util.List;

/**
 * 上传作品的表单
 *
 * @author mllove
 * @create 2018-09-22 17:07
 **/

public class NewWorksDto {


    private Long id;

    private String worksName;

    private String worksDescribe;

    private Long price;


    private List<WorksAttachment> worksAttachmentList ;

    public List<WorksAttachment> getWorksAttachmentList() {
        return worksAttachmentList;
    }

    public void setWorksAttachmentList(List<WorksAttachment> worksAttachmentList) {
        this.worksAttachmentList = worksAttachmentList;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getWorksDescribe() {
        return worksDescribe;
    }

    public void setWorksDescribe(String worksDescribe) {
        this.worksDescribe = worksDescribe;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
