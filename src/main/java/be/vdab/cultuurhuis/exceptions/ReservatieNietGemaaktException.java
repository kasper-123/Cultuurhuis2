package be.vdab.cultuurhuis.exceptions;

public class ReservatieNietGemaaktException extends RuntimeException{
private static final long serialVersionUID = 1L;
public ReservatieNietGemaaktException(Exception fout){
	super(fout);
}
}
