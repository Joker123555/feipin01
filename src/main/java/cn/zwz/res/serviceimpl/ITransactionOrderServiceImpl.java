package cn.zwz.res.serviceimpl;

import cn.zwz.res.mapper.TransactionOrderMapper;
import cn.zwz.res.entity.TransactionOrder;
import cn.zwz.res.service.ITransactionOrderService;
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
public class ITransactionOrderServiceImpl extends ServiceImpl<TransactionOrderMapper, TransactionOrder> implements ITransactionOrderService {

    @Autowired
    private TransactionOrderMapper transactionOrderMapper;
}