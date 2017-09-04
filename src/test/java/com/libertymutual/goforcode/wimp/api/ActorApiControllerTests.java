package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

public class ActorApiControllerTests {
	
	private ActorApiController controller;
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;

	@Before
	public void setUp() throws Exception {
		awardRepo = mock(AwardRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new ActorApiController(actorRepo, awardRepo);
	}

	@Test
	public void test_get_all_returns_all_actors_from_repo() {
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		actors.add(new Actor());
		when(actorRepo.findAll()).thenReturn(actors);
		
		List<Actor> actualActors = controller.getAll();
		
		assertThat(actualActors.size()).isEqualTo(3);
		assertThat(actualActors.get(2)).isSameAs(actors.get(2));
		verify(actorRepo).findAll();		
	}
	
	@Test
	public void test_get_one_returns_one_actor_from_repo() throws StuffNotFoundException {
		Actor actor = new Actor();
		when(actorRepo.findOne(3l)).thenReturn(actor);
		
		Actor resultingActor = controller.getOne(3l);
		
		assertThat(resultingActor).isSameAs(actor);
		verify(actorRepo).findOne(3l);
	}
	
	@Test
	public void test_get_one_throws_exception_when_no_actor_is_returned() throws StuffNotFoundException {
		try {
			controller.getOne(3l);
			fail("The controller did not throw StuffNotFoundException.");
		} catch (StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_delete_actor_from_actor_repo() {
		controller.delete(4l);
		
		verify(actorRepo).delete(4l);
	}
	
	@Test
	public void test_create_actor_creates_actor_in_repo() {
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		Actor resultingActor = controller.create(actor);
		
		assertThat(resultingActor).isSameAs(actor);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_associating_an_award_with_an_actor() {
		List<Award> awards = new ArrayList<Award>();
		Award award = new Award();
		awards.add(award);
		Actor actorWithAward = new Actor();
		actorWithAward.setAwards(awards);
		when(actorRepo.findOne(3l)).thenReturn(actorWithAward);
		
		Actor actualActor = controller.associateAnAward(3l, award);
		
		assertThat(actualActor.getAwards()).isSameAs(awards);
		assertThat(actualActor).isSameAs(actorWithAward);
		verify(awardRepo).save(award);
		verify(actorRepo, times(2)).findOne(3l);
	}
	
	@Test
	public void test_updating_an_actor_in_repo() {
		Actor actor = new Actor();
		actor.setId(3l);
		when(actorRepo.save(actor)).thenReturn(actor);
		
		Actor resultingActor = controller.update(actor, 3l);
		
		assertThat(resultingActor).isSameAs(actor);
		assertThat(resultingActor.getId()).isEqualTo(actor.getId());
		verify(actorRepo).save(actor);
	}

}
