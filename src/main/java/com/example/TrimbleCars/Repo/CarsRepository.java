package com.example.TrimbleCars.Repo;

import com.example.TrimbleCars.Entity.CarsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<CarsEntity, Integer> {

    @Query(
            value = "SELECT * FROM CARS C WHERE C.car_number = ?1",
            nativeQuery = true)
    Optional<CarsEntity> findBycarNumber(int carNumber);
}
