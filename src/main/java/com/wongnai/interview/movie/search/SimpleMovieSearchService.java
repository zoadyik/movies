package com.wongnai.interview.movie.search;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService extends Movie implements MovieSearchService {
  @Autowired private MovieDataService movieDataService;

  @Override
  public List<Movie> search(String queryText) {
    // TODO: Step 2 => Implement this method by using data from MovieDataService
    // All test in SimpleMovieSearchServiceIntegrationTest must pass.
    // Please do not change @Component annotation on this class

    return movieDataService.fetchAll().stream()
            .filter(movieData -> isContain(movieData.getTitle(), StringUtils.capitalize(queryText)) && !Objects.equals(movieData.getTitle(), StringUtils.capitalize(queryText)))
            .map(
                m -> new Movie(m.getTitle(), m.getCast()))
            .collect(Collectors.toList());
  }

  private boolean isContain(String movie, String queryText) {
	  Pattern re = Pattern.compile("\\b" + queryText +"\\b");
	  Matcher m = re.matcher(movie);
	  return m.find();
  }
}
