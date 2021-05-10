package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.Mandje;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("mandje")
public class MandjeController implements Serializable {
	private final VoorstellingRepository voorstellingRepository;
	Mandje mandje;
		MandjeController(VoorstellingRepository voorstellingRepository){
			this.voorstellingRepository = voorstellingRepository;
			this.mandje=new Mandje();
		
		}
@GetMapping
public ModelAndView getMandje() throws Exception {
			Map<Voorstelling,Integer> Set=new TreeMap<>();
			for (var e:mandje.getMandje().entrySet()) {
				try {
			Set.put(voorstellingRepository.findById(e.getKey()).get(), e.getValue());
				}catch (Exception ex){
					throw new Exception(ex);
				}
			}
			return new ModelAndView("mandje","mandje",mandje.getMandje());
}
@PostMapping("/{id}/{aantal}")
boolean add(@PathVariable long id ,@PathVariable int aantal){
			try {
				mandje.add(id, aantal);
				return true;
			}catch (VoorstellingNietGevondenException ex){
				throw new VoorstellingNietGevondenException();
			}
}


@GetMapping("/{id}/{aantal}")
boolean setMandje(@PathVariable long id ,@PathVariable int aantal) throws Exception{
	try {
	if(mandje.getMandje().containsKey(id)==true){
			Mandje nieuw= new Mandje();
		nieuw.add(1,2);
			nieuw.setAankoopMandje(1,2);
			return true;
		}
	}catch (VoorstellingNietGevondenException ex){
		throw new VoorstellingNietGevondenException();
	} catch (Exception exception) {
		exception.printStackTrace();
	}
return false;}
		}
