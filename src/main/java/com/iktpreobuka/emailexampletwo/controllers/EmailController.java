package com.iktpreobuka.emailexampletwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.emailexampletwo.models.EmailObject;
import com.iktpreobuka.emailexampletwo.services.EmailService;

@RestController
@RequestMapping("/")
public class EmailController {

	@Autowired
	private EmailService emailService;

	private static String PATH_TO_ATTACHMENT = "C:/home/montekrista/Desktop/gospode.jpg";

	// testiramo slanje poruke
	@RequestMapping(method = RequestMethod.POST, path = "/simpleEmail")
	public String sendSimpleEmail(@RequestBody EmailObject emailObject) {
		// ako su prazna polja vratićemo NULL
		if (emailObject == null || emailObject.getTo() == null || emailObject.getText() == null)
			return null;
		emailService.sendSimpleEmailMessage(emailObject);
		return "Email message sent successfully!";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/emailWithAttachment")
	public String sendMessageWithAttachment(@RequestBody EmailObject object) throws Exception {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendMessageWithAttachment(object, PATH_TO_ATTACHMENT);
		return "Email message sent successfully!";
	}

}

/*
• 1.1 omogućiti upload fajla sa listom korisnika gde svaki red u fajlu sadrži podatke za jednog korisnika (ime i email), 
gde su podaci delimitirani zarezom
• 1.2 nakon što je fajl sa korisnicima upload-ovan omogućiti čuvanje svih korisnika koji se nalaze u fajlu
• 1.3 Prilikom dodavanja novog korisnika ili izmene postojećeg korisnika omogućiti proveru da li je prosleđena mail adresa
korisnika već uneta u bazu podataka. 
Ukoliko jeste zabraniti unos ili izmenu
• 1.4 u klasu UserEntity dodati polje troškovi. 
Prilikom unosa novog ili izmene postojećeg korisnika polje troškovi postaviti na vrednost 5000 ukoliko korisnik živi u Novom Sadu, 
ili 10000 ukoliko korisnik živi u Beogradu. U svim ostalim situacijama upisati vrednost 0 u polje troškovi
• 2.1 Kreirati REST endpoint koji omogućuje download fajla
• 2.2 Kreirati REST endpoint koji omogućuje downolad fajla u kome se nalaze podaci o svim korisnicima koji se nalaze u bazi podataka. 
Fajl je potrebno prvo napraviti na serverskoj strani tako da su podaci o korisnicima razdvojeni zarezom (csv fajl)
• 2.3 Proširiti zadatak 2.2 tako da REST endpoint prima listu svih atributa klase UserEntity koje korisnik želi da se nalaze u csv fajlu.
Ukoliko se u listi nalazi atribut koji nije deo UserEntity kase, vratiti odgovarajuću grešku korisniku. Ukoliko je prosleđena prazna lista, 
u fajl upisati sve atribute klase UserEntity
• 2.4 Napraviti endpoint za slanje MIME email-a sa attachment-om, 
gde ce parametri email-a (to, subject, text) fajl koji je attachment biti prosledjeni endpoint-u.
 */
