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

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SponsorServiceImp implements SponsorService {

    private static final Logger logger = LoggerFactory.getLogger(SponsorServiceImp.class);

    @Autowired
    private SponsorRepository sponsorRepository;

    @Override
    public Sponsor findOne(Long id) {
        return sponsorRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sponsor create(Map<String, String> reqParam) {
        Sponsor sponsor = new Sponsor();
        Address address = sponsor.getAddress();
        updateSponsor(sponsor, address, reqParam);
        return sponsorRepository.save(sponsor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sponsor update(Long id, Map<String, String> reqParam) {
        Sponsor sponsor = sponsorRepository.findOne(id);
        Address address = sponsor.getAddress();
        updateSponsor(sponsor, address, reqParam);
        return sponsorRepository.save(sponsor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Sponsor delete(Long id) {
        Sponsor sponsor = sponsorRepository.findOne(id);
        if (sponsor != null) {
            sponsorRepository.delete(id);
        }
        return sponsor;
    }

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
