insert into users(role, email, lastname, name, password, username) values ('ADMIN', 'admin@discover.it', 'admin',	'admin',	'$2a$10$cg7yEeBGZobjxheWoBoAM.Wzo.aXMdt1wR1aHbaNdlzZCIXrR2kL.',	'admin');
insert into users(role, email, lastname, name, password, username) values ('CONTRIBUTOR', 'contributor@discover.it', 'contributor',	'contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'contributor');
insert into users(role, email, lastname, name, password, username) values ('AUTH_CONTRIBUTOR', 'auth_contributor@discover.it', 'auth_contributor', 'auth_contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'auth_contributor');
insert into users(role, email, lastname, name, password, username) values ('CURATORE', 'curatore@discover.it', 'curatore', 'curatore', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'curatore');
insert into users(role, email, lastname, name, password, username) values ('CONTRIBUTOR', 'contributor.camerino@discover.it', 'contributor',	'contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'contributor.camerino');
insert into users(role, email, lastname, name, password, username) values ('AUTH_CONTRIBUTOR', 'auth_contributor.camerino@discover.it', 'auth_contributor', 'auth_contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'auth_contributor.camerino');
insert into users(role, email, lastname, name, password, username) values ('CURATORE', 'curatore.camerino@discover.it', 'curatore', 'curatore', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'curatore.camerino');



insert into municipalities(description, name) values ('Comune di macerata', 'Macerata');
insert into municipalities(description, name) values ('Comune di camerino', 'Camerino');

insert into edges(latitude, longitude, municipality_id, position) values (43.3145, 13.44933, 1, 0);
insert into edges(latitude, longitude, municipality_id, position) values (43.29414,	13.39646, 1, 1);
insert into edges(latitude, longitude, municipality_id, position) values (43.27539, 13.4342, 1, 2);
insert into edges(latitude, longitude, municipality_id, position) values (43.29926, 13.48278, 1, 3);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.14370843705047,	13.063859939575195,	2,	0);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.140577078142925,	13.054418563842775,	2,	1);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.135942372626026,	13.053903579711916,	2,	2);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.130931484996964,	13.054933547973635,	2,	3);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.12667190770115,	13.062229156494142,	2,	4);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.12529374565466,	13.069438934326174,	2,	5);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.12867645161504,	13.079395294189453,	2,	6);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.13268534236973,	13.075103759765627,	2,	7);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.13782134963578,	13.078708648681642,	2,	8);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.143082178099625,	13.082656860351564,	2,	9);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.14884351845969,	13.07853698730469,	2,	10);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.15235015543291,	13.068237304687502,	2,	11);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.15472954451308,	13.05931091308594,	2,	12);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.15097257204435,	13.053989410400392,	2,	13);
insert into edges(latitude, longitude, municipality_id, position) VALUES(43.144960935706116,	13.052959442138672,	2,	14);



insert into user_municipality(municipality_id, user_id) values (1, 2);
insert into user_municipality(municipality_id, user_id) values (1, 3);
insert into user_municipality(municipality_id, user_id) values (1, 4);
insert into user_municipality(municipality_id, user_id) values (2, 5);
insert into user_municipality(municipality_id, user_id) values (2, 6);
insert into user_municipality(municipality_id, user_id) values (2, 7);

insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, 4, 2, 43.30032173018055, 13.453416367615311, 'Piazza principale di macerata', 'Piazza Macerata', 'APPROVED');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, 4, 2, 43.299150501906546, 13.455934524536133, 'Sferisterio', 'Sferisterio', 'APPROVED');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, null, 2, 43.307130023732846, 13.435784152495666, 'Stadio di Macerata', 'Stadio di Macerata', 'IN_APPROVAL');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values(1, 4, 2, 43.299759543424706, 13.446813800445032, 'Giardini di Macerata', 'Giardini Diaz', 'APPROVED');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values (2, 6, 6, 43.1357641, 13.0683092, 'Camerino centro', 'Centro', 'APPROVED');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values (2, 6,	6, 43.139919472395675, 13.067507743835451, 'polo universitario', 'Polo universitario', 'APPROVED');
insert into pois(municipality_id, approver, creator, latitude, longitude, description, name, status) values (2, null, 6, 43.13822845371114, 13.071756362915039, 'Punto non approvato', 'Altro punto non approvato', 'IN_APPROVAL');