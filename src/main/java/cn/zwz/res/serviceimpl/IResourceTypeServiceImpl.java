package cn.zwz.res.serviceimpl;

import cn.zwz.res.mapper.ResourceTypeMapper;
import cn.zwz.res.entity.ResourceType;
import cn.zwz.res.service.IResourceTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@Service
@Transactional
public class IResourceTypeServiceImpl extends ServiceImpl<ResourceTypeMapper, ResourceType> implements IResourceTypeService {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;
}