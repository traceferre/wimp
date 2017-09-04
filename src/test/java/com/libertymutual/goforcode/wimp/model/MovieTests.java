package com.libertymutual.goforcode.wimp.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;

public class MovieTests {
	
	private Movie movie;

	@Before
	public void setUp() {
		movie = new Movie();
	}

	@Test
	public void test_setting_movie_id_sets_id() {
		movie.setId(2l);
		
		assertThat(movie.getId()).isEqualTo(2l);
	}
	
	@Test
	public void test_setting_movie_title_name_sets_title() {
		movie.setTitle("Forest Gump");
		
		assertThat(movie.getTitle()).isSameAs("Forest Gump");
	}
	
	@Test
	public void test_setting_movie_release_date_sets_date() {
		@SuppressWarnings("deprecation")
		Date date = new Date(1990, 05, 20, 10, 5, 20);
		movie.setReleaseDate(date);
		
		assertThat(movie.getReleaseDate()).isSameAs(date);
	}
	
	@Test
	public void test_setting_movie_budget_sets_budget() {
		movie.setBudget(5000000l);
		
		assertThat(movie.getBudget()).isEqualTo(5000000l);
	}
	
	@Test
	public void test_setting_movie_distributor_sets_distributor() {
		movie.setDistributor("Paramount");
		
		assertThat(movie.getDistributor()).isSameAs("Paramount");
	}
	
	@Test
	public void test_setting_movie_actors_sets_list_of_actors() {
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		
		movie.setActors(actors);
		
		assertThat(movie.getActors()).isSameAs(actors);
		assertThat(movie.getActors().size()).isEqualTo(2);	
	}
	
	@Test
	public void test_adding_actor_adds_actor_to_list_of_actors() {
		test_setting_movie_actors_sets_list_of_actors();
		Actor actor = new Actor();
		
		movie.addActor(actor);
		
		assertThat(movie.getActors().size()).isEqualTo(3);
		assertThat(movie.getActors().get(2)).isSameAs(actor);
	}

	@Test
	public void test_adding_actor_to_null_list_creates_list() {
		Actor actor = new Actor();
		
		movie.addActor(actor);
		
		assertThat(movie.getActors().get(0)).isSameAs(actor);
		assertThat(movie.getActors().size()).isEqualTo(1);
	}
}
