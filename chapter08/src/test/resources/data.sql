INSERT INTO ARTISTS (NAME)
VALUES ('Threadbare Loaf');
INSERT INTO ARTISTS (NAME)
VALUES ('Therapy Zeppelin');
INSERT INTO ARTISTS (NAME)
VALUES ('Clancy In Silt');

INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'Thre%'),
        'Someone Stole the Flour', 4);
INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'Thre%'),
        'What Happened to Our First CD?', 17);
INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'The%'),
        'Medium', 4);
INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'C%'),
        'Igneous', 5);
