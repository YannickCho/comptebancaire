<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compte bancaire</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body>
<table border = "2" style="width : 50%">
<tr><td class="titre" style="width : 25%">Gestion de comptes bancaires avec JSP</td></tr>
</table>
<table border = "2" style="width : 50%">
<tr><td style="width : 27%">
<form action="gestion" method="get">
<table>
<tr><td>Titulaire :</td> 
<td><input type="text" name="titulaire"/></td></tr>
<tr><td>Num�ro : </td>
<td><input type="text" name="numero"/></td></tr>
<tr><td>Solde, Cr�dit ou D�bit : </td> 
<td><input type="text" name="scd"/></td></tr>
</table>
<input type="radio" id="creer" name="actionCompte" value="creer" checked="checked">
<label for="creer">Cr�er compte</label></br>
<input type="radio" id="crediter" name="actionCompte" value="crediter">
<label for="crediter">Cr�diter compte</label></br>
<input type="radio" id="debiter" name="actionCompte" value="debiter">
<label for="debiter">D�biter compte</label></br>

<button type="submit" name="raz" value = "non">Ex�cuter</button>
<button type="submit" name="raz" value = "oui">Remise � z�ro</button>
</form>
</td>
<td style="width : 25%"><img src="logo-banques.png"></td>
</tr>
</table>
<table border = "2" style="width : 50%">
<tr><td class="titre">Traitements</td></tr>
</table>
<c:set var="cc" value="${compte }" scope="session"/>
<c:choose>
<c:when test="${traitement == 'creation' }">
Compte cr�� :</br>
<c:out value="Titulaire : ${cc.titulaire }"/></br>
 <c:out value="Num�ro : ${cc.numero }"/></br>
 <c:out value="Solde : ${cc.solde }"/></br>
</c:when>
<c:when test="${traitement == 'changement' }">
<c:out value="Nouveau solde : ${cc.solde }"/></br>
</c:when>
<c:when test="${traitement == 'erreur' }">
<c:out value=" ${message }"/></br>
</c:when>
</c:choose>

</body>
</html>