#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
DROP FUNCTION IF EXISTS char_to_uuid;
CREATE FUNCTION `char_to_uuid`(`s` CHAR(36)) RETURNS binary(16)
    DETERMINISTIC SQL SECURITY INVOKER
RETURN UNHEX(CONCAT(LEFT(s, 8), MID(s, 10, 4), MID(s, 15, 4), MID(s, 20, 4), RIGHT(s, 12)));


DROP FUNCTION IF EXISTS uuid_to_char;
DELIMITER ${symbol_dollar}${symbol_dollar}
CREATE FUNCTION `uuid_to_char`(`b` BINARY(16)) RETURNS char(36) CHARSET utf8
    DETERMINISTIC  SQL SECURITY INVOKER
BEGIN
  DECLARE hex CHAR(32);
  SET hex = HEX(b);
  RETURN LOWER(CONCAT(LEFT(hex, 8), '-', MID(hex, 9,4), '-', MID(hex, 13,4), '-', MID(hex, 17,4), '-', RIGHT(hex, 12)));
END${symbol_dollar}${symbol_dollar}

DELIMITER ;