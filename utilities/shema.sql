CREATE TABLE MN_USUARIO(
    IdUsuario           INTEGER     PRIMARY KEY NOT NULL AUTOINCREMENT,
    NombreUsuario       TEXT        NOT NULL,
    ContrasenaUsuario   TEXT        NOT NULL
);
INSERT INTO MN_USUARIO(NombreUsuario, ContrasenaUsuario) VALUES("profe","827ccb0eea8a706c4c34a16891f84e7b");

INSERT INTO MN_USUARIO(NombreUsuario, ContrasenaUsuario) VALUES("ariel.mora@epn.edu.ec","6d95c5095fdd135589c328b6b2421285");

INSERT INTO MN_USUARIO(NombreUsuario, ContrasenaUsuario) VALUES("fernando.nagua@epn.edu.ec","7a50f881fd3ab79fd4afd48a9c0da490");

SELECT * FROM MN_USUARIO;