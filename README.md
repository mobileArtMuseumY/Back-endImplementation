**项目发布完成**：

简单套用了个页面，现附件只能传一个，附件可能多个还没做  

发布项目页面：http://localhost:8080/view/projectadd

一个接口：

1、发布项目：http://localhost:8080/project/add

请求参数：  
projectStr 
```
{
	"projectName" : "artmuseum",
	"budget" : "10000",
	"expectedTime" : "20",
	"tenderPeriod" : "30",
	"skillList" : "[1]",
	"projectDescription" : "艺术"
}
```
projectAttachments：附件


**企业注册完成**：

简单套用了个页面，前段校验还没做

企业注册界面：http://localhost:8080/view/businessregist


三个接口:   
* 1、获取手机验证码： 
```
http://localhost:8080/business/sendverifycode?telephone=15289089090
```

* 2、校验手机验证码：
```
http://localhost:8080/business/verifycode?mobileVerifyCodeActual=123456
```

* 3、注册:
```
http://localhost:8080/business/regist
```
