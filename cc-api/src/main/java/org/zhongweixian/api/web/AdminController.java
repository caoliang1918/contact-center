package org.zhongweixian.api.web;

import org.cti.cc.po.AdminAccountInfo;
import org.cti.cc.po.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by caoliang on 2022/1/6
 * <p>
 * 超管操作
 */
@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {


    /**
     * 添加菜单
     *
     * @return
     */
    @GetMapping("getMenus")
    public CommonResponse getMenus(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo) {

        return new CommonResponse("");
    }


}
