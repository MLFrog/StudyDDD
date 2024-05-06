CREATE TABLE sensor_data
(
  sensor_id   BIGSERIAL   NOT NULL,
  sensor_name VARCHAR(20) NULL,
  sensor_type VARCHAR(1)  NULL,
  data        VARCHAR(100)NULL,
  created_at TIMESTAMP    NULL,
  updated_at TIMESTAMP    NULL,
  PRIMARY KEY (sensor_id)
);

COMMENT ON TABLE sensor_data IS '센서 데이터 테이블';
COMMENT ON COLUMN sensor_data.sensor_id IS 'DB 식별자';
COMMENT ON COLUMN sensor_data.sensor_name IS '센서 이름';
COMMENT ON COLUMN sensor_data.sensor_type IS '센서 타입';
COMMENT ON COLUMN sensor_data.data IS '센서 데이터';
COMMENT ON COLUMN sensor_data.created_at IS '생성일';
COMMENT ON COLUMN sensor_data.updated_at IS '수정일';

CREATE TABLE sensor_log
(
  log_id    BIGSERIAL   NOT NULL,
  sensor_id BIGINT      NULL,
  owner     BIGINT      NULL,
  hash      VARCHAR(50) NULL,
  created_at TIMESTAMP    NULL,
  updated_at TIMESTAMP    NULL,
  PRIMARY KEY (log_id)
);

COMMENT ON TABLE sensor_log IS '센서 로그 테이블';
COMMENT ON COLUMN sensor_log.log_id IS 'DB 식별자';
COMMENT ON COLUMN sensor_log.sensor_id IS 'sensor 식별자';
COMMENT ON COLUMN sensor_log.owner IS '센서 관리자 식별자';
COMMENT ON COLUMN sensor_log.hash IS 'tmp 데이터';
COMMENT ON COLUMN sensor_log.created_at IS '생성일';
COMMENT ON COLUMN sensor_log.updated_at IS '수정일';

CREATE TABLE "data_user"
(
  id        BIGSERIAL   NOT NULL,
  username  VARCHAR(20) NOT NULL,
  password  VARCHAR(50) NULL,
  created_at TIMESTAMP    NULL,
  updated_at TIMESTAMP    NULL,
  PRIMARY KEY (id)
);

COMMENT ON TABLE "data_user" IS '유저 테이블';
COMMENT ON COLUMN "data_user".id IS 'DB 식별자';
COMMENT ON COLUMN "data_user".username IS '유저 이름';
COMMENT ON COLUMN "data_user".password IS '패스워드';
COMMENT ON COLUMN "data_user".created_at IS '생성일';
COMMENT ON COLUMN "data_user".updated_at IS '수정일';