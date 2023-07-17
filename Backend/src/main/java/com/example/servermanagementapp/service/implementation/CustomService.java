package com.example.servermanagementapp.service.implementation;

import com.example.servermanagementapp.enumeration.Status;
import com.example.servermanagementapp.model.Server;
import com.example.servermanagementapp.repository.ServerRepository;
import com.example.servermanagementapp.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomService implements ServerService
{
    private final ServerRepository serverRepository;
    @Override
    public Server saveServer(Server server)
    {
        server.setImageURL(createImageUrl()); //TODO: Something, idk how this is supposed to work
        return serverRepository.save(server);
    }

    private String createImageUrl()
    {
        String[] imageNames = { "1.png", "2.png", "3.png", "4.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException
    {
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_RUNNING : Status.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> getServers(int limit)
    {
        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Collection<Server> getServers(int start, int limit)
    {
        return serverRepository.findAll(PageRequest.of(start,limit)).toList();
    }

    @Override
    public Optional<Server> getServer(Long id)
    {
        return serverRepository.findById(id);
    }

    @Override
    public Server updateServer(Server server)
    {
        return serverRepository.save(server);
    }

    @Override
    public Boolean deleteServer(Long id)
    {
        serverRepository.deleteById(id);
        return TRUE;
    }
}
