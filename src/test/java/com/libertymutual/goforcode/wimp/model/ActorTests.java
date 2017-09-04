package com.libertymutual.goforcode.wimp.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;

public class ActorTests {

	private Actor actor;
	
	@Before
	public void setUp() {
		actor = new Actor();
	}

	@Test
	public void test_setting_actor_id_sets_id() {
		actor.setId(3l);
		
		assertThat(actor.getId()).isEqualTo(3l);
	}
	
	@Test
	public void test_setting_actor_first_name_sets_first_name() {
		actor.setFirstName("Bobby");
		
		assertThat(actor.getFirstName()).isSameAs("Bobby");
	}
	
	@Test
	public void test_setting_actor_last_name_sets_last_name() {
		actor.setLastName("Smith");
		
		assertThat(actor.getLastName()).isSameAs("Smith");
	}
	
	@Test
	public void test_setting_actor_birthdate_sets_birthdate() {
		@SuppressWarnings("deprecation")
		Date date = new Date(2000, 05, 20, 5, 5, 5);
		actor.setBirthDate(date);
		
		assertThat(actor.getBirthDate()).isSameAs(date);
	}
	
	@Test
	public void test_setting_active_since_year_sets_year() {
		actor.setActiveSinceYear(2010l);
		
		assertThat(actor.getActiveSinceYear()).isEqualTo(2010l);
	}
	
	@Test
	public void test_setting_movies_sets_list_of_movies() {
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		
		actor.setMovies(movies);
		
		assertThat(actor.getMovies()).isSameAs(movies);
		assertThat(actor.getMovies().size()).isEqualTo(2);
	}
	
	@Test
	public void test_setting_awards_sets_list_of_awards() {
		List<Award> awards = new ArrayList<Award>();
		awards.add(new Award());
		awards.add(new Award());
		awards.add(new Award());
		
		actor.setAwards(awards);
		
		assertThat(actor.getAwards()).isSameAs(awards);
		assertThat(actor.getAwards().size()).isEqualTo(3);		
	}
	
	@Test
	public void test_adding_an_award_adds_award_to_list() {
		List<Award> awards = new ArrayList<Award>();
		awards.add(new Award());
		actor.setAwards(awards);
		Award award = new Award();
		
		actor.addAward(award);
		
		assertThat(actor.getAwards().get(1)).isSameAs(award);
		assertThat(actor.getAwards().size()).isEqualTo(2);
	}
	
	@Test
	public void test_adding_an_award_to_null_list() {		
		actor.addAward(new Award());
		
		assertThat(actor.getAwards().size()).isEqualTo(1);
	}

}
