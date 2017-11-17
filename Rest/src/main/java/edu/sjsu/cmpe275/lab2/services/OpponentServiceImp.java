package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the implementation of OpponentService. Every method is transactional.
 *
 * @author Kang-Hua Wu
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OpponentServiceImp implements OpponentService {

    @Autowired
    PlayerRepository playerRepository;

    /**
     * This method is for setting up a match between two players e.g. setting up opponent relationship.
     *
     * @param id1 The id of a player for setting up match.
     * @param id2 The id of another player for setting up match.
     * @return Returns the id1 player after set successful. Returns null if either player is not found.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player setupMatch(long id1, long id2) {
        Player player1 = playerRepository.findOne(id1);
        Player player2 = playerRepository.findOne(id2);
        if (player1 == null || player2 == null) {
            return null;
        }
        player1.addOpponent(player2);
        player2.addOpponent(player1);
        playerRepository.save(player2);
        return playerRepository.save(player1);
    }

    /**
     * This method is for deleting a match between two players e.g. deleting opponent relationship.
     *
     * @param id1 The id of a player for deleting a match.
     * @param id2 The id of another player for deleting a match.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player deleteMatch(long id1, long id2) {
        Player player1 = playerRepository.findOne(id1);
        Player player2 = playerRepository.findOne(id2);
        if (player1 == null ||
                player2 == null ||
                !player1.hasOpponent(player2)) {
            return null;
        }
        player1.removeOpponent(player2);
        player2.removeOpponent(player1);
        playerRepository.save(player2);
        return playerRepository.save(player1);
    }
}
