package com.wongnai.interview.movie.sync;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MovieDataSynchronizer {
  @Autowired private MovieDataService movieDataService;

  @Autowired private MovieRepository movieRepository;

  @Transactional
  public void forceSync() {
    // TODO: implement this to sync movie into repository
    List<Movie> movieList =
        movieDataService.fetchAll().stream()
            .map(m -> new Movie(m.getTitle(), m.getCast()))
            .collect(Collectors.toList());
    movieRepository.saveAll(movieList);
  }
}
