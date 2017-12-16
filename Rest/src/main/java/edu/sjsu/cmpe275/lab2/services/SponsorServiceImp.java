package edu.sjsu.cmpe275.lab2.services;

import edu.sjsu.cmpe275.lab2.models.Address;
import edu.sjsu.cmpe275.lab2.models.Sponsor;
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
 * This is an implementation for SponsorService.
 *
 * @author Yuntian Shen
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SponsorServiceImp implements SponsorService {

    private static final Logger logger = LoggerFactory.getLogger(SponsorServiceImp.class);

    @Autowired
    private SponsorRepository sponsorRepository;

    /**
     * This method is for finding sponsor given an id.
     *
     * @param id The id of which player is finding.
     * @return Returns the player if is found. Otherwise, returns null.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sponsor findOne(Long id) {
        return sponsorRepository.findOne(id);
    }

    /**
     * This method is for creating a new sponsor.
     *
     * @param reqParam HashMap of attribute to be updated to newly created sponsor.
     * @return Returns a newly created sponsor.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sponsor create(Map<String, String> reqParam) {
        Sponsor sponsor = new Sponsor();
        Address address = sponsor.getAddress();
        updateSponsor(sponsor, address, reqParam);
        return sponsorRepository.save(sponsor);
    }

    /**
     * This method is for updating a sponsor.
     *
     * @param id The id of the sponsor to be updated.
     * @param reqParam HashMap of attributes to be updated to the sponsor.
     * @return Returns updated sponsor object if successful. Otherwise, returns null if the sponsor is not found.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sponsor update(Long id, Map<String, String> reqParam) {
        Sponsor sponsor = sponsorRepository.findOne(id);
        if (sponsor == null) {
            return null;
        }
        Address address = sponsor.getAddress();
        if (address == null) {
            address = new Address();
            sponsor.setAddress(address);
        }
        updateSponsor(sponsor, address, reqParam);
        return sponsorRepository.save(sponsor);
    }

    /**
     * This method is for deleting a sponsor given an id.
     *
     * @param id The id of which sponsor is being deleted.
     * @return Returns the sponsor being deleted if successful. Otherwise, returns null if sponsor is not found.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sponsor delete(Long id) {
        Sponsor sponsor = sponsorRepository.findOne(id);
        if (sponsor != null) {
            sponsorRepository.delete(id);
        }
        return sponsor;
    }

    /**
     * Helper method for updating sponsor's attributes.
     *
     * @param sponsor Sponsor Object.
     * @param address Address Object.
     * @param reqParam HashMap of attributes to be updated to sponsor or address.
     */
    private void updateSponsor(Sponsor sponsor, Address address, Map<String, String> reqParam) {
        for (String key : reqParam.keySet()) {
            String param = reqParam.get(key);
            switch (key) {
                case KEY_NAME:
                    sponsor.setName(param);
                    break;
                case KEY_DESCRIPTION:
                    sponsor.setDescription(param);
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
