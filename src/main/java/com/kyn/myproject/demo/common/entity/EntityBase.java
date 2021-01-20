package com.kyn.myproject.demo.common.entity;

import com.kyn.myproject.demo.common.Domain;

/**
 * @author Kangyanan
 * @Description: 实体基类
 * @date 2021/1/19 22:54
 */
public abstract class EntityBase implements Domain {
    protected abstract String getEntityId();
}
