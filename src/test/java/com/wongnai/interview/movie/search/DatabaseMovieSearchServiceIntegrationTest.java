package com.wongnai.interview.movie.search;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieTestHelper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseMovieSearchServiceIntegrationTest {
	@Autowired
	private DatabaseMovieSearchService searchService;

	@Test
	public void testFindSingleKeywordContainInTitle() {
		List<Movie> result = searchService.search("Glorious");

		assertGloriousMovieKeyword(result);
	}

	private void assertGloriousMovieKeyword(List<Movie> result) {
		Assert.assertThat(result.size(), Matchers.equalTo(7));
		Assert.assertThat(MovieTestHelper.toMovieNames(result),
				Matchers.hasItems("The Glorious Lady", "The Glorious Fool", "One Glorious Day", "One Glorious Night",
						"Glorious Betsy", "His Glorious Night",
						"Borat! Cultural Learnings of America for Make Benefit Glorious Nation of Kazakhstan"));

		List<String> actors = MovieTestHelper.findMatchedName(result, "His Glorious Night").getActors();
		Assert.assertThat(actors.size(), Matchers.equalTo(2));
		Assert.assertThat(actors, Matchers.hasItems("John Gilbert", "Catherine Dale Owen"));
	}

	@Test
	public void testPartialWordMustMatchMovies() {
		List<Movie> result = searchService.search("Glorio");

		assertGloriousMovieKeyword(result);
	}

	@Test
	public void testFullMovieNameMustMatchMovie() {
		List<Movie> result = searchService.search("The Glorious Lady");

		Assert.assertThat(result.size(), Matchers.equalTo(1));
		Assert.assertThat(MovieTestHelper.toMovieNames(result), Matchers.hasItems("The Glorious Lady"));
	}

	@Test
	public void testNotFoundMovie() {
		List<Movie> result = searchService.search("Lady Glorious");

		Assert.assertThat(result.size(), Matchers.equalTo(0));
	}

	@Test
	public void testFindSingleKeywordContainInTitleWithCaseInsensitive() {
		List<Movie> result = searchService.search("glorious");

		assertGloriousMovieKeyword(result);
	}
}
