DROP INDEX IF EXISTS artist_name;
DROP TABLE IF EXISTS artists;

CREATE TABLE IF NOT EXISTS ARTISTS
(
  id   BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  name VARCHAR(64) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS artist_name
  ON ARTISTS(name);