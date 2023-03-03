INSERT INTO user_account(username, email, phone)
	VALUES('fireball_enthusiast', 'fireballz@partyup.com', '') 
	, ('partyMOM', 'important@partyup.com', '(555) 555 - 5555')
	, ('devilishlyRoguish', 'edgemaster@partyup.com', '')	
	, ('tavern-song-fan-2', 'musician@partyup.com', '')	
	, ('Z.E.R.O', 'null@partyup.com', '')	
	, ('articulate-donut', 'dictionary@partyup.com', '');
	
INSERT INTO play_group(owner_id, title, game, max_player_count, accepting_new_players, description, game_location)
	VALUES(5, '5e - Mines of Phandelver', 'D&D 5e', 4, false, 'Running beginner module. New players welcome.', 'TBD')
	, (6, 'P2e Beginners Box', 'Pathfinder 2e', 4, true, 'Come to learn Pathfinder 2e', 'TBD');
	
INSERT INTO user_message(sender_id, receiver_id, message_content)
	VALUES(5, 1, 'Welcome to my 5e game!')
	, (5, 2, 'Good to have you!')
	, (4, 6, 'Will you have good bgm?');
	
INSERT INTO user_group(player_id, group_id)
	VALUES(1,1)
	, (2,1)
	, (3,1)
	, (4,1)
	, (3,2);
	
INSERT INTO join_request(player_id, group_id)
	VALUES (4,2)
		, (2,2);