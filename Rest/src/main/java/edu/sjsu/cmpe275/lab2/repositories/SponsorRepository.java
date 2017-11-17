package edu.sjsu.cmpe275.lab2.repositories;

import edu.sjsu.cmpe275.lab2.models.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is SponsorRepository
 *
 * @author Kang-Hua Wu
 * @version
 */
@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}
