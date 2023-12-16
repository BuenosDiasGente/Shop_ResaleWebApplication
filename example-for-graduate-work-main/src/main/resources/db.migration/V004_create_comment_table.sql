create TABLE IF NOT EXISTS comment
(
    id         int4 PRIMARY KEY NOT NULL,
    created_at varchar(255),
    price      int4,
    ad_id      int4 REFERENCES ad (id),
    users_id   int4 REFERENCES users (id)
);
create sequence comment_sequence start 1 increment 1;