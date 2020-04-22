-- SEQUENCE: public.ext_id_generator

-- DROP SEQUENCE public.ext_id_generator;
/*---------------------------------------------EXTERNAL ID GENERATOR SEQUENCE----------------------------------------*/
CREATE SEQUENCE IF NOT EXISTS public.ext_id_generator
    INCREMENT 43
    START 230
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.ext_id_generator
    OWNER TO tester;

/*---------------------------------------------USER ROLES INSERT----------------------------------------------------*/
INSERT INTO public.role(id, name) SELECT 1,'ROLE_ADMIN' WHERE NOT EXISTS (SELECT * from public.role where id = 1);
INSERT INTO public.role(id, name) SELECT 2,'ROLE_STUDENT' WHERE NOT EXISTS (SELECT * from public.role where id = 2);

/*---------------------------------------------USER DETAILS INSERT----------------------------------------------------*/
INSERT INTO public.user_details(
	id, first_name, last_name, password, username, role_id)
	SELECT 1, 'Adam', 'Smith', '$2y$10$sAZOCeiXfgn.WaPLBHMXmeQR2lMEHE0OR598qsjHPShpdS3ea6js2','adams@g.com',  1 
	WHERE NOT EXISTS (SELECT * from public.user_details where id = 1);
INSERT INTO public.user_details(
	id, first_name, last_name, password, username, role_id)
	SELECT 2, 'John', 'Kelly', '$2y$10$sAZOCeiXfgn.WaPLBHMXmeQR2lMEHE0OR598qsjHPShpdS3ea6js2','johnk@g.com',  2 
	WHERE NOT EXISTS (SELECT * from public.user_details where id = 2);	
INSERT INTO public.user_details(
	id, first_name, last_name, password, username, role_id)
	SELECT 3, 'Lisa', 'Adams', '$2y$10$sAZOCeiXfgn.WaPLBHMXmeQR2lMEHE0OR598qsjHPShpdS3ea6js2','lisaa@g.com',  1
	WHERE NOT EXISTS (SELECT * from public.user_details where id = 3);
	
/*-----------------------------------------STUDENT DETAILS INSERT----------------------------------------------------*/	
INSERT INTO public.student(
	id, join_date, room_number, semester, user_id)
	SELECT 1, '2020-01-01 00:00:00',200, 'FIRST', 2 WHERE NOT EXISTS (SELECT * FROM public.student where id = 1);
	
/*-----------------------------------SET SEQUENCE AS DEFAULT VALUE----------------------------------------------------*/
ALTER TABLE public.appliance ALTER COLUMN serial_number SET DEFAULT nextVal('ext_id_generator');

ALTER TABLE public.rent ALTER COLUMN serial_number SET DEFAULT nextVal('ext_id_generator');


/*-----------------------------------------APPLIANCE TYPES INSERT----------------------------------------------------*/	
INSERT INTO public.appliance_type(
	id, name)
	SELECT 1, 'Vacuum' WHERE NOT EXISTS (SELECT * from public.appliance_type where id = 1);
INSERT INTO public.appliance_type(
	id, name)
	SELECT 2, 'Clothes Iron' WHERE NOT EXISTS (SELECT * from public.appliance_type where id = 2);
INSERT INTO public.appliance_type(
	id, name)
	SELECT 3, 'Waffle Iron' WHERE NOT EXISTS (SELECT * from public.appliance_type where id = 3);
INSERT INTO public.appliance_type(
	id, name)
	SELECT 4, 'Party Room' WHERE NOT EXISTS (SELECT * from public.appliance_type where id = 4);	
INSERT INTO public.appliance_type(
	id, name)
	SELECT 5, 'Table Room' WHERE NOT EXISTS (SELECT * from public.appliance_type where id = 5);

/*-----------------------------------------APPLIANCES INSERT----------------------------------------------------*/	
INSERT INTO public.appliance(
	id, available_appliances, created_on, max_time, model_name, price_per_day, serial_number, state, appliance_type)
	SELECT 1, 14, '2020-01-22 15:10:26.252', 10, 'A1', 12, 230, 0, 1 WHERE NOT EXISTS (SELECT * FROM public.appliance WHERE id = 1);
	
INSERT INTO public.appliance(
	id, available_appliances, created_on, max_time, model_name, price_per_day, serial_number, state, appliance_type)
	SELECT 2, 15, '2020-01-22 15:11:26.252', 12, 'A2', 10, 273, 0, 2 WHERE NOT EXISTS (SELECT * FROM public.appliance WHERE id = 2);
	
INSERT INTO public.appliance(
	id, available_appliances, created_on, max_time, model_name, price_per_day, serial_number, state, appliance_type)
	SELECT 3, 10, '2020-01-22 15:12:26.252', 15, 'A3', 5, 316, 1, 3 WHERE NOT EXISTS (SELECT * FROM public.appliance WHERE id = 3);
	
INSERT INTO public.appliance(
	id, available_appliances, created_on, max_time, model_name, price_per_day, serial_number, state, appliance_type)
	SELECT 4, 12, '2020-01-22 15:13:26.252', 11, 'A4', 8, 359, 0, 4 WHERE NOT EXISTS (SELECT * FROM public.appliance WHERE id = 4);
	
INSERT INTO public.appliance(
	id, available_appliances, created_on, max_time, model_name, price_per_day, serial_number, state, appliance_type)
	SELECT 5, 13, '2020-01-22 15:14:26.252', 16, 'A5', 16, 402, 0, 5 WHERE NOT EXISTS (SELECT * FROM public.appliance WHERE id = 5);
	
INSERT INTO public.appliance(
	id, available_appliances, created_on, max_time, model_name, price_per_day, serial_number, state, appliance_type)
	SELECT 6, 13, '2020-01-22 15:14:26.252', 16, 'A6', 16, 445, 0, 1 WHERE NOT EXISTS (SELECT * FROM public.appliance WHERE id = 6);
	
/*-------------------------------------------------RENTS INSERT----------------------------------------------------*/
INSERT INTO public.rent(
	id, creation_date, number_of_appliances, rent_amount, selected_end_date, serial_number, status, appliance_id, student_id)
	SELECT 13, '2020-01-22 15:20:26.252', 5, 36, '2020-01-27 23:59:59.999', 746, 0, 1, 1 WHERE NOT EXISTS (SELECT * FROM public.rent WHERE id = 13);
	

INSERT INTO public.rent(
	id, creation_date, number_of_appliances, rent_amount, selected_end_date, serial_number, status, appliance_id, student_id)
	SELECT 14, '2020-01-22 15:22:26.252', 8, 20, '2020-01-26 23:59:59.999', 789, 0, 5, 1 WHERE NOT EXISTS (SELECT * FROM public.rent WHERE id = 14);
	

INSERT INTO public.rent(
	id, creation_date, number_of_appliances, rent_amount, selected_end_date, serial_number, status, appliance_id, student_id)
	SELECT 15, '2020-01-22 15:23:26.252', 1, 12, '2020-01-25 23:59:59.999', 832, 0, 1, 1 WHERE NOT EXISTS (SELECT * FROM public.rent WHERE id = 15);

	
ALTER SEQUENCE ext_id_generator START WITH 1000 INCREMENT 100;

/*-----------------------------------------------TO BE RUN EXTERNALLY ON PG ADMIN----------------------------------*/
ALTER SEQUENCE hibernate_sequence START WITH 100 INCREMENT 10;