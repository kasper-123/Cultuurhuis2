package be.vdab.cultuurhuis.dto;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


@Component
@SessionScope
public class Mandje {
private Object TreeMap;
TreeMap<Long,Integer> mandje=new TreeMap();



public boolean add(long id , int aantal){
	try {
		mandje.put(id, aantal);
		
		
		return true;
	}catch (VoorstellingNietGevondenException ex){
		throw new VoorstellingNietGevondenException();
	}
}

public Map<Long,Integer> getMandje() {
	
		return mandje;
	}


public boolean clearMandje()throws Exception{
	try {
		mandje.clear();
		return true;
	}catch (Exception ex)
	{throw new Exception();}
}

public boolean setAankoopMandje(long id,int aantal)throws Exception{
	try {
		mandje.replace(id,aantal);
		return true;
	}catch (Exception ex)
	{throw new Exception();}
}

}
