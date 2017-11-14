package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Player;

import java.util.Map;

public interface PlayerService {
    public Player findOne(Long id);

    public Player create(Map<String, String> reqParam);

    public Player update(Long id, Map<String, String> reqParam);

    public Player delete(Long id);
}
