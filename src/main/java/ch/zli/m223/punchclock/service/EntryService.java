package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Entry service.
 */
@Service
public class EntryService {
    private EntryRepository entryRepository;

    /**
     * Instantiates a new Entry service.
     *
     * @param entryRepository the entry repository
     */
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    /**
     * Create entry entry.
     *
     * @param entry the entry
     * @return the entry
     */
    public Entry createEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    /**
     * Delete entry.
     *
     * @param id the id
     */
    public void deleteEntry(long id) { entryRepository.deleteById(id); }

    /**
     * Update entry entry.
     *
     * @param entry the entry
     * @return the entry
     */
    public Entry updateEntry(Entry entry) {
        return entryRepository.save(entry);
    }
}
