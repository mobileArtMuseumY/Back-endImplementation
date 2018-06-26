package com.website.service.inter;
import com.website.pojo.*;
import com.website.vo.ProjectVo;
import com.website.common.ServerResponse;

public interface ProjectServiceIn {
    public ServerResponse<String> addProject(ProjectVo project);


}
