package com.packtpub.jdk.core.eleven.common.module.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result {
    private final String workerName;
    private final int sleepInSec, result;
}
