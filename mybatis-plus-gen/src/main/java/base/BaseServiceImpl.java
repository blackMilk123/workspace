package base;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseServiceImpl<D extends BaseDao<T>, T extends BaseEntity> extends ServiceImpl<D, T> {

    protected static Logger logger = LogManager.getLogger(BaseServiceImpl.class);

}
