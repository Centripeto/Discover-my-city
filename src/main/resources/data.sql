insert into users(role, email, lastname, name, password, username) values ('ADMIN', 'admin@discover.it', 'admin',	'admin',	'$2a$10$cg7yEeBGZobjxheWoBoAM.Wzo.aXMdt1wR1aHbaNdlzZCIXrR2kL.',	'admin');
insert into users(role, email, lastname, name, password, username) values ('CONTRIBUTOR', 'contributor@discover.it', 'contributor',	'contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'contributor');
insert into users(role, email, lastname, name, password, username) values ('AUTH_CONTRIBUTOR', 'auth_contributor@discover.it', 'auth_contributor', 'auth_contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'auth_contributor');
insert into users(role, email, lastname, name, password, username) values ('CURATORE', 'curatore@discover.it', 'curatore', 'curatore', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'curatore');

insert into municipalities(description, name) values ('Comune di macerata', 'Macerata');

insert into edges(latitude, longitude, municipality_id, position) values (43.3145, 13.44933, 1, 0);
insert into edges(latitude, longitude, municipality_id, position) values (43.29414,	13.39646, 1, 1);
insert into edges(latitude, longitude, municipality_id, position) values (43.27539, 13.4342, 1, 2);
insert into edges(latitude, longitude, municipality_id, position) values (43.29926, 13.48278, 1, 3);

insert into user_municipality(municipality_id, user_id) values (1, 2);
insert into user_municipality(municipality_id, user_id) values (1, 3);
insert into user_municipality(municipality_id, user_id) values (1, 4);

insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, null, 2, 43.30032173018055, 13.453416367615311, 'Piazza principale di macerata', 'Piazza Macerata', 'IN_APPROVAL');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, null, 2, 43.307130023732846, 13.456182455701931, 'Sferisterio', 'Sferisterio', 'IN_APPROVAL');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, null, 2, 43.307130023732846, 13.435784152495666, 'Stadio di Macerata', 'Stadio di Macerata', 'IN_APPROVAL');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, null, 2, 43.299759543424706, 13.446813800445032, 'Giardini di Macerata', 'Giardini Diaz', 'IN_APPROVAL');
