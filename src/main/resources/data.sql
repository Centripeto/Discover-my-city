insert into users(role, email, lastname, name, password, username) values ('ADMIN', 'admin@discover.it', 'admin',	'admin',	'$2a$10$cg7yEeBGZobjxheWoBoAM.Wzo.aXMdt1wR1aHbaNdlzZCIXrR2kL.',	'admin');
insert into users(role, email, lastname, name, password, username) values ('CONTRIBUTOR', 'contributor@discover.it', 'contributor',	'contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'contributor');
insert into users(role, email, lastname, name, password, username) values ('AUTH_CONTRIBUTOR', 'auth_contributor@discover.it', 'auth_contributor', 'auth_contributor', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'auth_contributor');
insert into users(role, email, lastname, name, password, username) values ('CURATORE', 'curatore@discover.it', 'curatore', 'curatore', '$2a$10$GxUtzAjn/JetF0qpEe3dnOsqzvUnXCHzJ.1qV8S2qyyaAp6jE2HLa', 'curatore');



insert into pois(approver, creator, latitude, longitude, description, name, status) values(null, 2, 43.30032173018055, 13.453416367615311, 'Piazza principale di macerata', 'Piazza Macerata', 'IN_APPROVAL');
insert into pois(approver, creator, latitude, longitude, description, name, status) values(null, 2, 43.307130023732846, 13.456182455701931, 'Sferisterio', 'Sferisterio', 'IN_APPROVAL');
insert into pois(approver, creator, latitude, longitude, description, name, status) values(null, 2, 43.307130023732846, 13.435784152495666, 'Stadio di Macerata', 'Stadio di Macerata', 'IN_APPROVAL');
insert into pois(approver, creator, latitude, longitude, description, name, status) values(null, 2, 43.299759543424706, 13.446813800445032, 'Giardini di Macerata', 'Giardini Diaz', 'IN_APPROVAL');
