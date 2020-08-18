INSERT INTO user (type, enabled, name, surname, email, password) VALUES ('ADMIN', true, 'Admin', 'Admin', 'admin@mail.com','$2a$10$aMBAEXjwqcavdNT373EM7.hlk2MWp1SNYCZ5NWk/Dytyf/xPsI02a');

INSERT INTO user (type, enabled,  name, surname, email, password, user_status) VALUES ('CAMPER', true, 'Camper', 'Camper', 'camper@mail.com','$2a$10$aMBAEXjwqcavdNT373EM7.hlk2MWp1SNYCZ5NWk/Dytyf/xPsI02a','ACTIVE');

INSERT INTO user (type, enabled,  name, surname, email, password, user_status, number_of_reports) VALUES ('CATERER', true, 'Caterer', 'Caterer', 'caterer@mail.com','$2a$10$aMBAEXjwqcavdNT373EM7.hlk2MWp1SNYCZ5NWk/Dytyf/xPsI02a','ACTIVE', 0);

INSERT INTO statistic (caterer_id) VALUES (3);

INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, tent_spots_number, longitude, latitude, price_per_day, rating, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje 1', 'Neki opis', 'Novi Sad', true, false, '2020-07-04 00:00:00', '2020-09-04 00:00:00', 1, 14.5, 23.5, 4500, 4.5, 3, 1);
INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, tent_spots_number, longitude, latitude, price_per_day, rating, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje 2', 'Neki opis', 'Novi Sad', true, false, '2020-07-04 00:00:00', '2020-10-04 00:00:00', 20, 14.5, 23.5, 4500, 3.5, 3, 1);
INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, tent_spots_number, longitude, latitude, price_per_day, rating, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje 3', 'Neki opis', 'Beograd', true, false, '2020-04-04 00:00:00', '2020-06-25 00:00:00', 20, 14.5, 23.5, 4500, 4.8, 3, 1);
INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, tent_spots_number, longitude, latitude, price_per_day, rating, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje 4', 'Neki opis', 'Beograd', true, false, '2020-04-04 00:00:00', '2020-06-25 00:00:00', 20, 14.5, 23.5, 4500, 2.5, 3, 1);
INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, tent_spots_number, longitude, latitude, price_per_day, rating, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje 5', 'Neki opis', 'Beograd', true, false, '2020-04-04 00:00:00', '2020-06-25 00:00:00', 20, 14.5, 23.5, 4500, 1.8, 3, 1);
INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, tent_spots_number, longitude, latitude, price_per_day, rating, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje 6', 'Neki opis', 'Beograd', true, false, '2020-04-04 00:00:00', '2020-06-25 00:00:00', 20, 14.5, 23.5, 4500, 3.6, 3, 1);
INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, tent_spots_number, longitude, latitude, price_per_day, rating, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje 7', 'Neki opis', 'Beograd', true, false, '2020-04-04 00:00:00', '2020-06-25 00:00:00', 20, 14.5, 23.5, 4500, 3.4, 3, 1);

INSERT INTO comment (content, comment_type, posting_date, camper_id, campsite_id, caterer_id) VALUES ('Mesto je odlicno', 'COMMENT','2020-07-04 00:00:00',2,1,3);

INSERT INTO reservation (start_date, end_date, camper_id, campsite_id, reservation_type) VALUES ('2020-08-04 00:00:00', '2020-08-27 00:00:00', 2, 1, 'ACCEPTED');

INSERT INTO favourite_campsites (campsite_id, camper_id) VALUES (1,2);

INSERT INTO activity_type (id, activities) VALUES (1, 'HIKING');

INSERT INTO authority (role) VALUES ('ROLE_ADMIN');
INSERT INTO authority (role) VALUES ('ROLE_CAMPER');
INSERT INTO authority (role) VALUES ('ROLE_CATERER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 3);