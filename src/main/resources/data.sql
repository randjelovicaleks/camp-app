INSERT INTO user (type, enabled, name, surname, email, password) VALUES ('ADMIN', true, 'Admin', 'Admin', 'admin@mail.com','$2a$10$aMBAEXjwqcavdNT373EM7.hlk2MWp1SNYCZ5NWk/Dytyf/xPsI02a');

INSERT INTO user (type, enabled,  name, surname, email, password, user_status) VALUES ('CAMPER', true, 'Camper', 'Camper', 'camper@mail.com','$2a$10$aMBAEXjwqcavdNT373EM7.hlk2MWp1SNYCZ5NWk/Dytyf/xPsI02a','ACTIVE');

INSERT INTO user (type, enabled,  name, surname, email, password, user_status) VALUES ('CATERER', true, 'Caterer', 'Caterer', 'caterer@mail.com','$2a$10$aMBAEXjwqcavdNT373EM7.hlk2MWp1SNYCZ5NWk/Dytyf/xPsI02a','ACTIVE');

INSERT INTO statistic (caterer_id) VALUES (3);

INSERT INTO campsite (name, description, nearest_city, close_to_mountain, close_to_river, opening_date, closing_date, spots_number, longitude, latitude, caterer_id, statistic_id)
VALUES ('Mesto za kampovanje', 'Neki opis', 'Novi Sad', true, false, '2020-07-04 00:00:00', '2020-09-04 00:00:00', 20, 14.5,23.5, 3,1);

INSERT INTO comment (content, comment_type, posting_date, camper_id, campsite_id, caterer_id) VALUES ('Mesto je odlicno', 'COMMENT','2020-07-04 00:00:00',2,1,3);

INSERT INTO reservation (start_date, end_date, camper_id, campsite_id) VALUES ('2020-08-04 00:00:00', '2020-08-27 00:00:00', 2, 1);

INSERT INTO favourite_campsites (campsite_id, camper_id) VALUES (1,2);

INSERT INTO activity_type (id, activities) VALUES (1, 'HIKING');

INSERT INTO authority (role) VALUES ('ROLE_ADMIN');
INSERT INTO authority (role) VALUES ('ROLE_CAMPER');
INSERT INTO authority (role) VALUES ('ROLE_CATERER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 3);