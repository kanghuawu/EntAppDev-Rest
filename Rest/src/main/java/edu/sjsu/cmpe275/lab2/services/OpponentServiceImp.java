package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<Player> player1Opponent = player1.getOpponents();
        List<Player> player2Opponent = player2.getOpponents();
        if (player1Opponent.contains(player2)) {
            return null;
        }
        player1Opponent.add(player2);
        player2Opponent.add(player1);
        playerRepository.save(player1);
        playerRepository.save(player2);
        return player1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player deleteMatch(long id1, long id2) {
        Player player1 = playerRepository.findOne(id1);
        Player player2 = playerRepository.findOne(id2);
        List<Player> player1Opponent = player1.getOpponents();
        List<Player> player2Opponent = player2.getOpponents();
        if (player1 == null || player2 == null || !player1Opponent.contains(player2)) {
            return null;
        }
        player1Opponent.remove(player2);
        player2Opponent.remove(player1);
        playerRepository.save(player1);
        playerRepository.save(player2);
        return player1;
    }
}
