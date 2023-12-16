create TABLE IF NOT EXISTS ad
(
    id          int4 PRIMARY KEY NOT NULL,
    title       varchar(255),
    price       int4,
    description varchar(255),
    users_id    int4 REFERENCES users (id),
    image_id    int4 REFERENCES image (id)
);
create sequence ad_sequence start 1 increment 1;