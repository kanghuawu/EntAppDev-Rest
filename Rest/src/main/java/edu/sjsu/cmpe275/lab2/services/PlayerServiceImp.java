package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Address;
import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.models.Sponsor;
import edu.sjsu.cmpe275.lab2.repositories.PlayerRepository;
import edu.sjsu.cmpe275.lab2.repositories.SponsorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

/**
 * This is an implementation for PlayerService.
 *
 * @author Chenhua Zhu
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PlayerServiceImp implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImp.class);
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private SponsorRepository sponsorRepository;

    /**
     * This method is for finding player given an id.
     *
     * @param id The id of the player to be searched for.
     * @return Returns the player if found. Otherwise, return null.
     */
    @Override
    public Player findOne(Long id) {
        return playerRepository.findOne(id);
    }

    /**
     * This method is for creating player given HashMap of parameters.
     *
     * @param reqParam The attributes to be attached to the newly created player.
     * @return Returns a player if successful. Otherwise, returns null if email has been used or sponsor is not found.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player create(Map<String, String> reqParam) {
        Player player = playerRepository.findByEmail(reqParam.get(KEY_EMAIL));
        if (player != null) {
            return null;
        }
        player = new Player();
        Address address = player.getAddress();
        Sponsor sponsor = null;
        if (reqParam.containsKey(KEY_SPONSOR)) {
            sponsor = sponsorRepository.findOne(Long.parseLong(reqParam.get(KEY_SPONSOR)));
            if (sponsor == null) {
                return null;
            }
        }
        updatePlayer(player, address, sponsor, reqParam);
        return playerRepository.save(player);
    }

    /**
     * This method is for updating a player.
     *
     * @param id The id of which player is being updated.
     * @param reqParam The attributes in HashMap to be updated.
     * @return Returns updated player if successful. Otherwise, returns null if player is not found or sponsor
     * is not found.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player update(Long id, Map<String, String> reqParam) {
        Player player = playerRepository.findOne(id);
        if (player == null) {
            return null;
        }
        Address address = player.getAddress();
        if (reqParam.containsKey(KEY_SPONSOR)) {
            Long sponsorId = Long.parseLong(reqParam.get(KEY_SPONSOR));
            if (!sponsorRepository.exists(sponsorId)) {
                return null;
            }
            Sponsor sponsor = sponsorRepository.findOne(Long.parseLong(reqParam.get(KEY_SPONSOR)));
            updatePlayer(player, address, sponsor, reqParam);
        }
        updatePlayer(player, address, null, reqParam);
        return playerRepository.save(player);

    }

    /**
     * This method is for deleting a player given an id.
     *
     * @param id The id of which player is being deleted.
     * @return Returns the deleted player if successful. Otherwise returns null if the player is not found.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Player delete(Long id) {
        Player player = playerRepository.findOne(id);
        if (player != null) {
            for (Player oppo : player.getOpponents()) {
                oppo.removeOpponent(player);
                playerRepository.save(oppo);
            }
            playerRepository.delete(id);
        }
        return player;
    }

    /**
     * The helper method for update player.
     *
     * @param player Player Object.
     * @param address Address Object.
     * @param sponsor Sponsor Object.
     * @param reqParam HashMap of attributes going to be updated to player, address, or sponsor.
     */
    private void updatePlayer(Player player, Address address, Sponsor sponsor, Map<String, String> reqParam) {
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
                case KEY_SPONSOR:
                    player.setSponsor(sponsor);
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
