package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Entry repository.
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
