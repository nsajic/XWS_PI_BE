insert into rola (naziv) values ('FizickoLice');
insert into rola (naziv) values ('PravnoLice');
insert into rola (naziv) values ('MenadzerSistema');

insert into privilegija (naziv) values ('Banka:Dodaj');
insert into privilegija (naziv) values ('Banka:Izmeni');
--insert into privilegija (naziv) values ('Banka:Obrisi');
insert into privilegija (naziv) values ('Banka:IzlistajPretrazi');

insert into rola_privilegija values (1, 1);
insert into rola_privilegija values (1, 2);
insert into rola_privilegija values (1, 3);
--insert into rola_privilegija values (1, 4);

insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username)                                  values (false, 'M','Sima1', 'Simic1', 112345, '1111', 'adresa1', 11111, 3, 'k1@k1.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'kor');
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax) values (false, 'F','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 1, 'k2@k2.com', '1', 'kor', true, true, 25254525, 2332221, 'fax.fax');
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax) values (false, 'F','Sima2', 'Simic2', 112345, '1111', 'adresa1', 11111, 1, 'k6@k6.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'kor', true, true, 25254525, 2332221, 'fax.fax');
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'F','Sima3', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k3@k3.com', '2', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'F','Sima4', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k4@k4.com', '2', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima5', 'Simic2', 112345, '1111', 'adresa1', 11111, 2, 'k5@k5.com', '2', 'kor', true, true, 25254525, 2332221, 'fax.fax', 1);

insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka1', 111, '111', '111');
insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka2', 222, '222', '222');

insert into valuta (sifra_valute, naziv_valute) values ('JEN', 'Japanski jen');
insert into valuta (sifra_valute, naziv_valute) values ('DIN', 'Dinar');

insert into racun (broj_racuna, status_racuna, fizicko_lice_id, pravno_lice_id, banka_id, valuta_id) values ('123', 1, 1, null, 1, 1);
insert into racun (broj_racuna, status_racuna, fizicko_lice_id, pravno_lice_id, banka_id, valuta_id) values ('123', 1, 1, null, 1, 2);

insert into dnevno_stanje_racuna (datum, prethodno_stanje, promet_na_teret, promet_u_korist, novo_stanje, racun_id) values ('2017-06-17 00:00:00', 10000, 1000, 2000, 11000, 1)
