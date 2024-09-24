package com.myfirstproject.springapplication.repositories;

import com.myfirstproject.springapplication.entity.DailyWage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DailyWageRepository extends JpaRepository<DailyWage,Long> {

    List<DailyWage> findByWorkerId(Long workerId);

    // Sum of all wages for a specific worker
    @Query("SELECT SUM(w.wageAmount) FROM DailyWage w WHERE w.worker.id = :workerId")
    Double sumWagesByWorkerId(@Param("workerId") Long workerId);

    // Delete wage by date for a specific worker
    @Query("DELETE FROM DailyWage w WHERE w.worker.id = :workerId AND w.date = :date")
    void deleteByWorkerIdAndDate(@Param("workerId") Long workerId, @Param("date") LocalDate date);

}
