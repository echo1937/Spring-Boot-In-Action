INSERT INTO common.person (id, first_name, last_name) VALUES (1, 'John', 'Doe');
INSERT INTO common.person (id, first_name, last_name) VALUES (2, 'John', 'Lewis');

INSERT INTO common.address (id, city, state, street, zip_code, person_id) VALUES (1, 'Los Angeles', 'CA', 'Stanford Ave', '90001', 1);
INSERT INTO common.address (id, city, state, street, zip_code, person_id) VALUES (2, 'Abington', 'MA', 'Brockton Avenue', '02305', 2);
