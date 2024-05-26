CREATE TABLE IF NOT EXISTS Course (
	id serial NOT NULL UNIQUE,
	uuid uuid NOT NULL UNIQUE,
	name varchar(256) NOT NULL,
	description text,
	author_id int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Section (
	id serial NOT NULL UNIQUE,
	uuid uuid NOT NULL UNIQUE,
	name varchar(256) NOT NULL,
	description text,
	course_id int NOT NULL,
	position int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Topic (
	id serial NOT NULL UNIQUE,
	uuid uuid NOT NULL UNIQUE,
	name varchar(256) NOT NULL,
	description text,
	section_id int NOT NULL,
	position int NOT NULL,
	next_topic_id int,
	prev_topic_id int,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Block (
	id serial NOT NULL UNIQUE,
	uuid uuid NOT NULL UNIQUE,
	name varchar(256) NOT NULL,
	topic_id int NOT NULL,
	position int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Resource (
	id serial NOT NULL UNIQUE,
	uuid uuid NOT NULL UNIQUE,
	type text NOT NULL,
	content text NOT NULL,
	block_id int NOT NULL,
	position int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Author (
	id serial NOT NULL UNIQUE,
	uuid uuid NOT NULL UNIQUE,
	name varchar(256) NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE Course ADD CONSTRAINT Course_fk4 FOREIGN KEY (author_id) REFERENCES Author(id);
ALTER TABLE Section ADD CONSTRAINT Section_fk4 FOREIGN KEY (course_id) REFERENCES Course(id);
ALTER TABLE Topic ADD CONSTRAINT Topic_fk4 FOREIGN KEY (section_id) REFERENCES Section(id);

ALTER TABLE Topic ADD CONSTRAINT Topic_fk6 FOREIGN KEY (next_topic_id) REFERENCES Topic(id);

ALTER TABLE Topic ADD CONSTRAINT Topic_fk7 FOREIGN KEY (prev_topic_id) REFERENCES Topic(id);
ALTER TABLE Block ADD CONSTRAINT Block_fk3 FOREIGN KEY (topic_id) REFERENCES Topic(id);
ALTER TABLE Resource ADD CONSTRAINT Resource_fk4 FOREIGN KEY (block_id) REFERENCES Block(id);

-- Sequence for Author table
CREATE SEQUENCE author_SEQ
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Sequence for Course table
CREATE SEQUENCE course_SEQ
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Sequence for Section table
CREATE SEQUENCE section_SEQ
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Sequence for Topic table
CREATE SEQUENCE topic_SEQ
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Sequence for Block table
CREATE SEQUENCE block_SEQ
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Sequence for Resource table
CREATE SEQUENCE resource_SEQ
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;