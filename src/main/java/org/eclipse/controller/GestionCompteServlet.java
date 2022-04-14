package org.eclipse.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.model.Compte;


/**
 * Servlet implementation class GestionCompteServlet
 */
@WebServlet("/gestion")
public class GestionCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionCompteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<Compte> listeC = new ArrayList<>();
		Compte compte= new Compte();
		String raz = request.getParameter("raz");


		if (!raz.equals("oui")) {
			double scd = Double.parseDouble(request.getParameter("scd"));
			String actionCompte = request.getParameter("actionCompte");
			int numero = Integer.parseInt(request.getParameter("numero"));
			boolean numExiste = false;
			try {
				FileInputStream fis = new FileInputStream("c:\\test\\compte.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);

				listeC = (ArrayList<Compte>) ois.readObject();

				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			switch(actionCompte) {
			case "creer" :
				String titulaire = request.getParameter("titulaire");
				if (!numeroDansListe(numero, listeC)) {
					compte.setTitulaire(titulaire);
					compte.setNumero(numero);
					compte.setSolde(scd);
					listeC.add(compte);
					request.setAttribute("traitement", "creation");
				}
				else {
					request.setAttribute("traitement", "erreur");
					request.setAttribute("message", "Ce numéro est déjà dans la liste de comptes");
				}
				break;
			case "crediter" :
				
				for(int i = 0; i < listeC.size(); i++) {
					compte = listeC.get(i);
					if (compte.getNumero() == numero) {
						numExiste = true;
						break;
					}
				}
				if (numExiste) {
					crediter(compte, scd);
					request.setAttribute("traitement", "changement");
				}
				else {
					request.setAttribute("traitement", "erreur");
					request.setAttribute("message", "Ce numéro n'existe pas");
				}
				break;
			case "debiter" :
				
				for(int i = 0; i < listeC.size(); i++) {
					compte = listeC.get(i);
					if (compte.getNumero() == numero) {
						numExiste = true;
						break;
					}
				}
				if (numExiste) {
					debiter(compte, scd);
					request.setAttribute("traitement", "changement");
				}
				else {
					request.setAttribute("traitement", "erreur");
					request.setAttribute("message", "Ce numéro n'existe pas");
				}
				break;
			}
			
			try {
				FileOutputStream fs = new FileOutputStream("c:\\test\\compte.txt");
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(listeC);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("compte", compte);
		}



		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public double crediter(Compte cc, double somme) {
		double newSolde = cc.getSolde() + somme;
		cc.setSolde(newSolde);		
		return newSolde;
	}

	public double debiter(Compte cc, double somme) {
		double newSolde = cc.getSolde() - somme;
		cc.setSolde(newSolde);		
		return newSolde;
	}

	public boolean numeroDansListe(int numero, ArrayList<Compte> listeC) {
		boolean reponse = false;
		Compte compte;

		for(int i = 0; i  < listeC.size(); i++) {
			compte = listeC.get(i);
			if (compte.getNumero() == numero) {
				reponse = true;
				break;
			}

		}

		return reponse;
	}

}
