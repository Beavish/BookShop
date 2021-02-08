package com.kieran.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kieran.app.exception.ResourceNotFoundException;
import com.kieran.app.model.Book;
import com.kieran.app.repository.BookRepository;

//import javax.validation.Valid;


@RestController @CrossOrigin
@RequestMapping("/api/v1")
public class BookController {
	
	@Autowired
	private BookRepository bookrepo;
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookrepo.findAll();
		
	}
	
	  @GetMapping("/books/{id}")
	    public ResponseEntity<Book> getEmployeeById(@PathVariable(value = "id") Long bookId)
	        throws ResourceNotFoundException {
	        Book book = bookrepo.findById(bookId)
	          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + bookId));
	        return ResponseEntity.ok().body(book);
	    }
	    
	  @PostMapping("/books")
	    public Book createEmployee(@RequestBody Book book) {
	        return bookrepo.save(book);
	    }
	
	//
	   @PutMapping("/books/{id}")
	    public ResponseEntity<Book> updateEmployee(@PathVariable(value = "id") Long bookId,
	          @RequestBody Book employeeDetails) throws ResourceNotFoundException {
	        Book book = bookrepo.findById(bookId)
	        .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));

	        final Book updatedBook = bookrepo.save(book);
	        return ResponseEntity.ok(updatedBook);
	    }
	   //
	   @DeleteMapping("/books/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long bookId)
	         throws ResourceNotFoundException {
	        Book book = bookrepo.findById(bookId)
	       .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));

	        bookrepo.delete(book);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	
	
	
// end of class
}
