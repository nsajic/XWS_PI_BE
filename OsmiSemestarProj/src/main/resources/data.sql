insert into drzava (naziv_drzave) values ('Srbija')


insert into naseljeno_mesto (naziv, ptt_oznaka, drzava_id) values ('Zrenjanin', '23000', (select id from drzava where naziv_drzave = 'Srbija'))
insert into naseljeno_mesto (naziv, ptt_oznaka, drzava_id) values ('Novi Sad', '21000', (select id from drzava where naziv_drzave = 'Srbija'))


