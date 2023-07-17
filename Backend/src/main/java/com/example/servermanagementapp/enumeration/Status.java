package com.example.servermanagementapp.enumeration;

import lombok.Getter;

@Getter
public enum Status
{
    SERVER_RUNNING("SERVER_RUNNING"),
    SERVER_DOWN("SERVER_DOWN");
    private String status;

    Status(String status)
    {
        this.status = status;
    }
}
