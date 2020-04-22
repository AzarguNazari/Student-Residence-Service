-- SEQUENCE: public.ext_id_generator

-- DROP SEQUENCE public.ext_id_generator;

/*---------------------------------------------EXTERNAL ID GENERATOR SEQUENCE----------------------------------------*/
CREATE  SEQUENCE IF NOT EXISTS public.ext_id_generator
    INCREMENT 43
    START 230
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.ext_id_generator
    OWNER TO tester;



/*---------------------------------------------ANNOUNCEMENT TYPES INSERT--------------------------------------------*/	
INSERT INTO public.announcement_type(
	id, name )
	SELECT 1, 'New HA Available' WHERE NOT EXISTS (SELECT * from public.announcement_type where id = 1);
INSERT INTO public.announcement_type(
	id, name )
	SELECT 2, 'General Information' WHERE NOT EXISTS (SELECT * from public.announcement_type where id = 2);
INSERT INTO public.announcement_type(
	id, name )
	SELECT 3, 'Maintenance Notice' WHERE NOT EXISTS (SELECT * from public.announcement_type where id = 3);	

/*-----------------------------------SET SEQUENCE AS DEFAULT VALUE----------------------------------------------------*/
ALTER TABLE public.announcement ALTER COLUMN external_id SET DEFAULT nextVal('ext_id_generator');


/*---------------------------------------------ANNOUNCEMENTS INSERT--------------------------------------------*/
INSERT INTO public.announcement(
	id,  creation_date, description, external_id, priority, announcement_type_id, admin_id)
	SELECT 7, '2020-01-22 15:24:26.252', 'Ann1', 488, 0, 2, 1 WHERE NOT EXISTS (SELECT * FROM public.announcement WHERE id = 7);
	

INSERT INTO public.announcement(
	id,  creation_date, description, external_id, priority, announcement_type_id, admin_id)
	SELECT 8, '2020-01-22 15:25:26.252', 'Ann2', 531, 2, 3, 1 WHERE NOT EXISTS (SELECT * FROM public.announcement WHERE id = 8);


INSERT INTO public.announcement(
	id, appliance_serial_number, creation_date, description, external_id, priority, announcement_type_id, admin_id)
	SELECT 9,273, '2020-01-22 15:26:26.252', 'Ann3', 574, 1, 1, 1 WHERE NOT EXISTS (SELECT * FROM public.announcement WHERE id = 9);
	

INSERT INTO public.announcement(
	id, creation_date, description, external_id, priority, announcement_type_id, admin_id)
	SELECT 10, '2020-01-22 15:27:26.252', 'Ann4', 617, 0, 2, 1 WHERE NOT EXISTS (SELECT * FROM public.announcement WHERE id = 10);
	
INSERT INTO public.announcement(
	id, creation_date, description, external_id, priority, announcement_type_id, admin_id)
	SELECT 11, '2020-01-22 15:27:26.252', 'Ann5', 660, 0, 2, 1 WHERE NOT EXISTS (SELECT * FROM public.announcement WHERE id = 11);	
	
INSERT INTO public.announcement(
	id, appliance_serial_number, creation_date, description, external_id, priority, announcement_type_id, admin_id)
	SELECT 12,230, '2020-01-22 15:28:26.252', 'Ann6', 703, 1, 1, 1 WHERE NOT EXISTS (SELECT * FROM public.announcement WHERE id = 12);
	
/*------------------------------------------------------REPLIES INSERT----------------------------------------------*/

INSERT INTO public.reply(
	id, creation_date, message_text, announcement_id, user_id)
	SELECT 17, '2020-01-22 15:29:26.252', 'OK', 7, 2 WHERE NOT EXISTS (SELECT * FROM public.reply WHERE id = 17);


INSERT INTO public.reply(
	id, creation_date, message_text, announcement_id, user_id)
	SELECT 18, '2020-01-22 15:30:26.252', 'TESTED', 8, 2 WHERE NOT EXISTS (SELECT * FROM public.reply WHERE id = 18);
	

INSERT INTO public.reply(
	id, creation_date, message_text, announcement_id, user_id)
	SELECT 19, '2020-01-22 15:31:26.252', 'DONE', 9, 2 WHERE NOT EXISTS (SELECT * FROM public.reply WHERE id = 19);	
	
ALTER SEQUENCE ext_id_generator START WITH 1000 INCREMENT 100;

/*-----------------------------------------------TO BE RUN EXTERNALLY ON PG ADMIN----------------------------------*/
ALTER SEQUENCE hibernate_sequence START WITH 100 INCREMENT 10;
	
