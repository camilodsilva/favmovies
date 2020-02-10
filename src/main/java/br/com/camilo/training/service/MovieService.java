package br.com.camilo.training.service;

import br.com.camilo.training.model.Movie;
import br.com.camilo.training.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie create(final Movie movie) {
        return this.movieRepository.save(movie);
    }

    public List<Movie> index() {
        return this.movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return this.movieRepository.findById(id).map(found -> {
            return found;
        }).orElse(null);
    }

    public List<Movie> findByDescription(final String description) {
        return this.movieRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public Movie update(Long id, Movie movie) {
        return this.movieRepository.findById(id).map(found -> {
            found.setBanner(movie.getBanner());
            found.setDescription(movie.getDescription());
            found.setDuration(movie.getDuration());
            found.setFavorite(movie.isFavorite());
            found.setFavoriteAllowed(movie.isFavoriteAllowed());
            found.setGenre(movie.getGenre());
            found.setTitle(movie.getTitle());

            return this.movieRepository.save(found);
        }).orElse(null);
    }

    public Movie delete(Long id) {
        return this.movieRepository.findById(id).map(found -> {
            this.movieRepository.delete(found);

            return found;
        }).orElse(null);
    }
}
