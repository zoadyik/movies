package com.wongnai.interview.movie.external;

import java.util.Arrays;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieDataServiceImplIntegrationTest {
	@Autowired
	private MovieDataServiceImpl movieDataService;

	@Test
	public void testFetchAll() {
		MoviesResponse result = movieDataService.fetchAll();
		Assert.assertThat(result.size(), Matchers.equalTo(28795));
	}

	@Test
	public void testMappingDataCorrectly() {
		MoviesResponse result = movieDataService.fetchAll();
		Optional<MovieData> afterDark = result.stream()
				.filter(m -> m.getTitle().equals("One Night in Rome"))
				.findFirst();

		Assert.assertThat(afterDark.isPresent(), Matchers.equalTo(true));
		Assert.assertThat(afterDark.get().getYear(), Matchers.equalTo(1924));
		Assert.assertThat(afterDark.get().getGenres(), Matchers.equalTo(Arrays.asList("Romance")));
		Assert.assertThat(afterDark.get().getCast(), Matchers.equalTo(Arrays.asList("Laurette Taylor", "Tom Moore")));
	}
}
