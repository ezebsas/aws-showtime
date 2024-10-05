ALTER TABLE event_seat
DROP
CONSTRAINT fkc66enersdqpdr160v88qyo6ff;

ALTER TABLE event_seat
DROP
CONSTRAINT fkdr3x3c6qcpguqltydver1h75;

ALTER TABLE seat
DROP
CONSTRAINT fkhxi4f44gv7s6u5a90ehw1g0x3;

ALTER TABLE event_section_ticket
DROP
CONSTRAINT fkia9rn7fdj82mo6c3vy8bmow6f;

ALTER TABLE seat_event_seats
DROP
CONSTRAINT fkjds6gif0x42sd6kbagaur7awr;

ALTER TABLE event_seat
DROP
CONSTRAINT fkn73ajeogver09hq5a1u5xv4y8;

ALTER TABLE event_seat
DROP
CONSTRAINT fko9e3b7wptbj0lwsufp3q6acxv;

ALTER TABLE seat_event_seats
DROP
CONSTRAINT fkqjm3f0qok47h0oyp07krwl685;

ALTER TABLE event_section_ticket
DROP
CONSTRAINT fksgeu43o8twrqlkuukymfk2ow3;

ALTER TABLE event_section
    ADD available_seats INTEGER;

ALTER TABLE ticket
    ADD event_section_id BIGINT;

ALTER TABLE ticket
    ADD quantity INTEGER;

ALTER TABLE event
    ADD max_seats_per_user INTEGER;

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_EVENT_SECTION FOREIGN KEY (event_section_id) REFERENCES event_section (id);

DROP TABLE event_seat CASCADE;

DROP TABLE event_section_ticket CASCADE;

DROP TABLE seat CASCADE;

DROP TABLE seat_event_seats CASCADE;

ALTER TABLE event_section
ALTER
COLUMN price TYPE DECIMAL USING (price::DECIMAL);

ALTER TABLE ticket
ALTER
COLUMN total TYPE DECIMAL USING (total::DECIMAL);