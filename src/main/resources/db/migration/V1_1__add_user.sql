INSERT INTO social.users
VALUES ('1', 'User', 'Name', 30, '1993-01-01', '', 'Moscow',
        '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8')
ON CONFLICT (id) DO
    NOTHING;