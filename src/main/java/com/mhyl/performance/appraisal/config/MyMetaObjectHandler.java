package com.mhyl.performance.appraisal.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author ArchieDing
 * @since 2020/09/02
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", Long.class, System.currentTimeMillis());

		this.strictInsertFill(metaObject, "isDelete", String.class, "N");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
	}
}
