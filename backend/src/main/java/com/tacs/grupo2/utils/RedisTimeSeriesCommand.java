package com.tacs.grupo2.utils;

import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.util.SafeEncoder;

public enum RedisTimeSeriesCommand implements ProtocolCommand {
    TS_CREATE("TS.CREATE"),
    TS_ADD("TS.ADD"),
    TS_REVRANGE("TS.REVRANGE");

    private final byte[] raw;

    RedisTimeSeriesCommand(String command) {
        raw = SafeEncoder.encode(command);
    }

    @Override
    public byte[] getRaw() {
        return raw;
    }
}