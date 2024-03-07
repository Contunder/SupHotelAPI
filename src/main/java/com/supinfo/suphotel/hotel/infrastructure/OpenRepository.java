package com.supinfo.suphotel.hotel.infrastructure;

import com.supinfo.suphotel.hotel.infrastructure.model.Open;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Set;

public interface OpenRepository extends JpaRepository<Open, Long> {
    Open getOpenByDate(Date date);
    boolean existsByDate(Date date);
}
