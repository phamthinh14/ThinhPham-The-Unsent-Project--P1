drop table if exists messages;
drop table if exists senders;

create table senders(sender_id integer not null,
		sender_name varchar(100),
		PRIMARY KEY(sender_id));

create table messages(receiver_id integer not null,
		 receiver_name varchar(100),
		 messages varchar(2000),
		 PRIMARY KEY(receiver_id),
		 CONSTRAINT fk_receiver FOREIGN KEY(receiver_id) REFERENCES senders(sender_id) ON DELETE CASCADE ON UPDATE CASCADE);

insert into senders(sender_id, sender_name) values(41, 'Ethan');
insert into senders(sender_id, sender_name) values(28, 'Nahte' );
insert into senders(sender_id, sender_name) values(3, 'Nathan' );
insert into senders(sender_id, sender_name) values(14, 'Thanks' );
insert into senders(sender_id, sender_name) values(7, 'Onlin Odin');

insert into messages(receiver_id, receiver_name, messages) values(41, 'Thark Low', 'I got option');
insert into messages(receiver_id, receiver_name, messages) values(28, 'Mr.Show', 'Aye stop showing off');
insert into messages(receiver_id, receiver_name, messages) values(3, 'Mr.Show', 'This is another for Show');
insert into messages(receiver_id, receiver_name, messages) values(14, 'Nwahs', 'I have been dealing with some wack');
insert into messages(receiver_id, receiver_name, messages) values(7, 'Shawn', 'My name is really Franco Info');