package com.example.servermanagementapp.service;

import com.example.servermanagementapp.enumeration.Status;
import com.example.servermanagementapp.model.Server;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface ServerService
{
    Server saveServer(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> getServers(int limit);
    Collection<Server> getServers(int start, int limit);
    Optional<Server> getServer(Long id);
    Server updateServer(Server server);
    Boolean deleteServer(Long id);


}
