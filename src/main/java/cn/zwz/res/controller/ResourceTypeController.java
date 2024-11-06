package cn.zwz.res.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.res.entity.ResourceType;
import cn.zwz.res.service.IResourceTypeService;
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
@Api(tags = "资源类型管理接口")
@RequestMapping("/zwz/resourceType")
@Transactional
public class ResourceTypeController {

    @Autowired
    private IResourceTypeService iResourceTypeService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条资源类型")
    public Result<ResourceType> get(@RequestParam String id){
        return new ResultUtil<ResourceType>().setData(iResourceTypeService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源类型个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iResourceTypeService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源类型")
    public Result<List<ResourceType>> getAll(){
        return new ResultUtil<List<ResourceType>>().setData(iResourceTypeService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资源类型")
    public Result<IPage<ResourceType>> getByPage(@ModelAttribute ResourceType resourceType ,@ModelAttribute PageVo page){
        QueryWrapper<ResourceType> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(resourceType.getTitle())) {
            qw.like("title",resourceType.getTitle());
        }
        IPage<ResourceType> data = iResourceTypeService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<ResourceType>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改资源类型")
    public Result<ResourceType> saveOrUpdate(ResourceType resourceType){
        if(iResourceTypeService.saveOrUpdate(resourceType)){
            return new ResultUtil<ResourceType>().setData(resourceType);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资源类型")
    public Result<ResourceType> insert(ResourceType resourceType){
        iResourceTypeService.saveOrUpdate(resourceType);
        return new ResultUtil<ResourceType>().setData(resourceType);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资源类型")
    public Result<ResourceType> update(ResourceType resourceType){
        iResourceTypeService.saveOrUpdate(resourceType);
        return new ResultUtil<ResourceType>().setData(resourceType);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资源类型")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iResourceTypeService.removeById(id);
        }
        return ResultUtil.success();
    }
}
