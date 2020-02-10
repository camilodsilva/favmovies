package br.com.camilo.training.controller;

import br.com.camilo.training.service.MovieService;
import br.com.camilo.training.error.ResourceNotFoundException;
import br.com.camilo.training.model.Movie;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/apis/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody final Movie movie) {
        Movie created = this.movieService.create(movie);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> index() {
        List<Movie> movies = this.movieService.index();

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) {
        Movie found = this.movieService.findById(id);

        this.checkMovieExists(id, found);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping(path = "/findByDescription/{description}")
    public ResponseEntity<?> findByDescription(@PathVariable final String description) {
        List<Movie> found = this.movieService.findByDescription(description);

        // this.checkMovieExists(id, found);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id, @RequestBody final Movie movie) {
        Movie updated = this.movieService.update(id, movie);

        this.checkMovieExists(id, updated);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        Movie delete = this.movieService.delete(id);

        this.checkMovieExists(id, delete);

        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    private void checkMovieExists(final Long id, final Movie movie) {
        if (movie == null) {
            throw new ResourceNotFoundException(String.format("Movie Not found: %d",id));
        }
    }
}
