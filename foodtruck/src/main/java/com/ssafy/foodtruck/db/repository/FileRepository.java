package com.ssafy.foodtruck.db.repository;

import com.ssafy.foodtruck.db.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {
}
