DROP PROCEDURE IF EXISTS user_defined_security_question_find_all;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS user_defined_security_question_find_all(
    IN resource_id CHAR(36),
    IN page INT,
    IN size INT
)
COMMENT 'Find all user defined security questions'
BEGIN
	DECLARE offset INT;
	SET offset = (page - 1) * size;

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    IF page IS NULL OR page <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page cannot be a negative number or null';
    END IF;

    IF size IS NULL OR size <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'size cannot be a negative number or null';
    END IF;

    SELECT
        udsq.id AS id,
        udsq.resource_id AS resource_id,
        udsq.question AS security_question
    FROM user_defined_security_question udsq
    WHERE udsq.resource_id = resource_id
    ORDER BY udsq.created_at DESC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS user_defined_security_question_find_all_total;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS user_defined_security_question_find_all_total(resource_id CHAR(36))
RETURNS INT DETERMINISTIC
COMMENT 'Get total elements of user_defined_security_question_find_all'
BEGIN
    DECLARE total_elements INT;

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    SELECT COUNT(*)
    INTO total_elements
    FROM user_defined_security_question udsq
    WHERE udsq.resource_id = resource_id;

    RETURN total_elements;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS user_defined_security_question_find_answer;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS user_defined_security_question_find_answer(
    id CHAR(36),
    resource_id CHAR(36)
)
RETURNS CHAR(60) DETERMINISTIC
COMMENT 'Find the answer of specified resource id and security question id for checking answer'
BEGIN
    DECLARE answer CHAR(60) DEFAULT NULL;

    IF id IS NULL OR TRIM(id) = '' OR LENGTH(id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'id cannot be blank or null';
    END IF;

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    SELECT udsq.answer
    INTO answer
    FROM user_defined_security_question udsq
    WHERE udsq.id = id
    AND udsq.resource_id = resource_id;

    RETURN answer;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS user_defined_security_question_save;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS user_defined_security_question_save(
    IN resource_id CHAR(36),
    IN question VARCHAR(255),
    IN answer CHAR(60)
)
COMMENT 'save user defined security questions'
BEGIN

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    IF question IS NULL OR TRIM(question) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'question cannot be blank or null';
    END IF;

    IF answer IS NULL OR TRIM(answer) = '' OR LENGTH(answer) <> 60 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'answer cannot be blank or null';
    END IF;

    INSERT INTO user_defined_security_question (id, resource_id, question, answer)
    VALUES (UUID(), resource_id, question, answer);
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS user_defined_security_question_update_answer;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS user_defined_security_question_update_answer(
    IN id CHAR(36),
    IN resource_id CHAR(36),
    IN answer CHAR(60)
)
COMMENT 'update answer of user defined security questions'
BEGIN

    IF id IS NULL OR TRIM(id) = '' OR LENGTH(id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'id cannot be blank or null';
    END IF;

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    IF answer IS NULL OR TRIM(answer) = '' OR LENGTH(answer) <> 60 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'answer cannot be blank or null';
    END IF;

    UPDATE user_defined_security_question udsq
    SET udsq.answer = answer
    WHERE udsq.id = id
    AND udsq.resource_id = resource_id;
END //
DELIMITER ;