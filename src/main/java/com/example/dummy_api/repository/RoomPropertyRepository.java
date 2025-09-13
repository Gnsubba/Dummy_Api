package com.example.dummy_api.repository;

import com.example.dummy_api.models.RoomProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPropertyRepository extends JpaRepository<RoomProperty, Long> {
}
