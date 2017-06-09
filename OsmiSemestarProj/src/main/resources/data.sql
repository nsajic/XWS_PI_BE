--insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka1', 111, '111', '111');
--insert into banka (naziv_banke, sifra_banke, swift_kod, obracunski_racun) values ('Banka2', 222, '222', '222');

insert into delatnost(naziv_delatnosti) values ('Ugostiteljstvo');
insert into delatnost(naziv_delatnosti) values ('Turizam');
insert into delatnost(naziv_delatnosti) values ('Poljoprivreda');
insert into delatnost(naziv_delatnosti) values ('IT');

insert into klijent (tip_klijenta, ime, prezime, broj_licne_karte, datum_isteka_licne, telefon, adresa, jmbg, pib, delatnost_id) values ('F','Sima1', 'Simic1', 112345, '01-01-17', '1111', 'adresa1', 11111, null, null);
insert into klijent (tip_klijenta, ime, prezime, broj_licne_karte, datum_isteka_licne, telefon, adresa, jmbg, pib, delatnost_id) values ('F','Sima2', 'Simic2', 212345, '02-02-17', '2222', 'adresa1', 22222, null, null);

--insert into valuta (sifra_valute, naziv_valute) values ('111', '111')
--insert into valuta (sifra_valute, naziv_valute) values ('222', '222')
--insert into valuta (sifra_valute, naziv_valute) values ('333', '333')

--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('123', 'o', 1, 1, 1)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('122', 'o', 1, 2, 1)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('121', 'o', 1, 2, 1)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('1256', 'o', 1, 1, 2)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('4423', 'o', 1, 1, 1)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('12211', 'o', 2, 2, 1)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('12111', 'o', 1, 2, 1)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('12561', 'o', 2, 1, 2)
--insert into racun (broj_racuna, status_racuna, klijent_id, banka_id, valuta_id) values ('44231', 'o', 2, 1, 1)