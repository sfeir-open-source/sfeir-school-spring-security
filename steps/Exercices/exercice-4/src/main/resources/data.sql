INSERT INTO SCHOOL_USER (name, password, role, enabled) VALUES
('admin', '{noop}admin', 'ADMIN', true),
('tutor', '{noop}sfeir', 'SFEIR', true),
('student', '{noop}student', 'VISITOR', true),
('jean_dupont', '{bcrypt}$2a$10$MWUWxSRMQw3ieBynpUkDR.QK0oziv04RwMkRL.rdaHk0/V5fMEuAy', 'VISITOR', true), -- password: azerty
('john_doe', '{bcrypt}$2a$10$r1YH47BG1TLrLX2Vl1sS4.4rrQOh7XMe96nXPF6lvv2fLF4I9ztZ2', 'VISITOR', true),    -- password: qwerty
('disabled_user', '{noop}abc', 'VISITOR', false);