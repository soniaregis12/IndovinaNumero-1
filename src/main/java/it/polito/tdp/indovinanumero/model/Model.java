package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	public Model() {
		
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int tentativoProvato) {
		
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata");
		}
		
		// Controllo imput
		if(!tentativoValido(tentativoProvato)) {
			throw new InvalidParameterException("Devi inserire un valore che non hai ancora provato tra 1 e " + NMAX);
		}
		
		
		// Se arriviamo qui allora il tentativo è valido, quindi possiamo provarlo
		this.tentativiFatti ++;
		this.tentativi.add(tentativoProvato);
		
		if(this.tentativiFatti == TMAX) {
			this.inGioco = false;
		}
		
		if(tentativoProvato == this.segreto) {
			this.inGioco = false;
			return 0;
		}
		
		// Se arriviamo qui allora non ho indovinato
		if(tentativoProvato < this.segreto) {
			return -1;
		}else {
			return 1;
		}
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo < 1 || tentativo > NMAX) {
			return false;
		}else
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public void setSegreto(int segreto) {
		this.segreto = segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public void setTentativiFatti(int tentativiFatti) {
		this.tentativiFatti = tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}

	
}
