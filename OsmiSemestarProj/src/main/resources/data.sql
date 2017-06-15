/*insert into rola (naziv) values ('FizickoLice');
insert into rola (naziv) values ('PravnoLice');

insert into privilegija (naziv) values ('Banka:Dodaj');
insert into privilegija (naziv) values ('Banka:Izmeni');
insert into privilegija (naziv) values ('Banka:Obrisi');
insert into privilegija (naziv) values ('Banka:IzlistajPretrazi');

insert into rola_privilegija values (1, 1);
insert into rola_privilegija values (1, 2);
--insert into rola_privilegija values (1, 3);
insert into rola_privilegija values (1, 4);

--insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
--insert into delatnost(naziv_delatnosti) values ('Turizam');
--insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
--insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values ('F','Sima1', 'Simic1', 112345, '1111', 'adresa1', 11111, 1, 'k1@k1.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'kor');
insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax) values ('P','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k2@k2.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'kor', true, true, 25254525, 2332221, 'fax.fax');

insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values ('P','Sima3', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k3@k3.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values ('P','Sima4', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k4@k4.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values ('P','Sima5', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k5@k5.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
--insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values ('F','Sima1', 'Simic1', 112345, '1111', 'adresa1', 11111, 1, 'k1@k1.com', '1', 'kor');
--insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values ('F','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 1, 'k2@k2.com', '2', 'kor');


--insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka1', 111, '111', '111');
--insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka2', 222, '222', '222');


insert into valuta (sifra_valute, naziv_valute) values ('JEN', 'Japanski jen');


insert into valuta (sifra_valute, naziv_valute) values ('USD', 'Dinar');
insert into valuta (sifra_valute, naziv_valute) values ('EUR', 'Evro');
insert into valuta (sifra_valute, naziv_valute) values ('USA', 'Dolar');

--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('123', 1, 1, 1, 1);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('122', 1, 1, 2, 1);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('121', 0, 1, 2, 1);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('1256', 0, 1, 1, 2);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('4423', 0, 1, 1, 1);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('12211', 0, 2, 2, 1);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('12111', 0, 1, 2, 1);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('12561', 1, 2, 1, 2);
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('44231', 0, 2, 1, 1);
*/



insert into rola (naziv) values ('FizickoLice');
insert into rola (naziv) values ('PravnoLice');

insert into privilegija (naziv) values ('Banka:Dodaj');
insert into privilegija (naziv) values ('Banka:Izmeni');
insert into privilegija (naziv) values ('Banka:Obrisi');
insert into privilegija (naziv) values ('Banka:IzlistajPretrazi');

insert into rola_privilegija values (1, 1);
insert into rola_privilegija values (1, 2);
insert into rola_privilegija values (1, 3);
insert into rola_privilegija values (1, 4);

--insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
--insert into delatnost(naziv_delatnosti) values ('Turizam');
--insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
--insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values (false, 'F','Sima1', 'Simic1', 112345, '1111', 'adresa1', 11111, 1, 'k1@k1.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'kor');


insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax) values (false, 'P','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k2@k2.com', '1', 'kor', true, true, 25254525, 2332221, 'fax.fax');
--insert into klijent (tip, tip_klijenta, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, pib, delatnost_id) values (1, 'F','Sima2', 'Simic2', 212345, '2222', 'adresa1', 22222, null, null);

insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima3', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k3@k3.com', '2', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima4', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k4@k4.com', '2', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima5', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k5@k5.com', '2', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
--insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values ('F','Sima1', 'Simic1', 112345, '1111', 'adresa1', 11111, 1, 'k1@k1.com', '1', 'kor');
--insert into korisnik (tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username) values ('F','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 1, 'k2@k2.com', '2', 'kor');


--insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka1', 111, '111', '111');
--insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka2', 222, '222', '222');


insert into valuta (sifra_valute, naziv_valute) values ('JEN', 'Japanski jen');



