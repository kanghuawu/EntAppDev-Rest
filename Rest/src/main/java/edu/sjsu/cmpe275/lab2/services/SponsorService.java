package edu.sjsu.cmpe275.lab2.services;


import edu.sjsu.cmpe275.lab2.models.Sponsor;

import java.util.Map;


/**
 * This is an interface for SponsorService.
 *
 * @author Yuntian Shen
 * @version 1.0
 */
public interface SponsorService {
    public Sponsor findOne(Long id);

    public Sponsor create(Map<String, String> reqParam);

    public Sponsor update(Long id, Map<String, String> reqParam);

    public Sponsor delete(Long id);
}
