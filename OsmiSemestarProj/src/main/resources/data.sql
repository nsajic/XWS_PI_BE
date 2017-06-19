insert into rola (naziv) values ('FizickoLice');
insert into rola (naziv) values ('PravnoLice');
insert into rola (naziv) values ('MenadzerSistema');

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

insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values (true, 'F','Sima1', 'Simic1', 112345, '1111', 'adresa1', 11111, 1, 'k1@k1.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'kor');

insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax) values (true, 'M','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 3, 'k2@k2.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'kor', true, true, 25254525, 2332221, 'fax.fax');
--insert into klijent (tip, tip_klijenta, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, pib, delatnost_id) values (1, 'F','Sima2', 'Simic2', 212345, '2222', 'adresa1', 22222, null, null);

insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima3', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k3@k3.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima4', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k4@k4.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima5', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k5@k5.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
--insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values ('F','Sima1', 'Simic1', 112345, '1111', 'adresa1', 11111, 1, 'k1@k1.com', '1', 'kor');
--insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values ('F','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 1, 'k2@k2.com', '2', 'kor');


insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka1', 111, '111', '111');
insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka2', 222, '222', '222');


insert into valuta (sifra_valute, naziv_valute) values ('JEN', 'Japanski jen');