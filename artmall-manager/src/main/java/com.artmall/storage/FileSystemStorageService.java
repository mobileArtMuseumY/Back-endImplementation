package com.artmall.storage;


import com.artmall.DTO.studentController.NewStudentDTO;
import com.artmall.exception.NullException;
import com.artmall.exception.FileException;
import com.artmall.service.StorageService;
import com.artmall.service.StudentService;
import com.artmall.utils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @create 2018-08-20 16:18
 **/
@Service
public class FileSystemStorageService implements StorageService {

    private final static Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final Path rootLocation;
    private final Path watermarkLocationp;
    private final Path imagedirectoryLocation;
    private final Path businessAttachmentLocation;

    public Path getImagedirectoryLocation() {
        return imagedirectoryLocation;
    }


    public Path getRootLocation() {
        return rootLocation;
    }
    public Path getWatermarkLocationp(){
        return watermarkLocationp;
    }
    public Path getBusinessAttachmentLocation(){
        return businessAttachmentLocation;
    }

    //获取配置文件里面的地址
    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.watermarkLocationp = Paths.get(properties.getWatermark());
        this.imagedirectoryLocation = Paths.get(properties.getImagedirectory());
        this.businessAttachmentLocation = Paths.get(properties.getBusinessAttachment());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(watermarkLocationp);
            Files.createDirectories(imagedirectoryLocation);
            Files.createDirectories(businessAttachmentLocation);
        }
        catch (IOException e) {
            throw new FileException("Could not initialize storage", e);
        }
    }

    /**
     * 单文件的上传
     * @param file
     * @return
     */
    @Override
    public Path store(MultipartFile file,Path path)  {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

            if (file.isEmpty()) {
                throw new FileException("Failed to store empty file " + filename);

            }
            if (filename.contains("..")) {
                throw new FileException(
                        "Cannot store file with relative path outside current directory "
                                + filename);            }

        try {
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, path,
                            StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (IOException e) {
            throw new FileException("Failed to store file " + filename, e);

        }

    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }


    public Path[] fileUpload(String path_sign,MultipartFile []file){
        int len=file.length;
        //为了区分每个不同类型的文件放在不同的文件夹

        Path []filePath=new Path[len];
//        String [] filePathByString = new String[len];
        Path path = null;

        //文件路径为路径加上文件名称
        for(int i=0;i<len;i++){
            String fileName = FileUtils.creatFileName(file[i]);
            path=makePath(path_sign,fileName);
            filePath[i]=path;
        }
        //初始化文件
        File[]files=new File[len];

        for(int i=0;i<len;i++) {

            try {
                InputStream inputStream = file[i].getInputStream();
                Files.copy(inputStream, filePath[i],
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println("1");
            } catch (IOException e) {
                if (i == len)
                    i--;
                for (int j = i; j >= 0; j--) {
                    files[j].delete();
                }
                throw new FileException("Failed to store file " + file[i].getOriginalFilename(), e);
            }
        }
        return filePath;

    }

    @Autowired
    StudentService studentService;

    public void uploadExcel(MultipartFile multipartFile) throws Exception {
        Workbook wb = null;
        File file = transferToFile(multipartFile);
        if (ExcelUtils.isExcel2007(multipartFile.getOriginalFilename())) {
            wb = new XSSFWorkbook(new FileInputStream(file));
        } else {
            wb = new HSSFWorkbook(new FileInputStream(file));
        }
        Sheet sheet = wb.getSheetAt(0);


        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (i == 0) {
                Map<String,String> stuMeg = getValue(row);
                checkValue(stuMeg);
            } else {
                Map<String,String> stuMeg = getValue(row);

                NewStudentDTO newStudentDTO = setNewStudentDTO(stuMeg);

                studentService.addUser(newStudentDTO);


            }
        }
        wb.close();
    }

    /**
     * 检查第一排数据是否正确
     * @param stuMeg
     */
    private void checkValue(Map<String,String> stuMeg) throws Exception {
        if (!(stuMeg.get("studentId").equals("学号")&&stuMeg.get("loginName").equals("名字")&&
                stuMeg.get("sex").equals("性别")&&stuMeg.get("bankAccount").equals("支付宝账号")&&
                stuMeg.get("tel").equals("电话号码")&&stuMeg.get("cardId").equals("身份证")&&stuMeg.get("email").equals("邮箱"))){
            throw new Exception("excel格式有误");
        }
    }

    private NewStudentDTO setNewStudentDTO(Map<String,String> stuMeg) {
        NewStudentDTO newStudentDTO = new NewStudentDTO();
        newStudentDTO.setStudentId(stuMeg.get("studentId"));
        newStudentDTO.setId(Tools.initId());
        newStudentDTO.setLoginName(stuMeg.get("loginName"));
        newStudentDTO.setSex(stuMeg.get("sex"));
        newStudentDTO.setBankAccount(stuMeg.get("bankAccount"));
        newStudentDTO.setTel(stuMeg.get("tel"));
        newStudentDTO.setCardId(stuMeg.get("cardId"));
        newStudentDTO.setEmail(stuMeg.get("email"));
        return newStudentDTO;
    }

    private Map<String,String> getValue(Row row) {
        DataFormatter formatter = new DataFormatter();
        String studentId = formatter.formatCellValue(row.getCell(0));
        String loginName = formatter.formatCellValue(row.getCell(1));
        String sex = formatter.formatCellValue(row.getCell(2));
        String bankAccount = formatter.formatCellValue(row.getCell(3));
        String tel = formatter.formatCellValue(row.getCell(4));
        String cardId = formatter.formatCellValue(row.getCell(5));
        String email = formatter.formatCellValue(row.getCell(6));
        Map<String,String> stuMeg = new HashMap<>();
        stuMeg.put("studentId",studentId);
        stuMeg.put("loginName",loginName);
        stuMeg.put("sex",sex);
        stuMeg.put("bankAccount",bankAccount);
        stuMeg.put("tel",tel);
        stuMeg.put("cardId",cardId);
        stuMeg.put("email",email);
        return stuMeg;
    }

    /**
     * 将MultipartFile转换未File
     * @param multipartFile
     * @return
     */
    private File transferToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }

    /**
     * 返回文件路径加文件名
     * @param path_sign
     * @param filename
     * @return
     */
    public Path makePath(String path_sign,String filename){
        File file=new File(this.rootLocation.resolve(path_sign).toString());
        if(!file.exists()) file.mkdirs();
        return file.toPath().resolve(filename);
    }

    public Path makeWatermarkPath(String path_sign, String filename){
        File file = new File(this.watermarkLocationp.resolve(path_sign).toString());
        if (!file.exists()) file.mkdirs();
        return file.toPath().resolve(filename);
    }

    @Override
    public Path makeImageDirectoryPath(String pathSign, String filename) {
        File file = new File(this.imagedirectoryLocation.resolve(pathSign).toString());
        if (!file.exists()) file.mkdirs();
        return file.toPath().resolve(filename);
    }

    @Override
    public Path getBusinessAttachmentPath(String pathSign,String filename) {
        File file = new File(this.businessAttachmentLocation.resolve(pathSign).toString());
        if (!file.exists()) file.mkdirs();
        return file.toPath().resolve(filename);
    }



    @Autowired
    private ServletContext servletContext;
    @Override
    public ResponseEntity<InputStreamResource> fileDownload(String fileName,String filePath,Long fileSize) {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext,fileName);
        log.info("fileName:"+fileName);
        log.info("mediaType:"+mediaType);

        File file =new File(filePath);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new FileException("下载失败");
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+file.getName())
                .contentType(mediaType)
                .contentLength(fileSize)
                .body(resource);
    }

    @Override
    public Path getProjectDirectory(Long projectId) {
        String pathSign = "//project//"+projectId;
        Path path = this.rootLocation.resolve(pathSign);
        log.info("the direction is :"+path.toString());
        return path;
    }

    @Override
    public Path getAvatarPath(String pathSign, String fileName) {
        File file = new File(this.rootLocation.resolve(pathSign).toString());
        if (!file.exists()) file.mkdirs();
        return file.toPath().resolve(fileName);
    }


}


