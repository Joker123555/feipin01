package cn.zwz.res.serviceimpl;

import cn.zwz.res.mapper.ReceptionistMapper;
import cn.zwz.res.entity.Receptionist;
import cn.zwz.res.service.IReceptionistService;
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
public class IReceptionistServiceImpl extends ServiceImpl<ReceptionistMapper, Receptionist> implements IReceptionistService {

    @Autowired
    private ReceptionistMapper receptionistMapper;
}