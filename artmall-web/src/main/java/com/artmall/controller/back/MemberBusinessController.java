package com.artmall.controller.back;

import com.artmall.DO.Business;
import com.artmall.DTO.businessController.BusinessBaseDTO;
import com.artmall.DTO.businessController.BusinessShowDTO;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 企业管理
 *
 * @author
 * @create 2018-09-07 13:46
 **/
@RestController
@RequestMapping("/businessController")
public class MemberBusinessController {

    /**
     * 管理员添加企业，直接至审核通过状态
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    public ServerResponse add (@RequestBody NewBusinessDTO newBusinessDTO) throws Exception {
        Business business = businessService.register(newBusinessDTO);
        business.setIsVerified(Const.BUSINESS_PASS);
        businessService.addUser(business);
        return ServerResponse.Success("添加成功");
    }


    /**
     * 分页展示所有数据
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public ServerResponse show(@RequestParam("page") int page,
                               @RequestParam("rows") int rows){
        List<BusinessShowDTO> list = businessService.show(page,rows);
        return ServerResponse.Success("展示成功",list);
    }


    @Autowired
    BusinessService businessService;

    /**
     * 审核企业信息，审核通过修改状态,审核通过为2，为通过为3
     * @param map
     * @return
     */
    @RequestMapping(value = "/verify",method = RequestMethod.POST )
    public ServerResponse verify (@RequestBody Map<String,String> map){
        Long businessId = Long.valueOf(map.get("businessId"));
        Byte isVerified = Byte.valueOf(map.get("status"));
        Business business = businessService.selectBusinessById(businessId);
        businessService.setStatus(business,isVerified);
        return ServerResponse.Success("审核完成");
    }

    /**
     * 展示待审核的企业
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/show/verify",method = RequestMethod.GET)
    public ServerResponse showVerify (@RequestParam("page") int page,
                                      @RequestParam("rows") int rows){
        List<BusinessShowDTO> list = businessService.showVerify(page,rows);
        return ServerResponse.Success("展示成功",list);
    }

    /**
     * 删除企业，把除了id外的全部置为null，verify置为4，显示用户已注销
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST )
    public ServerResponse delete (@RequestBody Map<String,String> map){
        Long businessId = Long.valueOf(map.get("businessId"));
        Business business = businessService.selectBusinessById(businessId);
        businessService.delete(business);
        return ServerResponse.Success("删除成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST )
    public ServerResponse update (@RequestBody BusinessBaseDTO updateDTO){
        businessService.update(updateDTO);
        return ServerResponse.Success("更新成功");
    }

    @RequestMapping(value = "/delete/all",method = RequestMethod.POST )
    public ServerResponse deleteAll (@RequestBody Map<String,String> map){
        String data = map.get("id");
        String params[] = data.split(",");
        for (int i=0;i<params.length;i++){
            if (params[i]==null){
                i++;
            }
            Business business = businessService.selectBusinessById(Long.valueOf(params[i]));
            businessService.delete(business);
        }
        return ServerResponse.Success("删除成功");
    }

}
