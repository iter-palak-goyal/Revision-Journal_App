package com.practice.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
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

//these are the special type of components which handles the http requests.
//we write endpoints here as methods.

//controller tohh  hi lakin kuch special type ki functionality bhi add kar raha h yeh.

@RestController
@RequestMapping("/_journal")
public class JournalEntryComponent {
	
	private Map<ObjectId, JournalEntry> journalEntries = new HashMap();
	
	@GetMapping
	public List<JournalEntry> getAll() {
		return new ArrayList<>(journalEntries.values());
	}
	
	@PostMapping
	public boolean createEntry(@RequestBody JournalEntry myEntry) {
		journalEntries.put(myEntry.getId(), myEntry);
		return true;
	}
	
	@GetMapping("/id/{myid}")
	public JournalEntry getEntryById(@PathVariable ObjectId myid) {
		return journalEntries.get(myid);
	}
	
	@DeleteMapping("/id/{myid}")
	public JournalEntry deleteEntryById(@PathVariable ObjectId myid) {
		return journalEntries.remove(myid);
	}
	
	@PutMapping("id/{myid}")
	public JournalEntry updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry myEntry) {
		return journalEntries.put(myEntry.getId(), myEntry);
	}
}
