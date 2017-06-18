insert into rola (naziv) values ('FizickoLice');
insert into rola (naziv) values ('PravnoLice');
insert into rola (naziv) values ('MenadzerSistema');

insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax) values (false, 'M','Sima1', 'Simic1', 112345, '1111', 'adresa1', 1, 3, 'k1@k1.com', '$2a$10$dY/AjjviMK7ekanzNADPd.jUlldHwfUIJcWFOXc7GyfUtFG/CBM6C', 'menadzer', true, true, 25254525, 2332221, 'fax.fax');
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax) values (false, 'F','Sima2', 'Simic2', 212345, '2222', 'adresa2', 2, 1, 'k2@k2.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'fizicko', true, true, 25254525, 2332221, 'fax.fax');
insert into korisnik (logovao_se, tip_korisnika, ime, prezime, broj_licne_karte, telefon, adresa, jmbg, rola_id, email, sifra, username, apr, op, pib, maticni_broj, fax, delatnost_id) values (false, 'P','Sima3', 'Simic3', 312345, '3333', 'adresa3', 3, 2, 'k3@k3.com', '$2a$12$/eDioOpiKrGQnDv53hPxIe8XDCJWP/Xe1fF6E5IrTVxuBozu9EX9G', 'pravno', true, true, 25254525, 2332221, 'fax.fax', 1);


insert into privilegija (naziv) values ('Banka:Dodaj');
insert into privilegija (naziv) values ('Banka:Izmeni');
insert into privilegija (naziv) values ('Banka:Obrisi');
insert into privilegija (naziv) values ('Banka:IzlistajPretrazi');

insert into rola_privilegija values (1, 1);
insert into rola_privilegija values (1, 2);
insert into rola_privilegija values (1, 3);
insert into rola_privilegija values (1, 4);

