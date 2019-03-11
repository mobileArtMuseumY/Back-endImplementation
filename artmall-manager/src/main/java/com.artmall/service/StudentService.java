package com.artmall.service;




import com.artmall.DO.Student;
import com.artmall.DTO.studentController.ShowDTO;
import com.artmall.DTO.studentController.UpStuDTO;
import com.artmall.response.ServerResponse;
import com.artmall.DTO.home.WorksDTO;
import com.artmall.DTO.studentController.NewStudentDTO;
import com.artmall.DTO.studentHome.StudentHomeInfoDTO;

import java.util.List;

/**
 * @author
 * @create 2018-08-06 10:38
 **/

public interface StudentService {


//    List<Student> getAll();

    /**
     * id来查找学生
     * @param id
     * @return
     */
   Student selectStudentById(Long id);

    /**
     * 添加学生，同时添加至user_role
     * @param student
     * @return
     * @throws Exception
     */
    Student addUser(NewStudentDTO student) throws Exception;

    /**
     * 通过学号来查找学生
     * @param userNo
     * @return
     */
    Student selectStudentByStuId(String userNo);

    /**
     * 重置密码
     * @param email
     * @param newpassword
     */
    void resetPassword(String email,String newpassword);

    /**
     * 通过email来查找学生
     * @param email
     * @return
     */
    Student selectStudentByEmail(String email);

    /**
     * 输入email重置密码
     * @param student
     * @param newPassword
     */
    void resetPasswordByEmail(Student student, String newPassword);

    /**
     * 获取当前学生
     * @return
     */
    Student getStudent();


    StudentHomeInfoDTO showHomeInfo(Long id);

    List<WorksDTO> showWorks(Long id);

    void addTransTime(Long sellerId);

    void login(Student student);

    void delete(Student student);

    void update(UpStuDTO upStuDTO);

    List<ShowDTO> show(int page, int rows);

    List<Student> getAllStudent();

    void initPwd(Student student);

    void setStatus(Student student, byte studentNormal);

 void resetEmail(Student student, String newEmail);

    void addFollowerCount(Long userId);

 void deleteFollowerCount(Long userId);

    void breakTimeAdd(Long sellerId);

 void updatePhone(String tel);


    void updateDescription(String description);
}
