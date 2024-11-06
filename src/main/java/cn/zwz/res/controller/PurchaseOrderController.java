package cn.zwz.res.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.res.entity.PurchaseOrder;
import cn.zwz.res.entity.Resource;
import cn.zwz.res.entity.SalesOrder;
import cn.zwz.res.entity.TransactionOrder;
import cn.zwz.res.service.IPurchaseOrderService;
import cn.hutool.core.util.StrUtil;
import cn.zwz.res.service.IResourceService;
import cn.zwz.res.service.ITransactionOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "资源求购单管理接口")
@RequestMapping("/zwz/purchaseOrder")
@Transactional
public class PurchaseOrderController {

    @Autowired
    private IPurchaseOrderService iPurchaseOrderService;

    @Autowired
    private IResourceService iResourceService;

    @Autowired
    private ITransactionOrderService iTransactionOrderService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条资源求购单")
    public Result<PurchaseOrder> get(@RequestParam String id){
        return new ResultUtil<PurchaseOrder>().setData(iPurchaseOrderService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源求购单个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iPurchaseOrderService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源求购单")
    public Result<List<PurchaseOrder>> getAll(){
        return new ResultUtil<List<PurchaseOrder>>().setData(iPurchaseOrderService.list());
    }
    @RequestMapping(value = "/getNotSellAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询微交易的资源出售单")
    public Result<List<PurchaseOrder>> getNotSellAll(){
        QueryWrapper<PurchaseOrder> qw = new QueryWrapper<>();
        qw.eq("status",0);
        return new ResultUtil<List<PurchaseOrder>>().setData(iPurchaseOrderService.list(qw));
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资源求购单")
    public Result<IPage<PurchaseOrder>> getByPage(@ModelAttribute PurchaseOrder purchaseOrder ,@ModelAttribute PageVo page){
        QueryWrapper<PurchaseOrder> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(purchaseOrder.getResName())) {
            qw.like("res_name",purchaseOrder.getResName());
        }
        if(!ZwzNullUtils.isNull(purchaseOrder.getReleaseName())) {
            qw.like("release_name",purchaseOrder.getReleaseName());
        }
        IPage<PurchaseOrder> data = iPurchaseOrderService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<PurchaseOrder>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改资源求购单")
    public Result<PurchaseOrder> saveOrUpdate(PurchaseOrder purchaseOrder){
        if(iPurchaseOrderService.saveOrUpdate(purchaseOrder)){
            return new ResultUtil<PurchaseOrder>().setData(purchaseOrder);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资源求购单")
    public Result<PurchaseOrder> insert(PurchaseOrder purchaseOrder){
        Resource resource = iResourceService.getById(purchaseOrder.getResId());
        if(resource == null) {
            return ResultUtil.error("资源不存在");
        }
        purchaseOrder.setResName(resource.getType() + "/" + resource.getTitle() + "/" + resource.getModal());
        User currUser = securityUtil.getCurrUser();
        purchaseOrder.setReleaseId(currUser.getId());
        purchaseOrder.setReleaseName(currUser.getNickname());
        purchaseOrder.setStatus(0);
        iPurchaseOrderService.saveOrUpdate(purchaseOrder);
        return new ResultUtil<PurchaseOrder>().setData(purchaseOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资源求购单")
    public Result<PurchaseOrder> update(PurchaseOrder purchaseOrder){
        Resource resource = iResourceService.getById(purchaseOrder.getResId());
        if(resource == null) {
            return ResultUtil.error("资源不存在");
        }
        purchaseOrder.setResName(resource.getType() + "/" + resource.getTitle() + "/" + resource.getModal());
        iPurchaseOrderService.saveOrUpdate(purchaseOrder);
        return new ResultUtil<PurchaseOrder>().setData(purchaseOrder);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资源求购单")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            PurchaseOrder order = iPurchaseOrderService.getById(id);
            if(order == null) {
                continue;
            }
            if(!Objects.equals(0,order.getStatus())) {
                return ResultUtil.error("已出售订单不可删除");
            }
            iPurchaseOrderService.removeById(id);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    @ApiOperation(value = "交易订单")
    public Result<Object> sell(@RequestParam String id){
        PurchaseOrder order = iPurchaseOrderService.getById(id);
        if(order == null) {
            return ResultUtil.error("订单不存在");
        }
        // 新增交易单
        TransactionOrder tOrder = new TransactionOrder();
        tOrder.setType(1);
        tOrder.setOrderId(order.getId());
        tOrder.setResId(order.getResId());
        tOrder.setResName(order.getResName());
        tOrder.setReleaseId(order.getReleaseId());
        tOrder.setReleaseName(order.getReleaseName());
        tOrder.setNumber(order.getNumber());
        tOrder.setPrice(order.getPrice());
        tOrder.setContent(order.getContent());
        User currUser = securityUtil.getCurrUser();
        tOrder.setFinishId(currUser.getId());
        tOrder.setFinishName(currUser.getNickname());
        tOrder.setFinishTime(DateUtil.now());
        iTransactionOrderService.saveOrUpdate(tOrder);
        // 持久化
        order.setStatus(0);
        iPurchaseOrderService.saveOrUpdate(order);
        return ResultUtil.success();
    }
}
