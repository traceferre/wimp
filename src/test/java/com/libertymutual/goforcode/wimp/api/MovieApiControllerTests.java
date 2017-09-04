package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {
	
	private MovieApiController controller;
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;

	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);
	}

	@Test
	public void test_get_all_returns_all_movies_from_repo() {
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		when(movieRepo.findAll()).thenReturn(movies);
		
		List<Movie> actualMovies = controller.getAll();
		
		assertThat(actualMovies.size()).isEqualTo(2);
		assertThat(actualMovies.get(1)).isSameAs(movies.get(1));
		verify(movieRepo).findAll();
	}
	
	@Test
	public void test_get_one_movie_returns_one_movie_from_repo() throws StuffNotFoundException {
		Movie movie = new Movie();
		when(movieRepo.findOne(2l)).thenReturn(movie);
		
		Movie resultingMovie = controller.getOne(2l);
		
		assertThat(resultingMovie).isSameAs(movie);
		verify(movieRepo).findOne(2l);
	}
	
	@Test
	public void test_get_one_movie_returns_exception_if_no_movie_found() throws StuffNotFoundException {
		try {
			controller.getOne(5l);
			fail("The controller did not throw StuffNotFoundException.");
		} catch (StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_delete_movie_from_movie_repo() {
		controller.delete(3l);
		
		verify(movieRepo).delete(3l);
	}
	
	@Test
	public void test_create_movie_creates_movie_in_repo() {
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		Movie resultingMovie = controller.create(movie);
		
		assertThat(resultingMovie).isSameAs(movie);
		verify(movieRepo).save(movie);
	}
	
	@Test
	public void test_associating_an_actor_with_movie() {
		Actor actor = new Actor();
		actor.setId(5l);
		Movie movie = new Movie();
		when(movieRepo.findOne(2l)).thenReturn(movie);
		when(actorRepo.findOne(5l)).thenReturn(actor);
		
		movie = controller.associateAnActor(2l, actor);
		
		assertThat(movie.getActors().get(0)).isSameAs(actor);
		verify(movieRepo).save(movie);
		verify(movieRepo).findOne(2l);
		verify(actorRepo).findOne(5l);
	}

	@Test
	public void test_updating_a_movie_updates_movie() {
		Movie movie = new Movie();
		movie.setId(3l);
		when(movieRepo.save(movie)).thenReturn(movie);
		
		Movie resultingMovie = controller.update(movie, 3l);
		
		assertThat(resultingMovie).isSameAs(movie);
		assertThat(resultingMovie.getId()).isEqualTo(movie.getId());
		verify(movieRepo).save(movie);
	}
}
