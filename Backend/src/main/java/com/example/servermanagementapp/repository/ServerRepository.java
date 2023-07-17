package com.example.servermanagementapp.repository;

import com.example.servermanagementapp.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long>
{
    Server findByIpAddress(String ipAddress);
}
