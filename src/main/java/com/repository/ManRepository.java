package com.repository;

import com.domain.Man;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManRepository extends JpaRepository<Man, Integer> {
    Man findByAgeAndName(int age, String name);

    @EntityGraph(attributePaths = "addresses")
    List<Man> findAllByAge(int age);

    @Query("DELETE FROM Man m WHERE m.age = :age")
    @Modifying
    void deleteAllByAge(@Param("age") int age);
}
