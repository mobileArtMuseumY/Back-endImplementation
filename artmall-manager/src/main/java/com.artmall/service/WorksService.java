package com.artmall.service;

import com.artmall.DO.OrderForm;
import com.artmall.DTO.NewWorksDto;
import com.artmall.DO.Student;
import com.artmall.DO.Works;
import com.artmall.DO.WorksAttachment;

import java.util.List;

public interface WorksService {

    Works addWorksInfo(NewWorksDto works);

    List<WorksAttachment> selectAttachmentByWorks(Long worksId) throws Exception;

    Works selectWorksById(Long worksId);

    Student selectStudentByWorksId(Long worksId);

    List<Works> selectWorksByStudentId(Long studentId);

    Works setWorksStatus(Works works,byte worksStatus);

    void delete(Works works);

    Works update(Works works);

    List<Works> show(int page, int rows);

    Works updateWorksInfo(NewWorksDto newWorksDto);

    void deleteWorksAllAttachemnt(Long id);

    void deleteWorksAttachemnt(WorksAttachment worksAttachment);

    WorksAttachment selectAttachmentById(Long attachmentId);

    void updateCollectCount(Works works,int i);

    void addWorksCollectCount(Long worksId);

    void deleteWorksCollectCount(Long worksId);

    NewWorksDto getInfo(Long worksId);


}
