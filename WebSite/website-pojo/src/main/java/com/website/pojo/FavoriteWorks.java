package beans;

import java.util.Date;

public class FavoriteWorks {
    private Long worksId;

    private Long userId;

    private Long useUserId;

    private Long useUserId2;

    private Byte isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getWorksId() {
        return worksId;
    }

    public void setWorksId(Long worksId) {
        this.worksId = worksId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUseUserId() {
        return useUserId;
    }

    public void setUseUserId(Long useUserId) {
        this.useUserId = useUserId;
    }

    public Long getUseUserId2() {
        return useUserId2;
    }

    public void setUseUserId2(Long useUserId2) {
        this.useUserId2 = useUserId2;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}