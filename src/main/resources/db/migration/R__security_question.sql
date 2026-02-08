DROP PROCEDURE IF EXISTS security_question_find_all;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS security_question_find_all(
    IN name VARCHAR(255),
    IN page INT,
    IN size INT
)
COMMENT 'Find all security questions'
BEGIN
	DECLARE offset INT;
	SET offset = (page - 1) * size;

    IF page IS NULL OR page <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page cannot be a negative number or null';
    END IF;

    IF size IS NULL OR size <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'size cannot be a negative number or null';
    END IF;

    SELECT
        sq.id AS id,
        sq.name AS name
    FROM security_question sq
    WHERE sq.name LIKE CONCAT(IFNULL(name, ''), '%')
    ORDER BY sq.created_at DESC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS security_question_find_all_total;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS security_question_find_all_total(name VARCHAR(255))
RETURNS INT DETERMINISTIC
COMMENT 'Get total elements of security_question_find_all'
BEGIN
    DECLARE total_elements INT;

    SELECT COUNT(*)
    INTO total_elements
    FROM security_question sq
    WHERE sq.name LIKE CONCAT(IFNULL(name, ''), '%');

    RETURN total_elements;
END //
DELIMITER ;