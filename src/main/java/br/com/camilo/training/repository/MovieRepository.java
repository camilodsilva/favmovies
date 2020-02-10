package br.com.camilo.training.repository;

import br.com.camilo.training.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByDescriptionContainingIgnoreCase(final String description);

}
