package com.practice.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.journalApp.entity.JournalEntry;
import com.practice.journalApp.repository.JounalEntryRepository;

@Component
public class JournalEntryService {
	@Autowired
	private JounalEntryRepository journalEntryRepo;
	public void saveEntry(JournalEntry journalEntry) {
		journalEntryRepo.save(journalEntry);
	}
	
	public List<JournalEntry> getAll() {
		return journalEntryRepo.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return journalEntryRepo.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		journalEntryRepo.deleteById(id);
	}
	
	public JournalEntry updateEntry(ObjectId id, JournalEntry newEntry) {
		Optional<JournalEntry> optionalEntry = journalEntryRepo.findById(id);
		if (optionalEntry.isPresent()) {
	        JournalEntry existingEntry = optionalEntry.get();

	        // Update title
	        if (newEntry.getTitle() != null && !newEntry.getTitle().equals("")) {
	            existingEntry.setTitle(newEntry.getTitle());
	        }

	        // Update content
	        if (newEntry.getContent() != null && !newEntry.getContent().equals("")) {
	            existingEntry.setContent(newEntry.getContent());
	        }

	        return journalEntryRepo.save(existingEntry);
	    }
		return null;
	}
	

}
