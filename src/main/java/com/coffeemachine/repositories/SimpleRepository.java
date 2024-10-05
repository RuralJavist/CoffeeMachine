package com.coffeemachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface SimpleRepository <T, R> extends JpaRepository<T, R> {
}
