package com.xdsoft.app.repos;

import com.xdsoft.app.domain.RatingDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Repository
@Transactional
public interface RatingRepo extends CrudRepository <RatingDb, Long>{

    @Query(value = "select * from RatingDb r where r.date >= ?1 and r.date <= ?2 and r.position = '1'", nativeQuery = true)
    Iterable<RatingDb> findByDateBetween(Date frommillis, Date tomillis);

    @Query(value = "select * from RatingDb r where r.date = ?1 and r.position = '1'", nativeQuery = true)
    Iterable<RatingDb> findByDateNative(Date frommillis);
}
