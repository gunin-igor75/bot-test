create  table pet(
                     id bigint primary key generated by default as identity,
                     name varchar(255) constraint name_unique unique not null,
                     adopted boolean,
                     report_id bigint constraint report_id references report unique
);