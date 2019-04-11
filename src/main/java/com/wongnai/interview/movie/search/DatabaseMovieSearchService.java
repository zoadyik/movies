package com.wongnai.interview.movie.search;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.MovieSearchService;

@Component("databaseMovieSearchService")
public class DatabaseMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 3 => Please make all test in DatabaseMovieSearchServiceIntegrationTest run pass.
		// This database search method must use only MovieRepository.findByNameContains(String), you also have to implement
		// MovieDataSynchronizer.forceSync() to load all movie data, using MovieDataService, into MovieRepository.
		// Do not change @Component annotation on this class
		return movieRepository.findByNameContains(StringUtils.capitalize(queryText));
	}
}
