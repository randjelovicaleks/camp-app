insert into user (type, name, surname, email, password) values ("ADMIN", "Admin", "Admin", "admin@mail.com","sifra");

insert into user (type, name, surname, email, password, user_status) values ("CAMPER", "Camper", "Camper", "camper@mail.com","sifra","ACTIVE");

insert into user (type, name, surname, email, password, user_status) values ("CATERER", "Caterer", "Caterer", "caterer@mail.com","sifra","ACTIVE");

insert into statistic (caterer_id) values (3);

insert into campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, spots_number, longitude, latitude, caterer_id, statistic_id)
values ("Mesto za kampovanje", "Neki opis", "Novi Sad", true, false, '2020-07-04 00:00:00', '2020-09-04 00:00:00', 20, 14.5,23.5, 3,1);

insert into comment (content, comment_type, posting_date, camper_id, campsite_id, caterer_id) values ("Mesto je odlicno", "COMMENT",'2020-07-04 00:00:00',2,1,3);

insert into reservation (start_date, end_date, camper_id, campsite_id) values ('2020-08-04 00:00:00', '2020-08-27 00:00:00', 2, 1);

insert into favourite_campsites (campsite_id, camper_id) values (1,2);

insert into activity_type (id, activities) values (1, "HIKING");