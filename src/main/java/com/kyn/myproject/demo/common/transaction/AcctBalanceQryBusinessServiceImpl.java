package com.kyn.myproject.demo.common.transaction;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Kangyanan
 * @Description: 余额查询服务处理实现
 * @date 2021/1/22 18:01
 */
@Service
public class AcctBalanceQryBusinessServiceImpl extends AbstractWapBusinessService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractWapBusinessService.class);
    @Override
    public void execute(ProjectContext context) {
        logger.info("进入AcctBalanceQryBusinessServiceImpl类execute方法！");
        super.execute(context);
    }
}
