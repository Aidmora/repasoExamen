CREATE TABLE MN_USUARIO(
    IdUsuario           INTEGER     NOT NULL    PRIMARY KEY  AUTOINCREMENT,
    NombreUsuario       TEXT        NOT NULL,
    ContrasenaUsuario   TEXT        NOT NULL
);
INSERT INTO MN_USUARIO(NombreUsuario, ContrasenaUsuario) VALUES("profe","827ccb0eea8a706c4c34a16891f84e7b");

INSERT INTO MN_USUARIO(NombreUsuario, ContrasenaUsuario) VALUES("ariel.mora@epn.edu.ec","6d95c5095fdd135589c328b6b2421285");

INSERT INTO MN_USUARIO(NombreUsuario, ContrasenaUsuario) VALUES("fernando.nagua@epn.edu.ec","7a50f881fd3ab79fd4afd48a9c0da490");

SELECT * FROM MN_USUARIO;

CREATE TABLE MN_ARSENALTIPO(
    IdArenalTipo        INTEGER     NOT NULL    PRIMARY KEY  AUTOINCREMENT,
    NombreArsenalTipo   TEXT        NOT NULL
);


CREATE TABLE MN_COORDENADAS(
    IdCoordenada    INTEGER     NOT NULL    PRIMARY KEY  AUTOINCREMENT,
    Coordenada      TEXT        NOT NULL
    );
SELECT * FROM MN_COORDENADAS;


CREATE TABLE MN_ARSENAL(
    IdArsenal       INTEGER     NOT NULL    PRIMARY KEY  AUTOINCREMENT,
    NombreArsenal   TEXT        NOT NULL

);
SELECT * FROM MN_ARSENAL;

CREATE TABLE MN_HORARIO(
    IdHorario       INTEGER     NOT NULL    PRIMARY KEY  AUTOINCREMENT,
    NombreDia       TEXT,
    Hora            TEXT                              
);
SELECT * FROM MN_HORARIO;
SELECT * FROM MN_ARSENALTIPO;