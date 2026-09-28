CREATE TABLE guest_session (
                               id BIGSERIAL PRIMARY KEY,
                               table_id BIGINT NOT NULL REFERENCES cafe_table(id) ON DELETE CASCADE,
                               started_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                               closed_at TIMESTAMPTZ,
                               status VARCHAR(20) NOT NULL DEFAULT 'OPEN'
);

CREATE INDEX idx_guest_session_table ON guest_session(table_id);
CREATE INDEX idx_guest_session_status ON guest_session(status);
CREATE INDEX idx_guest_session_open_by_table ON guest_session(table_id, status);