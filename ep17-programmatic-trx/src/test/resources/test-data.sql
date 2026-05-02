SET REFERENTIAL_INTEGRITY FALSE;

DROP TABLE IF EXISTS transaction_transfer;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS transaction_seq;
DROP TABLE IF EXISTS account;

SET REFERENTIAL_INTEGRITY TRUE;

CREATE TABLE account (
	code VARCHAR(4) PRIMARY KEY,
	name VARCHAR(40) NOT NULL,
	amount INTEGER NOT NULL,
	created_at TIMESTAMP,
	last_upd_at TIMESTAMP
);

insert into account (code, name, amount) values ('0001', 'Aung Aung', 100000);
insert into account (code, name, amount) values ('0002', 'Thidar', 100000);

CREATE TABLE transaction_seq (
	trx_date DATE PRIMARY KEY,
	seq_num INTEGER
);


CREATE TABLE transaction (
	trx_id VARCHAR(12) PRIMARY KEY,
	trx_type VARCHAR(20) NOT NULL,
	issuer VARCHAR(4) NOT NULL,
	amount INTEGER NOT NULL,
	issue_at TIMESTAMP NOT NULL,
	remark VARCHAR(255),
	FOREIGN KEY (issuer) REFERENCES account(code)
);

CREATE TABLE transaction_transfer (
	trx_id VARCHAR(12) PRIMARY KEY,
	receiver VARCHAR(4) NOT NULL,
	FOREIGN KEY (trx_id) REFERENCES transaction(trx_id),
	FOREIGN KEY (receiver) REFERENCES account(code)
);
