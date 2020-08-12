package com.mahdi.sandogh.model.found.repository;

import com.mahdi.sandogh.model.found.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mahdi
 * DateTime: ۱۲/۰۸/۲۰۲۰ - 17:23
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Repository
public interface FundRepo extends JpaRepository<Fund,Integer> {

    List<Fund> findAllByDisplayNameAndDeleted(String displayName,Boolean deleted);
    List<Fund> findAllByDeleted(Boolean deleted);
}
