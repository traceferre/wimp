package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Actor;
//import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

@RestController
@RequestMapping ("/api/actors")
public class ActorApiController {
	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	
	public ActorApiController(ActorRepository actorRepo, AwardRepository awardRepo) {
		this.actorRepo = actorRepo;
		this.awardRepo = awardRepo;
		
		Actor actor = new Actor();
		actor.setFirstName("Bill");
		actor.setLastName("Murray");
		actorRepo.save(actor);
		
		actor = new Actor();
		actor.setFirstName("Dan");
		actor.setLastName("Aykroyd");
		actorRepo.save(actor);
	}
	
	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if (actor == null) throw new StuffNotFoundException();
		
		
		
//		ActorWithMovies actorWM = new ActorWithMovies();
//		actorWM.setActiveSinceYear(actor.getActiveSinceYear());
//		actorWM.setBirthDate(actor.getBirthDate());
//		actorWM.setFirstName(actor.getFirstName());
//		actorWM.setMovies(actor.getMovies());
//		actorWM.setLastName(actor.getLastName());
//		return actorWM;
		
		return actor;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		try {
			actorRepo.delete(id);
		} catch (EmptyResultDataAccessException erdae) {
		}
	}
	
	@PostMapping("")
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	@PostMapping("/{actorId}/awards")
	public Actor associateAnAward(@PathVariable long actorId, @RequestBody Award award) {
		Actor actor = actorRepo.findOne(actorId);
		
//		Award tempAward = new Award();		
//		tempAward.setTitle(award.getTitle());
//		tempAward.setOrganization(award.getOrganization());
//		tempAward.setYear(award.getYear());
		award.setActor(actor);
		awardRepo.save(award);
		
		
		
		return actorRepo.findOne(actorId);
	}
	
	@PutMapping("/{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
}
