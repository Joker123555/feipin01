package cn.zwz.res.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.res.entity.ResourceOrganization;
import cn.zwz.res.service.IResourceOrganizationService;
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
@Api(tags = "资源回收机构管理接口")
@RequestMapping("/zwz/resourceOrganization")
@Transactional
public class ResourceOrganizationController {

    @Autowired
    private IResourceOrganizationService iResourceOrganizationService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条资源回收机构")
    public Result<ResourceOrganization> get(@RequestParam String id){
        return new ResultUtil<ResourceOrganization>().setData(iResourceOrganizationService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源回收机构个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iResourceOrganizationService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源回收机构")
    public Result<List<ResourceOrganization>> getAll(){
        return new ResultUtil<List<ResourceOrganization>>().setData(iResourceOrganizationService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资源回收机构")
    public Result<IPage<ResourceOrganization>> getByPage(@ModelAttribute ResourceOrganization resourceOrganization ,@ModelAttribute PageVo page){
        QueryWrapper<ResourceOrganization> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(resourceOrganization.getTitle())) {
            qw.like("title",resourceOrganization.getTitle());
        }
        if(!ZwzNullUtils.isNull(resourceOrganization.getAddress())) {
            qw.like("address",resourceOrganization.getAddress());
        }
        IPage<ResourceOrganization> data = iResourceOrganizationService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<ResourceOrganization>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改资源回收机构")
    public Result<ResourceOrganization> saveOrUpdate(ResourceOrganization resourceOrganization){
        if(iResourceOrganizationService.saveOrUpdate(resourceOrganization)){
            return new ResultUtil<ResourceOrganization>().setData(resourceOrganization);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资源回收机构")
    public Result<ResourceOrganization> insert(ResourceOrganization resourceOrganization){
        iResourceOrganizationService.saveOrUpdate(resourceOrganization);
        return new ResultUtil<ResourceOrganization>().setData(resourceOrganization);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资源回收机构")
    public Result<ResourceOrganization> update(ResourceOrganization resourceOrganization){
        iResourceOrganizationService.saveOrUpdate(resourceOrganization);
        return new ResultUtil<ResourceOrganization>().setData(resourceOrganization);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资源回收机构")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iResourceOrganizationService.removeById(id);
        }
        return ResultUtil.success();
    }
}
