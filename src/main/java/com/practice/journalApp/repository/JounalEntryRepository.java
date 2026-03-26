package com.practice.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.journalApp.entity.JournalEntry;

public interface JounalEntryRepository extends MongoRepository<JournalEntry, ObjectId>{

}
