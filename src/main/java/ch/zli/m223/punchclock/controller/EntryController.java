package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Entry controller.
 */
@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;

    /**
     * Instantiates a new Entry controller.
     *
     * @param entryService the entry service
     */
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    /**
     * Gets all entries.
     *
     * @return the all entries
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    /**
     * Create entry entry.
     *
     * @param entry the entry
     * @return the entry
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }


    /**
     * Delete entry.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable long id) {
        entryService.deleteEntry(id);
    }

    /**
     * Update entry entry.
     *
     * @param entry the entry
     * @return the entry
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@Valid @RequestBody Entry entry) {
        return entryService.updateEntry(entry);
    }

}
