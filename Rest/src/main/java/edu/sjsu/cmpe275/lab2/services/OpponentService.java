package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Player;

public interface OpponentService {
    public Player setupMatch(long id1, long id2);

    public Player deleteMatch(long id1, long id2);
}
