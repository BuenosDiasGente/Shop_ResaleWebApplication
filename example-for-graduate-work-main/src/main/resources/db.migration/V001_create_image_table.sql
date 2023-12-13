create TABLE IF NOT EXISTS image(
    id int4 PRIMARY KEY NOT NULL,
    image bytea
);
create sequence image_sequence start 1 increment 1;