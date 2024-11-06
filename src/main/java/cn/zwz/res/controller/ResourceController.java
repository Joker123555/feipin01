package cn.zwz.res.controller;

import cn.zwz.basics.log.LogType;
import cn.zwz.basics.log.SystemLog;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.data.vo.AntvVo;
import cn.zwz.res.entity.Resource;
import cn.zwz.res.entity.ResourceType;
import cn.zwz.res.service.IResourceService;
import cn.hutool.core.util.StrUtil;
import cn.zwz.res.service.IResourceTypeService;
import cn.zwz.test.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "资源品类管理接口")
@RequestMapping("/zwz/resource")
@Transactional
public class ResourceController {

    @Autowired
    private IResourceService iResourceService;

    @SystemLog(about = "查询图表数据", type = LogType.CHART,doType = "CHART-01")
    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        List<Resource> resourceList = iResourceService.list();
        for (Resource o : resourceList) {
            boolean flag = false;
            for (AntvVo vo : ansList) {
                if(Objects.equals(vo.getTitle(),o.getType())) {
                    flag = true;
                    vo.setValue(vo.getValue().add(BigDecimal.ONE));
                    break;
                }
            }
            if(!flag) {
                AntvVo vo = new AntvVo();
                vo.setTitle(o.getType());
                vo.setType("资源类型");
                vo.setValue(BigDecimal.ONE);
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条资源品类")
    public Result<Resource> get(@RequestParam String id){
        return new ResultUtil<Resource>().setData(iResourceService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源品类个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iResourceService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资源品类")
    public Result<List<Resource>> getAll(){
        return new ResultUtil<List<Resource>>().setData(iResourceService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资源品类")
    public Result<IPage<Resource>> getByPage(@ModelAttribute Resource resource ,@ModelAttribute PageVo page){
        QueryWrapper<Resource> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(resource.getTitle())) {
            qw.like("title",resource.getTitle());
        }
        if(!ZwzNullUtils.isNull(resource.getType())) {
            qw.like("type",resource.getType());
        }
        IPage<Resource> data = iResourceService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Resource>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改资源品类")
    public Result<Resource> saveOrUpdate(Resource resource){
        if(iResourceService.saveOrUpdate(resource)){
            return new ResultUtil<Resource>().setData(resource);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资源品类")
    public Result<Resource> insert(Resource resource){
        iResourceService.saveOrUpdate(resource);
        return new ResultUtil<Resource>().setData(resource);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资源品类")
    public Result<Resource> update(Resource resource){
        iResourceService.saveOrUpdate(resource);
        return new ResultUtil<Resource>().setData(resource);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资源品类")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iResourceService.removeById(id);
        }
        return ResultUtil.success();
    }
}
