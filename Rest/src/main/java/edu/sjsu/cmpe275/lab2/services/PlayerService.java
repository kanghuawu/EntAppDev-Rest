package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Player;

import java.util.Map;


/**
 * This is an interface for PlayerService.
 *
 * @author Chenhua Zhu
 * @version 1.0
 */
public interface PlayerService {
    public Player findOne(Long id);

    public Player create(Map<String, String> reqParam);

    public Player update(Long id, Map<String, String> reqParam);

    public Player delete(Long id);
}
