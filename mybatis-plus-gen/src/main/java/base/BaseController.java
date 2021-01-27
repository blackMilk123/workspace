package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController<B extends BaseService, T extends BaseEntity> {

    protected static Logger logger = LogManager.getLogger(BaseController.class);

    @Autowired
    protected B service;


}
