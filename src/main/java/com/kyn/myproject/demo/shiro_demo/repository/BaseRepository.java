package com.kyn.myproject.demo.shiro_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 17:38
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T,ID> {

}
