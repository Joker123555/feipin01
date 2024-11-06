package cn.zwz.res.serviceimpl;

import cn.zwz.res.mapper.SalesOrderMapper;
import cn.zwz.res.entity.SalesOrder;
import cn.zwz.res.service.ISalesOrderService;
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
public class ISalesOrderServiceImpl extends ServiceImpl<SalesOrderMapper, SalesOrder> implements ISalesOrderService {

    @Autowired
    private SalesOrderMapper salesOrderMapper;
}