DROP TABLE IF EXISTS user_group;
DROP TABLE IF EXISTS join_request;
DROP TABLE IF EXISTS user_message;
DROP TABLE IF EXISTS play_group;
DROP TABLE IF EXISTS user_account;

--Table for each individual user
CREATE TABLE user_account (
	user_id SERIAL NOT NULL PRIMARY KEY
	, username VARCHAR(30) NOT NULL UNIQUE
	, email VARCHAR(100) NOT NULL UNIQUE
	, phone VARCHAR(20)
	, pfp_url VARCHAR(250) 
);

--Table representing the games hosted by the users
CREATE TABLE play_group (
	group_id SERIAL NOT NULL PRIMARY KEY
	, owner_id INT NOT NULL
	, title VARCHAR(100) NOT NULL
	, game VARCHAR(100) NOT NULL
	, max_player_count INT NOT NULL
	, accepting_new_players BOOLEAN NOT NULL DEFAULT(true)
	, description VARCHAR(500)
	, game_location VARCHAR(100) DEFAULT('TBD')--might change value depending on Google Maps API
	
	, CONSTRAINT fk_group_user FOREIGN KEY (owner_id) REFERENCES user_account(user_id)
);

--Table to store user messages to each other
CREATE TABLE user_message (
	message_id SERIAL NOT NULL PRIMARY KEY
	, sender_id INT NOT NULL
	, receiver_id INT NOT NULL
	, send_date TIMESTAMP NOT NULL DEFAULT(LOCALTIMESTAMP)
	, message_content VARCHAR(255) NOT NULL
	
	, CONSTRAINT fk_sender_user FOREIGN KEY (sender_id) REFERENCES user_account(user_id)
	, CONSTRAINT fk_receiver_user FOREIGN KEY (receiver_id) REFERENCES user_account(user_id)
);

--Table to Associate users with games they are in as players
CREATE TABLE user_group (
	player_id INT NOT NULL
	, group_id INT NOT NULL
	
	, CONSTRAINT pk_player_group_id PRIMARY KEY (player_id, group_id)
	, CONSTRAINT fk_user_group_user FOREIGN KEY (player_id) REFERENCES user_account(user_id)
	, CONSTRAINT fk_user_group_group FOREIGN KEY (group_id) REFERENCES play_group(group_id)
);

CREATE TABLE join_request (
	player_id INT NOT NULL
	, group_id INT NOT NULL
	
	, CONSTRAINT pk_player_game_id PRIMARY KEY (player_id, group_id)
);


