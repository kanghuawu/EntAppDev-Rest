package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OpponentServiceImp implements OpponentService {

    @Autowired
    PlayerRepository playerRepository;

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
