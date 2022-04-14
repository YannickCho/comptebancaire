package org.eclipse.model;

import java.io.Serializable;

public class Compte implements Serializable{
	private String titulaire;
	private int numero;
	private double solde;
	
	
	public Compte() {}


	public String getTitulaire() {
		return titulaire;
	}


	public void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public double getSolde() {
		return solde;
	}


	public void setSolde(double solde) {
		this.solde = solde;
	}


	@Override
	public String toString() {
		return "titulaire : " + titulaire + "   numero : " + numero + "   solde : " + solde;
	}
	
	
}
