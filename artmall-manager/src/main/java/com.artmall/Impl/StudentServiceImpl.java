package com.artmall.Impl;



import com.artmall.DTO.studentController.ShowDTO;
import com.artmall.DTO.studentController.UpStuDTO;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.NullException;
import com.artmall.mapper.StudentMapper;
import com.artmall.DO.Student;
import com.artmall.DO.StudentExample;
import com.artmall.DO.Works;
import com.artmall.mapper.UserMapper;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.*;
import com.artmall.utils.IDUtils;
import com.artmall.utils.JWTUtil;
import com.artmall.utils.SaltUtil;
import com.artmall.DTO.home.WorksDTO;
import com.artmall.DTO.studentController.NewStudentDTO;
import com.artmall.DTO.studentHome.StudentHomeInfoDTO;
import com.artmall.utils.Tools;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author
 * @create 2018-08-08 13:51
 **/
//@CacheConfig(cacheNames = "stu")     //抽取缓存的公共配置
@Service(value = "StudentService")
public class StudentServiceImpl implements StudentService {


    @Autowired
    Student student;
    @Autowired
    StudentMapper studentMapper;


//    @Override
//    public List<Student> getAll() {
//        return studentMapper.selectAll();
//    }

    @Override
//    @CachePut
    public Student selectStudentById(Long id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        if (student==null){
            throw new NullException("没有此学生");
        }
        return student;
    }

    @Override
    public Student addUser(NewStudentDTO student)  {
        Student newStudent = new Student();
        newStudent.setId(Tools.initId());
        newStudent.setStudentId(student.getStudentId());
        newStudent.setSalt(SaltUtil.InitSalt());
        newStudent.setSex(student.getSex());
        newStudent.setBankAccount(student.getBankAccount());
        newStudent.setTel(student.getTel());
        newStudent.setHashedPwd(new SimpleHash("MD5",getInitPassword(student.getCardId()),ByteSource.Util.bytes(newStudent.getSalt()),1024).toString());
        newStudent.setCardId(student.getCardId());
        newStudent.setGmtCreate(new Date());
        newStudent.setIsVerified(Const.STUDENT_INIT);
        newStudent.setLoginName(student.getLoginName());
        newStudent.setTransactionTime(0);
        newStudent.setBreakTime(0);
        newStudent.setFollowerCount(0);
        newStudent.setEmail(student.getEmail());
        if(studentMapper.insert(newStudent)!=1){
            throw new ArtmallException("student插入失败");
        }

        userService.addUserRole(newStudent.getId(),Const.ROLE_STUDENT);

        return newStudent;
    }

    /**
     * 初始密码为身份证后六位
     * @param id
     * @return
     */
    private String getInitPassword(String id) {
//        Pattern p = Pattern.compile("[0-9]+[X|x]{1}");
//        Matcher m = p.matcher(id);
//        boolean b = m.matches();
//        if(b){
//            id = id.substring(id.length()-7,id.length()-1);
//        }else{
//            id = id.substring(id.length()-6);
//        }

        return "000000";
    }


    @Override
    public Student selectStudentByStuId(String userNo) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(userNo);
        List<Student> list = studentMapper.selectByExample(example);
        if (list.isEmpty())
            throw new NullException("此studentId没有对应的学生");
        else
            return list.get(0);
    }


    /**
     * 登录后直接通过jwt获取用户信息，进行密码的修改
     * @param newpassword
     * @return
     */
    @Override
    public void resetPassword(String email,String newpassword) {
        Student student = getStudent();
        student.setEmail(email);
        student.setHashedPwd(new SimpleHash("MD5",newpassword,ByteSource.Util.bytes(student.getSalt()),1024).toString());
        student.setIsVerified(Const.STUDENT_NORMAL);

        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public Student selectStudentByEmail(String email) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<Student> list = studentMapper.selectByExample(example);
        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

    /**
     * 未登录的时候修改密码
     * @param student
     * @param newPassword
     * @return
     */
    @Override
    public void resetPasswordByEmail(Student student, String newPassword) {
        student.setHashedPwd(new SimpleHash("MD5",newPassword,ByteSource.Util.bytes(student.getSalt()),1024).toString());
        student.setGmtModified(new Date());
        if(studentMapper.updateByPrimaryKey(student)!=1){
            throw new ArtmallException("修改密码失败");
        }
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @Override
    public Student getStudent() {
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        Long id = JWTUtil.getUserNo(token);
        if (id==null) {
            throw new NullException("获取当前用户失败");
        }
        Student student = studentMapper.selectByPrimaryKey(id);
        if (student == null){
            throw new NullException("不存在此学生");
        }

        return student;
    }

    @Autowired
    OrderService orderService;
    @Autowired
    SkillService skillService;
    @Autowired
    FollowerSerivce followerSerivce;
    @Override
    public StudentHomeInfoDTO showHomeInfo(Long id) {
        Student student = selectStudentById(id);
        StudentHomeInfoDTO studentHomeInfoDTO = new StudentHomeInfoDTO();
        studentHomeInfoDTO.setId(student.getId());
        studentHomeInfoDTO.setAvatar(student.getAvatar());
        studentHomeInfoDTO.setStudentId(student.getStudentId());
        studentHomeInfoDTO.setLoginName(student.getLoginName());
        studentHomeInfoDTO.setFollowerCount(Math.toIntExact(followerSerivce.getFollowedCount(id)));

        studentHomeInfoDTO.setFollowingCount(Math.toIntExact(followerSerivce.getFollowingCount(id)));

        studentHomeInfoDTO.setBreakTime(student.getBreakTime());
        studentHomeInfoDTO.setOrderCount(orderService.selectOrderByStudentId(student.getId()).size());
        studentHomeInfoDTO.setIntroduction(student.getIntroduction());
        studentHomeInfoDTO.setSkillList(skillService.getSkillListByStudentId(student.getId()));

        return studentHomeInfoDTO;
    }

    @Autowired
    WorksService worksService;
    @Override
    public List<WorksDTO> showWorks(Long id) {
        Student student =selectStudentById(id);
        List<Works> worksList=worksService.selectWorksByStudentId(student.getId());
        List<WorksDTO> worksDTOList = new ArrayList<>();
        for (Works works:worksList){
            WorksDTO worksDTO = new WorksDTO();
            worksDTO.setId(works.getId());
            worksDTO.setAttachmentShowName(works.getAttachmentShowName());
            worksDTO.setAttachmentShowPath(works.getAttachmentShowPath());
            worksDTOList.add(worksDTO);
        }
        return worksDTOList;
    }

    /**
     * 学生的交易总次数加一
     * @param sellerId
     */
    @Override
    public void addTransTime(Long sellerId) {
        Student student = selectStudentById(sellerId);
        student.setTransactionTime(student.getTransactionTime()+1);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    /**
     * 登入更新登入时间
     * @param student
     */
    @Override
    public void login(Student student) {
        student.setLoginTime(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    /**
     * 删除学生，学生相关的作品的一切，user_role
     * @param student
     */
    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void delete(Student student) {
        try{


            userService.deleteUserRole(student.getId());

            List<Works> worksList = worksService.selectWorksByStudentId(student.getId());
            if(worksList.size()!=0){
                for (Works works:worksList){
                    worksService.delete(works);
                }
            }
            studentMapper.deleteByPrimaryKey(student.getId());
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("studentId:"+student.getId()+"删除失败");
        }
    }

    /**
     * 管理员更改学生数据
     * @param upStuDTO
     */
    @Override
    public void update(UpStuDTO upStuDTO) {
        Student student = selectStudentById(upStuDTO.getId());
        student.setStudentId(upStuDTO.getStudentId());
        student.setLoginName(upStuDTO.getLoginName());
        student.setSex(upStuDTO.getSex());
        student.setBankAccount(upStuDTO.getBankAccount());
        student.setTel(upStuDTO.getTel());
        student.setEmail(upStuDTO.getEmail());
        student.setIntroduction(upStuDTO.getIntroduction());
        student.setTransactionTime(upStuDTO.getTransactionTime());
        student.setBreakTime(upStuDTO.getBreakTime());
        student.setIsVerified(upStuDTO.getIsVerified());
        student.setCardId(upStuDTO.getCardId());
        student.setGmtModified(new Date());
        if (studentMapper.updateByPrimaryKey(student)!=1){
            throw new ArtmallException("学生信息更新失败");
        }

    }



    @Override
    public List<ShowDTO> show(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<ShowDTO> students = new ArrayList<>();
        List<Student> list = getAllStudent();

        for (Student student :list){
            ShowDTO showDTO = new ShowDTO();
            showDTO.setId(student.getId());
            showDTO.setStudentId(student.getStudentId());
            showDTO.setLoginName(student.getLoginName());
            showDTO.setSex(student.getSex());
            showDTO.setBankAccount(student.getBankAccount());
            showDTO.setTel(student.getTel());
            showDTO.setEmail(student.getEmail());
            showDTO.setTransactionTime(student.getTransactionTime());
            showDTO.setBreakTime(student.getBreakTime());
            showDTO.setLoginTime(student.getLoginTime());
            showDTO.setIsVerified(student.getIsVerified());
            showDTO.setGmtCreate(student.getGmtCreate());
            showDTO.setGmtModified(student.getGmtModified());
            showDTO.setFollowerCount(student.getFollowerCount());
            showDTO.setCardId(student.getCardId());
            showDTO.setIntroduction(student.getIntroduction());
            students.add(showDTO);

        }


        return students;
    }

    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<>();
        StudentExample example = new StudentExample();
        example.setOrderByClause("`follower_count` desc");
        list = studentMapper.selectByExample(example);
        return list;
    }

    @Override
    public void initPwd(Student student) {
        if (student.getCardId()==null){
            throw new NullException("身份证号不可为空");
        }
        student.setHashedPwd(new SimpleHash("MD5",getInitPassword(student.getCardId()),ByteSource.Util.bytes(student.getSalt()),1024).toString());
        if (studentMapper.updateByPrimaryKey(student)!=1){
            throw new ArtmallException("初始化密码失败");
        }
    }

    @Override
    public void setStatus(Student student, byte status) {
        student.setIsVerified(status);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void resetEmail(Student student, String newEmail) {
        student.setEmail(newEmail);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void addFollowerCount(Long userId) {
        Student student = selectStudentById(userId);
        student.setFollowerCount(student.getFollowerCount()+1);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void deleteFollowerCount(Long userId) {
        Student student = selectStudentById(userId);
        student.setFollowerCount(student.getFollowerCount()-1);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);

    }

    @Override
    public void breakTimeAdd(Long sellerId) {
        Student student = selectStudentById(sellerId);
        student.setBreakTime(student.getBreakTime()+1);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void updatePhone(String tel) {
        Student student = getStudent();
        student.setTel(tel);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void updateDescription(String description) {
        Student student = getStudent();
        student.setIntroduction(description);
        student.setGmtModified(new Date());
        studentMapper.updateByPrimaryKey(student);

    }


}
