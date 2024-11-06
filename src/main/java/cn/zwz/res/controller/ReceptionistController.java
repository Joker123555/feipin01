package cn.zwz.res.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.res.entity.Receptionist;
import cn.zwz.res.service.IReceptionistService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "咨询接待人员管理接口")
@RequestMapping("/zwz/receptionist")
@Transactional
public class ReceptionistController {

    @Autowired
    private IReceptionistService iReceptionistService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条咨询接待人员")
    public Result<Receptionist> get(@RequestParam String id){
        return new ResultUtil<Receptionist>().setData(iReceptionistService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部咨询接待人员个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iReceptionistService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部咨询接待人员")
    public Result<List<Receptionist>> getAll(){
        return new ResultUtil<List<Receptionist>>().setData(iReceptionistService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询咨询接待人员")
    public Result<IPage<Receptionist>> getByPage(@ModelAttribute Receptionist receptionist ,@ModelAttribute PageVo page){
        QueryWrapper<Receptionist> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(receptionist.getName())) {
            qw.like("name",receptionist.getName());
        }
        if(!ZwzNullUtils.isNull(receptionist.getMobile())) {
            qw.like("mobile",receptionist.getMobile());
        }
        IPage<Receptionist> data = iReceptionistService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Receptionist>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改咨询接待人员")
    public Result<Receptionist> saveOrUpdate(Receptionist receptionist){
        if(iReceptionistService.saveOrUpdate(receptionist)){
            return new ResultUtil<Receptionist>().setData(receptionist);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增咨询接待人员")
    public Result<Receptionist> insert(Receptionist receptionist){
        iReceptionistService.saveOrUpdate(receptionist);
        return new ResultUtil<Receptionist>().setData(receptionist);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑咨询接待人员")
    public Result<Receptionist> update(Receptionist receptionist){
        iReceptionistService.saveOrUpdate(receptionist);
        return new ResultUtil<Receptionist>().setData(receptionist);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除咨询接待人员")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iReceptionistService.removeById(id);
        }
        return ResultUtil.success();
    }
}
