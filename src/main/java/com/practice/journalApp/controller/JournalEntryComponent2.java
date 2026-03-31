package com.practice.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.practice.journalApp.entity.JournalEntry;
import com.practice.journalApp.repository.JounalEntryRepository;
import com.practice.journalApp.service.JournalEntryService;

//these are the special type of components which handles the http requests.
//we write endpoints here as methods.

//controller tohh  hi lakin kuch special type ki functionality bhi add kar raha h yeh.

@RestController
@RequestMapping("/journal")
public class JournalEntryComponent2 {

	@Autowired
	private JournalEntryService journalEntryService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<JournalEntry> all = journalEntryService.getAll();
		if (all != null && !all.isEmpty()) {
			return new ResponseEntity<>(all, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PostMapping
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
		try {
			journalEntryService.saveEntry(myEntry);
			return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/id/{myid}")
	public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myid) {
		Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
//		if(journalEntry.isPresent()) {
//			return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/id/{myid}")
	public ResponseEntity<?> deleteById(@PathVariable ObjectId myid) {
		journalEntryService.deleteById(myid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PutMapping("id/{myid}")
	public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry) {
		JournalEntry journalEntry = journalEntryService.findById(myid).orElse(null);
		if (journalEntry != null) {
			journalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
					: journalEntry.getTitle());
			journalEntry.setContent(
					newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
							: journalEntry.getContent());
			journalEntryService.saveEntry(journalEntry);
			return new ResponseEntity<>(journalEntry, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
