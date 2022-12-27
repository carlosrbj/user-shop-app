package com.hsob.usershopapp.repository;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author carlos
 */
public abstract class ShopAppDAO {
    @Autowired
    protected MongoTemplate shopAppDB;
    protected final Log logger = LogFactory.getLog(getClass());
}

