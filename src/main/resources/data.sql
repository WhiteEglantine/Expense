-- admin/admin calculated by https://bcrypt-generator.com/
INSERT INTO users (id,username,password) VALUES (1,'admin', '$2a$12$jYJYfiKpP7QRK9nCg9E9ueoHB0vg546SyvgHAeLpGIESaNXg734JO');
INSERT INTO role (id,name) VALUES (1,'ADMIN');
INSERT INTO user_role (user_id,role_id) VALUES (1,1);
