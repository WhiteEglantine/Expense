-- admin/admin calculated by https://bcrypt-generator.com/
INSERT INTO role (id,name,privileges) VALUES (1,'ADMIN',json_array('SUPER','WRITE','READ'));
INSERT INTO role (id,name,privileges) VALUES (2,'USER',json_array('WRITE','READ'));
-- INSERT INTO users (id,username,password,role_id) VALUES (1,'admin', '$2a$12$jYJYfiKpP7QRK9nCg9E9ueoHB0vg546SyvgHAeLpGIESaNXg734JO',1);

