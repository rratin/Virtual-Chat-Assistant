create database register;
use register;
create table user (email varchar(50), password varchar(20));
ALTER TABLE user ADD id INT AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE user ADD fullname varchar(50);
UPDATE user SET fullname = 'sri' WHERE id = 2;
ALTER TABLE user ADD isActive TINYINT(1) DEFAULT 1;



CREATE TABLE notes (id INT AUTO_INCREMENT PRIMARY KEY,user_email VARCHAR(50),note_text TEXT,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE reminders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(50),
    message TEXT,
    reminder_time DATETIME
);
CREATE TABLE daily_tips (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tip TEXT,
    creation_date DATE
);
CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie TEXT
    
);

INSERT INTO daily_tips (tip) VALUES 
('Tip 1: Break your work into small steps to avoid feeling overwhelmed.'),
('Tip 2: Take regular breaks to improve concentration.'),
('Tip 3: Set clear goals to boost productivity.'),
('Tip 4: Maintain a healthy work-life balance for overall wellness.'),
('Tip 5: Practice mindfulness to enhance focus and reduce stress.');
INSERT INTO movies (movie) VALUES
('The Shawshank Redemption'),
('The Godfather'),
('The Dark Knight'),
('Pulp Fiction'),
('Schindler''s List'),
('Forrest Gump');

ALTER TABLE daily_tips DROP COLUMN creation_date;





UPDATE user SET isActive = 0 WHERE email = 'sri@gmail.com';


select * from user;
select * from notes;
select * from reminders;
select * from daily_tips;
select * from movies;

desc user