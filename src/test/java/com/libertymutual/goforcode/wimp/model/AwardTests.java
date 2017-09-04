package com.libertymutual.goforcode.wimp.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;

public class AwardTests {
	
	private Award award;

	@Before
	public void setUp() {
		award = new Award();
	}

	@Test
	public void test_setting_award_id_sets_id() {
		award.setId(6l);
		
		assertThat(award.getId()).isEqualTo(6l);
	}
	
	@Test
	public void test_setting_award_title_sets_title() {
		award.setTitle("Best Picture");
		
		assertThat(award.getTitle()).isSameAs("Best Picture");
	}
	
	@Test
	public void test_setting_award_organization_sets_organization() {
		award.setOrganization("Some Org");
		
		assertThat(award.getOrganization()).isSameAs("Some Org");
	}
	
	@Test
	public void test_setting_award_year_sets_year() {
		award.setYear(2005);
		
		assertThat(award.getYear()).isEqualTo(2005);
	}
	
	@Test
	public void test_setting_award_actor_sets_actor() {
		Actor actor = new Actor();
		
		award.setActor(actor);
		
		assertThat(award.getActor()).isSameAs(actor);
	}

}
