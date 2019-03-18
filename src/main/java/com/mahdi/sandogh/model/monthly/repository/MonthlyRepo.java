package com.mahdi.sandogh.model.monthly.repository;

import com.mahdi.sandogh.model.monthly.Monthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 21:15
 * https://github.com/mahdihp
 */


@Repository
public interface MonthlyRepo extends JpaRepository<Monthly, Long> {

    Optional<Monthly> findByUid(UUID uid);

    Optional<List<Monthly>> findAllByAccountUid(UUID uid);
}
