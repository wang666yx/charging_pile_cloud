package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.finance.StockUserChargeVO;
import com.suda.platform.VO.finance.StockUserMoneyDetailVO;
import com.suda.platform.enums.finance.FinancialTypeEnum;
import com.suda.platform.service.IStockUserChargeService;
import com.suda.platform.service.IStockUserMoneyDetailService;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 财务管理
 * @author kongling
 * @package com.suda.server.service.admin.controller
 * @date 2019-05-09  10:18
 * @project suda_cloud
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "admin/financial",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminFinancialController {
    @Autowired
    private IStockUserMoneyDetailService stockUserMoneyDetailService;
    @Autowired
    private IStockUserChargeService stockUserChargeService;

    /**
     * 财务流水
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/getMoneyDetails")
    @ResponseBody
    public Map<String, Object> getMoneyDetails(StockUserMoneyDetailVO vo, PageUtil pageUtil) {
        PageInfo<StockUserMoneyDetailVO> listVOPageInfo = stockUserMoneyDetailService.getAdminMoneyDetails(vo, pageUtil);
        for(StockUserMoneyDetailVO vos:listVOPageInfo.getList()){
            vos.setTypeStr(FinancialTypeEnum.getFinancialTypeEnumByCode(vos.getType()).getMessage());
        }
        return ResponseUtil.getSuccessMap(listVOPageInfo);
    }


    /**
     * 获取充值记录
     * @param coinCharge
     * @return
     */
    @RequestMapping(value = "getStockUserCoinCharges", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(notes = "", value = "")
    public Map<String, Object> getAppStockUserCoinCharges(StockUserChargeVO coinCharge, PageUtil pageUtil) {
        PageInfo<StockUserChargeVO> lists = stockUserChargeService.getAdminStockUserCoinCharges(coinCharge,pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }

}
