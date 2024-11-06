package cn.zwz.res.serviceimpl;

import cn.zwz.res.mapper.ResourceOrganizationMapper;
import cn.zwz.res.entity.ResourceOrganization;
import cn.zwz.res.service.IResourceOrganizationService;
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
public class IResourceOrganizationServiceImpl extends ServiceImpl<ResourceOrganizationMapper, ResourceOrganization> implements IResourceOrganizationService {

    @Autowired
    private ResourceOrganizationMapper resourceOrganizationMapper;
}