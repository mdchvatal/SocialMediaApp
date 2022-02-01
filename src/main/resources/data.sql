INSERT INTO timeline (timeline_id) VALUES (1);
INSERT INTO timeline (timeline_id) VALUES (2);

INSERT INTO user (user_name, name, timeline_id) VALUES ('matt', 'Matt Chvatal', 1);
INSERT INTO user (user_name, name, timeline_id) VALUES ('spencer', 'Spencer LaFarge', 2);

INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'Isn\'t everything so WONDERFUL?!', 1, 0, 0);
INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'So wonderful, Indeed', 2, 0, 0);
INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'I can hardly believe my eyes', 1, 0, 0);
INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'If only Papa could see me now', 1, 0, 0);
INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'What da heq?!', 1, 0, 0);
INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'Man, that\'s cool', 2, 0, 0);
INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'I do not believe in Santa Claus', 1, 0, 0);
INSERT INTO message (date, message, posting_user_user_id, number_of_likes, number_of_comments) VALUES (now(), 'Ribbit Ribbit', 1, 0, 0);

INSERT INTO timeline_messages (timeline_id, message_id) VALUES (1, 1);
INSERT INTO timeline_messages (timeline_id, message_id) VALUES (2, 2);
INSERT INTO timeline_messages (timeline_id, message_id) VALUES (1, 3);
INSERT INTO timeline_messages (timeline_id, message_id) VALUES (1, 4);
INSERT INTO timeline_messages (timeline_id, message_id) VALUES (1, 5);
INSERT INTO timeline_messages (timeline_id, message_id) VALUES (2, 6);
INSERT INTO timeline_messages (timeline_id, message_id) VALUES (1, 7);
INSERT INTO timeline_messages (timeline_id, message_id) VALUES (1, 8);


