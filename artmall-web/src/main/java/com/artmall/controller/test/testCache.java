/*
package com.artmall.controller.test;


import com.artmall.pojo.Student;
import com.artmall.response.ServerResponse;
import com.artmall.service.StorageService;
import com.artmall.service.StudentService;
import com.artmall.storage.FileSystemStorageService;
import com.artmall.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.Cacheable;

import javax.annotation.Resource;

*/
/**
 * @author
 * @create 2018-09-02 13:35
 *//*


@RestController
public class testCache {

    @Resource
    StudentService studentService;

    @Resource
    StringRedisTemplate stringRedisTemplate;   //k-v都是对象的



    */
/**
     * 将方法的运行结果进行缓存，以后再要数据，就不用再连接数据库
     * <p>
     * 几个属性：
     * cacheNames/value:指定缓存组件的名字
     * key:缓存数据使用的key；可以用它来指定，默认的时候用方法参数的值
     *
     * @param id
     * @return
     *//*




    @PostMapping(value = "/cache")
    public Student test(@RequestParam(name = "id") Long id) {
        System.out.println("find:" + id);
        return studentService.selectStudentById(id);

    }

    */
/**
     * Redis常见的五大数据类型
     * String(字符串)
     * stringRedisTemplate.opsForValue()[字符串]
     * stringRedisTemplate.opsForHash()[操作Hash]
     *//*


    @RequestMapping(value = "/test1")
    public void test01() {
        //给redis保存一个数据
        stringRedisTemplate.opsForValue().append("msg", "hello");
    }

    @RequestMapping(value = "/test2")
    public void test02(@RequestParam(name = "id") Long id) {
        Student student = studentService.selectStudentById(id);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到数据库
//        redisTemplate.opsForValue().set("tem",student);
        //1、将数据以json的方式保存
        //  (1)自己将数据转换未json
        //   (2) RedisTemplate里面有序列化规则
    }

    */
/**
     *    测试图片处理
     *
     *//*


*/
/*    @RequestMapping(value = "/testImage" )
    public void test03 (){
        ImageUtils.generateDirectoryThumbnail();

    }*//*


    @Autowired
    StorageService storageService;

    @RequestMapping(value = "/testStore",method = RequestMethod.POST)
    public ServerResponse test04 (@RequestParam("file")MultipartFile []multipartFiles){
        System.out.println("hello world");
        storageService.fileUpload("test\\",multipartFiles);
        return ServerResponse.Success("上传成功");

    }

}




*/
