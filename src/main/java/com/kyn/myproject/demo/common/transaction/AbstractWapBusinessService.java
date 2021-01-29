package com.kyn.myproject.demo.common.transaction;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Kangyanan
 * @Description: 网关类交易
 * @date 2021/1/22 18:00
 */
@Service
public abstract class AbstractWapBusinessService extends AbstractBusinessService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractWapBusinessService.class);
    @Override
    public void execute(ProjectContext context) {
        logger.info("进入AbstractWapBusinessService类execute方法！");
        logger.info("交易({})-网关请求处理-开始", context.getClientTransCode());
        super.submit(context);
    }
}
