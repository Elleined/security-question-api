DROP PROCEDURE IF EXISTS pre_defined_security_question_find_all;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS pre_defined_security_question_find_all(
    IN resource_id CHAR(36),
    IN page INT,
    IN size INT
)
COMMENT 'Find all pre defined security questions'
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
        pdsq.resource_id AS resource_id,
        pdsq.security_question_id AS security_question_id,
        sq.name AS security_question
    FROM pre_defined_security_question pdsq
    JOIN security_question sq ON sq.id = pdsq.security_question_id
    WHERE pdsq.resource_id = resource_id
    ORDER BY pdsq.created_at DESC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS pre_defined_security_question_find_all_total;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS pre_defined_security_question_find_all_total(resource_id CHAR(36))
RETURNS INT DETERMINISTIC
COMMENT 'Get total elements of pre_defined_security_question_find_all'
BEGIN
    DECLARE total_elements INT;

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    SELECT COUNT(*)
    INTO total_elements
    FROM pre_defined_security_question pdsq
    WHERE pdsq.resource_id = resource_id;

    RETURN total_elements;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS pre_defined_security_question_find_answer;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS pre_defined_security_question_find_answer(
    resource_id CHAR(36),
    security_question_id CHAR(36)
)
RETURNS CHAR(60) DETERMINISTIC
COMMENT 'Find the answer of specified resource id and security question id for checking answer'
BEGIN
    DECLARE answer CHAR(60) DEFAULT NULL;

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    IF security_question_id IS NULL OR TRIM(security_question_id) = '' OR LENGTH(security_question_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'security_question_id cannot be blank or null';
    END IF;

    SELECT pdsq.answer
    INTO answer
    FROM pre_defined_security_question pdsq
    WHERE pdsq.resource_id = resource_id
    AND pdsq.security_question_id = security_question_id;

    RETURN answer;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS pre_defined_security_question_exists;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS pre_defined_security_question_exists(
    resource_id CHAR(36),
    security_question_id CHAR(36)
)
RETURNS BOOLEAN DETERMINISTIC
COMMENT 'Returns true if combination of resource id and security question id exists, otherwise false'
BEGIN
    DECLARE is_exists BOOLEAN DEFAULT FALSE;

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    IF security_question_id IS NULL OR TRIM(security_question_id) = '' OR LENGTH(security_question_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'security_question_id cannot be blank or null';
    END IF;

    SELECT EXISTS (
        SELECT 1
        FROM pre_defined_security_question pdsq
        WHERE pdsq.resource_id = resource_id
        AND pdsq.security_question_id = security_question_id
    ) INTO is_exists;

    RETURN is_exists;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS pre_defined_security_question_save;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS pre_defined_security_question_save(
    IN resource_id CHAR(36),
    IN security_question_id CHAR(36),
    IN answer CHAR(60)
)
COMMENT 'save pre defined security questions'
BEGIN

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    IF security_question_id IS NULL OR TRIM(security_question_id) = '' OR LENGTH(security_question_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'security_question_id cannot be blank or null';
    END IF;

    IF answer IS NULL OR TRIM(answer) = '' OR LENGTH(answer) <> 60 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'answer cannot be blank or null';
    END IF;

    INSERT INTO pre_defined_security_question (id, resource_id, security_question_id, answer)
    VALUES (UUID(), resource_id, security_question_id, answer);
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS pre_defined_security_question_update_answer;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS pre_defined_security_question_update_answer(
    IN resource_id CHAR(36),
    IN security_question_id CHAR(36),
    IN answer CHAR(60)
)
COMMENT 'update answer of pre defined security questions'
BEGIN

    IF resource_id IS NULL OR TRIM(resource_id) = '' OR LENGTH(resource_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'resource_id cannot be blank or null';
    END IF;

    IF security_question_id IS NULL OR TRIM(security_question_id) = '' OR LENGTH(security_question_id) <> 36 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'security_question_id cannot be blank or null';
    END IF;

    IF answer IS NULL OR TRIM(answer) = '' OR LENGTH(answer) <> 60 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'answer cannot be blank or null';
    END IF;

    UPDATE pre_defined_security_question pdsq
    SET pdsq.answer = answer
    WHERE pdsq.resource_id = resource_id
    AND pdsq.security_question_id = security_question_id;
END //
DELIMITER ;