package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Address;
import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PlayerServiceImp implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImp.class);

    @Override
    public Player findOne(Long id) {
        return playerRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player create(Map<String, String> reqParam) {
        Player player = new Player();
        Address address = new Address();
        updatePlayer(player, address, reqParam);
        return playerRepository.save(player);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player update(Long id, Map<String, String> reqParam) {
        Player player = playerRepository.findOne(id);
        Address address = player.getAddress();
        updatePlayer(player, address, reqParam);
        return playerRepository.save(player);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player delete(Long id) {
        Player player = playerRepository.findOne(id);
        if (player != null) {
            playerRepository.delete(id);
        }
        return player;
    }

    private void updatePlayer(Player player, Address address, Map<String, String> reqParam) {
        for (String key : reqParam.keySet()) {
            String param = reqParam.get(key);
            switch (key) {
                case KEY_FIRSTNAME:
                    player.setFirstname(param);
                    break;
                case KEY_LASTNAME:
                    player.setLastname(param);
                    break;
                case KEY_EMAIL:
                    player.setEmail(param);
                    break;
                case KEY_DESCRIPTION:
                    player.setDescription(param);
                    break;
                case KEY_STREET:
                    address.setStreet(param);
                    break;
                case KEY_CITY:
                    address.setCity(param);
                    break;
                case KEY_STATE:
                    address.setState(param);
                    break;
                case KEY_ZIP:
                    address.setZip(param);
                    break;
                default:
                    logger.info("Wrong param: " + key);
                    break;
            }
        }
    }
}
