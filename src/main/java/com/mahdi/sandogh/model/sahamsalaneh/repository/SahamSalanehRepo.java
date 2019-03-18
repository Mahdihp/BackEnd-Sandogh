package com.mahdi.sandogh.model.sahamsalaneh.repository;

import com.mahdi.sandogh.model.sahamsalaneh.SahamSalaneh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/18/19
 * Time: 22:06
 * https://github.com/mahdihp
 */


@Repository
public interface SahamSalanehRepo extends JpaRepository<SahamSalaneh, Long> {

    Optional<SahamSalaneh> findByUid(UUID uid);

    Optional<SahamSalaneh> findByYesrs(int years);

    Optional<SahamSalaneh> findByMembershipFee(long money);

}
