--
-- PostgreSQL database dump
--

-- Dumped from database version 11.21
-- Dumped by pg_dump version 11.21

-- Started on 2026-02-18 01:02:18

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'WIN1252';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2867 (class 1262 OID 16385)
-- Name: student_management; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE student_management WITH TEMPLATE = template0 ENCODING = 'WIN1252' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE student_management OWNER TO postgres;

\connect student_management

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'WIN1252';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16387)
-- Name: student_core; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA student_core;


ALTER SCHEMA student_core OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 24576)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
                                   department_id character varying(255) NOT NULL,
                                   department_name character varying(255) NOT NULL
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 24581)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
                                student_id character varying(255) NOT NULL,
                                section_id character varying(64),
                                department_id character varying(255) NOT NULL
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 24602)
-- Name: student_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_details (
                                        student_id character varying(255) NOT NULL,
                                        subject_id character varying(255) NOT NULL,
                                        marks integer,
                                        student_name character varying(255) NOT NULL,
                                        department_id character varying(255) NOT NULL
);


ALTER TABLE public.student_details OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 24743)
-- Name: department; Type: TABLE; Schema: student_core; Owner: postgres
--

CREATE TABLE student_core.department (
                                         department_id character varying(10) NOT NULL,
                                         department_name character varying(100) NOT NULL
);


ALTER TABLE student_core.department OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24758)
-- Name: semester; Type: TABLE; Schema: student_core; Owner: postgres
--

CREATE TABLE student_core.semester (
                                       semester_id character varying(10) NOT NULL,
                                       semester_name character varying(20) NOT NULL
);


ALTER TABLE student_core.semester OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 32817)
-- Name: student; Type: TABLE; Schema: student_core; Owner: postgres
--

CREATE TABLE student_core.student (
                                      student_id character varying(10) NOT NULL,
                                      student_name character varying(50) NOT NULL,
                                      department_id character varying(10) NOT NULL,
                                      section character varying(5) NOT NULL
);


ALTER TABLE student_core.student OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 32827)
-- Name: student_details; Type: TABLE; Schema: student_core; Owner: postgres
--

CREATE TABLE student_core.student_details (
                                              student_id character varying(10) NOT NULL,
                                              subject_id character varying(10) NOT NULL,
                                              marks integer DEFAULT 0 NOT NULL
);


ALTER TABLE student_core.student_details OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 24763)
-- Name: subject; Type: TABLE; Schema: student_core; Owner: postgres
--

CREATE TABLE student_core.subject (
                                      subject_id character varying(10) NOT NULL,
                                      subject_name character varying(50) NOT NULL,
                                      semester_id character varying(10) NOT NULL
);


ALTER TABLE student_core.subject OWNER TO postgres;

--
-- TOC entry 2854 (class 0 OID 24576)
-- Dependencies: 197
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2855 (class 0 OID 24581)
-- Dependencies: 198
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2856 (class 0 OID 24602)
-- Dependencies: 199
-- Data for Name: student_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.student_details (student_id, subject_id, marks, student_name, department_id) VALUES ('ST_001', 'SUB_003', 87, 'Arun', 'CSE');
INSERT INTO public.student_details (student_id, subject_id, marks, student_name, department_id) VALUES ('ST_001', 'SUB_004', 87, 'Arun', 'CSE');
INSERT INTO public.student_details (student_id, subject_id, marks, student_name, department_id) VALUES ('ST_002', 'SUB_004', 87, 'Arun', 'CSE');
INSERT INTO public.student_details (student_id, subject_id, marks, student_name, department_id) VALUES ('ST_002', 'SUB_005', 87, 'Arun', 'CSE');


--
-- TOC entry 2857 (class 0 OID 24743)
-- Dependencies: 200
-- Data for Name: department; Type: TABLE DATA; Schema: student_core; Owner: postgres
--

INSERT INTO student_core.department (department_id, department_name) VALUES ('DEPT_CS', 'Computer Science and Engineering');
INSERT INTO student_core.department (department_id, department_name) VALUES ('DEPT_ECE', 'Electronics and Communication Engineering');
INSERT INTO student_core.department (department_id, department_name) VALUES ('DEPT_IT', 'Information Technology');


--
-- TOC entry 2858 (class 0 OID 24758)
-- Dependencies: 201
-- Data for Name: semester; Type: TABLE DATA; Schema: student_core; Owner: postgres
--

INSERT INTO student_core.semester (semester_id, semester_name) VALUES ('SEM_1', 'Semester One');
INSERT INTO student_core.semester (semester_id, semester_name) VALUES ('SEM_2', 'Semester Two');


--
-- TOC entry 2860 (class 0 OID 32817)
-- Dependencies: 203
-- Data for Name: student; Type: TABLE DATA; Schema: student_core; Owner: postgres
--

INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_001', 'john', 'DEPT_CS', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_002', 'raju', 'DEPT_ECE', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_003', 'robin', 'DEPT_IT', 'B');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_004', 'raghu', 'DEPT_CS', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_005', 'zoro', 'DEPT_ECE', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_006', 'aniruth', 'DEPT_IT', 'B');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_007', 'deepithi', 'DEPT_CS', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_008', 'deepa', 'DEPT_ECE', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_009', 'haritha', 'DEPT_IT', 'B');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_010', 'harika', 'DEPT_CS', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_011', 'lekha', 'DEPT_ECE', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_012', 'radha', 'DEPT_IT', 'B');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_013', 'reshma', 'DEPT_CS', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_014', 'prasad', 'DEPT_ECE', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_015', 'bhima', 'DEPT_IT', 'B');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_016', 'sankar', 'DEPT_CS', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_017', 'raja', 'DEPT_ECE', 'A');
INSERT INTO student_core.student (student_id, student_name, department_id, section) VALUES ('ST_018', 'roja', 'DEPT_IT', 'B');


--
-- TOC entry 2861 (class 0 OID 32827)
-- Dependencies: 204
-- Data for Name: student_details; Type: TABLE DATA; Schema: student_core; Owner: postgres
--

INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_001', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_002', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_003', 50);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_004', 77);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_005', 80);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_006', 44);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_007', 55);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_008', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_009', 88);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_010', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_011', 91);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_001', 'SUB_012', 54);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_001', 40);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_002', 38);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_003', 50);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_004', 60);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_005', 32);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_006', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_007', 31);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_008', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_017', 74);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_018', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_019', 23);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_002', 'SUB_020', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_001', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_002', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_003', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_004', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_005', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_006', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_007', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_008', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_013', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_014', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_015', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_003', 'SUB_016', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_001', 83);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_002', 25);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_003', 36);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_004', 98);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_005', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_006', 74);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_007', 75);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_008', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_009', 32);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_010', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_011', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_004', 'SUB_012', 54);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_001', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_002', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_003', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_004', 98);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_005', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_006', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_007', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_008', 91);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_017', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_018', 45);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_019', 34);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_005', 'SUB_020', 23);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_001', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_002', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_003', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_004', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_005', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_006', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_007', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_008', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_013', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_014', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_015', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_006', 'SUB_016', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_001', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_002', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_003', 50);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_004', 77);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_005', 80);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_006', 44);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_007', 55);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_008', 88);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_009', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_010', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_011', 91);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_007', 'SUB_012', 92);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_001', 40);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_002', 38);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_003', 50);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_004', 60);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_005', 32);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_006', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_007', 31);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_008', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_017', 54);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_018', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_019', 69);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_008', 'SUB_020', 45);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_001', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_002', 35);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_003', 73);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_004', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_005', 88);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_006', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_007', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_008', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_013', 23);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_014', 45);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_015', 66);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_009', 'SUB_016', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_001', 83);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_002', 25);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_003', 36);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_004', 98);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_005', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_006', 74);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_007', 75);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_008', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_009', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_010', 23);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_011', 78);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_010', 'SUB_012', 95);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_001', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_002', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_003', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_004', 98);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_005', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_006', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_007', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_008', 91);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_017', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_018', 73);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_019', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_011', 'SUB_020', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_001', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_002', 0);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_003', 67);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_004', 88);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_005', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_006', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_007', 80);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_008', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_013', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_014', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_015', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_012', 'SUB_016', 78);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_001', 40);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_002', 38);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_003', 50);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_004', 60);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_005', 32);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_006', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_007', 31);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_008', 56);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_009', 67);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_010', 59);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_011', 51);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_013', 'SUB_012', 62);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_001', 97);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_002', 34);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_003', 74);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_004', 75);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_005', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_006', 75);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_007', 73);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_008', 54);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_017', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_018', 54);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_019', 39);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_014', 'SUB_020', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_001', 80);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_002', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_003', 70);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_004', 60);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_005', 50);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_006', 70);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_007', 20);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_008', 30);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_013', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_014', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_015', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_015', 'SUB_016', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_001', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_002', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_003', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_004', 98);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_005', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_006', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_007', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_008', 91);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_009', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_010', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_011', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_016', 'SUB_012', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_001', 34);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_002', 36);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_003', 75);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_004', 73);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_005', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_006', 43);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_007', 76);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_008', 87);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_017', 48);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_018', 75);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_019', 65);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_017', 'SUB_020', 54);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_001', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_002', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_003', 89);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_004', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_005', 90);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_006', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_007', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_008', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_013', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_014', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_015', 100);
INSERT INTO student_core.student_details (student_id, subject_id, marks) VALUES ('ST_018', 'SUB_016', 100);


--
-- TOC entry 2859 (class 0 OID 24763)
-- Dependencies: 202
-- Data for Name: subject; Type: TABLE DATA; Schema: student_core; Owner: postgres
--

INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_001', 'English', 'SEM_1');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_002', 'Physics', 'SEM_1');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_003', 'Chemistry', 'SEM_1');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_004', 'Mathematics Basics', 'SEM_1');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_005', 'Graphics Basics', 'SEM_1');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_006', 'Computer Basics', 'SEM_1');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_008', 'EEE Basics', 'SEM_1');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_009', 'CS Sub1', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_010', 'CS Sub2', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_011', 'CS Sub3', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_012', 'CS Sub4', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_013', 'IT Sub1', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_014', 'IT Sub2', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_015', 'IT Sub3', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_016', 'IT Sub4', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_017', 'ECE Sub1', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_018', 'ECE Sub2', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_019', 'ECE Sub3', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_020', 'ECE Sub4', 'SEM_2');
INSERT INTO student_core.subject (subject_id, subject_name, semester_id) VALUES ('SUB_007', 'Mech Basics', 'SEM_1');


-- Completed on 2026-02-18 01:02:21

--
-- PostgreSQL database dump complete
--

