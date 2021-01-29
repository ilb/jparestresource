CREATE OR REPLACE FUNCTION char_to_uuid(x text) RETURNS bytea
    LANGUAGE 'sql'
    IMMUTABLE STRICT PARALLEL SAFE
AS $BODY$
SELECT decode(replace(x,'-',''), 'hex')
$BODY$;

CREATE OR REPLACE FUNCTION uuid_to_char(x bytea) RETURNS uuid
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL SAFE
AS $BODY$
SELECT encode(x, 'hex')::uuid
$BODY$;
