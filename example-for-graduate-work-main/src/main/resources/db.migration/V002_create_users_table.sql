create TABLE IF NOT EXISTS users
(
    id    int4 PRIMARY KEY NOT NULL,
    username varchar(255),
    password varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone varchar(255),
    role varchar(255),
    image_id  int4 REFERENCES image(id)
);
create sequence users_sequence start 1 increment 1;