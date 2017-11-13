package edu.sjsu.cmpe275.lab2.services;


import edu.sjsu.cmpe275.lab2.models.Sponsor;

import java.util.Map;

public interface SponsorService {
    public Sponsor findOne(Long id);

    public Sponsor create(Map<String, String> reqParam);

    public Sponsor update(Long id, Map<String, String> reqParam);

    public Sponsor delete(Long id);
}
