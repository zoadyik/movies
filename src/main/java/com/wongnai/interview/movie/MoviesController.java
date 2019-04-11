package com.wongnai.interview.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {
	/**
	 * Inject movie search service and use to handle search request.
	 * <p>
	 * You can specify an implementation using @Qualifier("beanName"), for example:
	 *
	 * <pre>
	 * {@literal @}Qualifier("databaseMovieSearchService")
	 * {@literal @}Qualifier("simpleMovieSearchService")
	 * {@literal @}Qualifier("invertedIndexMovieSearchService")
	 * </pre>
	 */
	@Autowired
	@Qualifier("invertedIndexMovieSearchService")
	private MovieSearchService movieSearchService;

	@RequestMapping(method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World!";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Movie> searchTitleWithKeyword(@RequestParam("q") String keyword) {
		return movieSearchService.search(keyword);
	}
}
