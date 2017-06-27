insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka1', 111, '111', '111');
insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka2', 222, '222', '222');

insert into rola (naziv) values ('aaaa');
insert into rola (naziv) values ('bbbb');
insert into rola (naziv) values ('BankarskiSluzbenik');

insert into privilegija (naziv) values ('Banka:View');	--1
insert into privilegija (naziv) values ('Banka:Dodaj');  --2
insert into privilegija (naziv) values ('Banka:Izmeni');  --3
insert into privilegija (naziv) values ('Banka:Obrisi');  --4
insert into privilegija (naziv) values ('Banka:IzlistajPretrazi');  --5

insert into privilegija (naziv) values ('Delatnost:View');  --6
insert into privilegija (naziv) values ('Delatnost:Dodaj');  --7
insert into privilegija (naziv) values ('Delatnost:Izmeni');  --8
insert into privilegija (naziv) values ('Delatnost:Obrisi');  --9
insert into privilegija (naziv) values ('Delatnost:IzlistajPretrazi');  --10

insert into privilegija (naziv) values ('Klijent:View');  --11
insert into privilegija (naziv) values ('Klijent:DodajPravnoFizicko');  --12
insert into privilegija (naziv) values ('Klijent:IzmeniPravnoFizicko');  --13
insert into privilegija (naziv) values ('Klijent:ObrisiPravnoFizicko');  --14
insert into privilegija (naziv) values ('Klijent:IzlistajPretraziPravnoFizicko');  --15

insert into privilegija (naziv) values ('Racun:View');  --16
insert into privilegija (naziv) values ('Racun:Dodaj');  --17
insert into privilegija (naziv) values ('Racun:Izmeni');  --18
insert into privilegija (naziv) values ('Racun:Obrisi');  --19
insert into privilegija (naziv) values ('Racun:IzlistajPretrazi');  --20
insert into privilegija (naziv) values ('Racun:DnevnoStanjeOdabranogRacuna');  --21

insert into privilegija (naziv) values ('Valuta:View');  --22
insert into privilegija (naziv) values ('Valuta:Dodaj');  --23
insert into privilegija (naziv) values ('Valuta:Izmeni');  --24
insert into privilegija (naziv) values ('Valuta:Obrisi');  --25
insert into privilegija (naziv) values ('Valuta:IzlistajPretrazi');  --26


insert into rola_privilegija values (1, 1);
insert into rola_privilegija values (1, 11);
insert into rola_privilegija values (1, 16);
insert into rola_privilegija values (1, 22);
insert into rola_privilegija values (1, 5);
insert into rola_privilegija values (1, 15);
insert into rola_privilegija values (1, 20);
insert into rola_privilegija values (1, 21);
insert into rola_privilegija values (1, 26);

insert into rola_privilegija values (3, 1);
insert into rola_privilegija values (3, 2);
insert into rola_privilegija values (3, 3);
insert into rola_privilegija values (3, 4);
insert into rola_privilegija values (3, 5);
insert into rola_privilegija values (3, 6);
insert into rola_privilegija values (3, 7);
insert into rola_privilegija values (3, 8);
insert into rola_privilegija values (3, 9);
insert into rola_privilegija values (3, 10);
insert into rola_privilegija values (3, 11);
insert into rola_privilegija values (3, 12);
insert into rola_privilegija values (3, 13);
insert into rola_privilegija values (3, 14);
insert into rola_privilegija values (3, 15);
insert into rola_privilegija values (3, 16);
insert into rola_privilegija values (3, 17);
insert into rola_privilegija values (3, 18);
insert into rola_privilegija values (3, 19);
insert into rola_privilegija values (3, 20);
insert into rola_privilegija values (3, 21);
insert into rola_privilegija values (3, 22);
insert into rola_privilegija values (3, 23);
insert into rola_privilegija values (3, 24);
insert into rola_privilegija values (3, 25);
insert into rola_privilegija values (3, 26);

insert into bankarski_sluzbenik (ime, prezime, email, sifra, telefon, adresa, jmbg, banka_id, rola_id) values ('Sasa', 'Momcilovic', 'a@gmail.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', '93849214', 'Vojvodjanska 14', '3948393', 1, 3);

insert into bankarski_sluzbenik (ime, prezime, email, sifra, telefon, adresa, jmbg, banka_id, rola_id) values ('Stefan', 'Bubanj', 'b@gmail.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', '93849214', 'Vojvodjanska 14', '3948393', 2, 3);


insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into valuta (sifra_valute, naziv_valute) values ('JEN', 'Japanski jen');
insert into valuta (sifra_valute, naziv_valute) values ('USD', 'Americki dolar');
insert into valuta (sifra_valute, naziv_valute) values ('RSD', 'Srpski dinar');

INSERT INTO `xws_pi_bezb`.`klijent` (`tip_klijenta`, `id`, `adresa`, `broj_licne_karte`, `email`, `ime`, `klijent_tip`, `prezime`, `telefon`) VALUES ('F', '1', 'jjjjjj', '324234', 'kk@g.com', 'stefan', 0, 'bubanj', '34');
INSERT INTO `xws_pi_bezb`.`fizicko_lice` (`ime_roditelja`,`jmbg`, `id`) values ('Nebo', '1233', '1');

INSERT INTO `xws_pi_bezb`.`klijent` (`tip_klijenta`, `id`, `adresa`, `broj_licne_karte`, `email`, `ime`, `klijent_tip`, `prezime`, `telefon`) VALUES ('P', '2', 'iiooi', '34234', 'kk@g.com', 'sasa', 1, 'momc', '44');
INSERT INTO `xws_pi_bezb`.`pravno_lice` (`pib`, `fax`, `maticni_broj`, `naziv`, `web`, `id`, `delatnost_id`) VALUES ('9889', '8998', '898989', 'jjjjj', 'www', '2', '1');


insert into racun (broj_racuna, status_racuna, banka_id, klijent_id, valuta_id, rezervisano) values ('111-2223334445556-78', 1, 1, 1, 1, 0);
insert into racun (broj_racuna, status_racuna, banka_id, klijent_id, valuta_id, rezervisano) values ('111-2223334445556-87', 1, 1, 1, 1, 0);
insert into racun (broj_racuna, status_racuna, banka_id, klijent_id, valuta_id, rezervisano) values ('222-2223334445556-78', 1, 2, 2, 1, 0);

insert into dnevno_stanje_racuna (datum, novo_stanje, prethodno_stanje, promet_na_teret, promet_u_korist, racun_id) values ('2017-04-15', 10000, 8000, 200, 500, 1);
insert into dnevno_stanje_racuna (datum, novo_stanje, prethodno_stanje, promet_na_teret, promet_u_korist, racun_id) values ('2017-04-18', 10000, 8000, 200, 500, 1);
insert into dnevno_stanje_racuna (datum, novo_stanje, prethodno_stanje, promet_na_teret, promet_u_korist, racun_id) values ('2017-04-19', 10000, 8000, 200, 500, 2);
insert into dnevno_stanje_racuna (datum, novo_stanje, prethodno_stanje, promet_na_teret, promet_u_korist, racun_id) values ('2017-04-20', 10000, 8000, 200, 500, 2);

	
--insert into dnevno_stanje_racuna (datum, novo_stanje, prethodno_stanje, promet_na_teret, promet_u_korist, racun_id) values ('2017-04-15', 10000, 8000, 200, 500, 1);




insert into analitika_izvoda (datum_analitike, datum_naloga, datum_valute, duznik_nalogodavac, iznos, model_odobrenja, model_zaduzenja, poziv_na_broj_odobrenja, poziv_na_broj_zaduzenja, primalac_poverilac, racun_duznika, racun_poverioca, smer, svrha_placanja, dnevno_stanje_racuna_id, valuta_id)
	values ('2017-04-15', '2017-04-15', '2017-04-15', 'Pera Peric', 3000, 97, 98,'8954-745826', '789655-4577', 'Sima Simic', '435-34543324-453', '321-4423254-324', 'izv', 'Kupljene stolice', 1, 1);


