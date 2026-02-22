--
-- PostgreSQL database dump
--

-- Dumped from database version 11.21
-- Dumped by pg_dump version 11.21

-- Started on 2026-02-22 20:41:34

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
-- TOC entry 2849 (class 1262 OID 16385)
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
-- TOC entry 2711 (class 2606 OID 24747)
-- Name: department department_pkey; Type: CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (department_id);


--
-- TOC entry 2713 (class 2606 OID 24762)
-- Name: semester semester_pkey; Type: CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.semester
    ADD CONSTRAINT semester_pkey PRIMARY KEY (semester_id);


--
-- TOC entry 2719 (class 2606 OID 32832)
-- Name: student_details student_details_pkey; Type: CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.student_details
    ADD CONSTRAINT student_details_pkey PRIMARY KEY (student_id, subject_id);


--
-- TOC entry 2717 (class 2606 OID 32821)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (student_id);


--
-- TOC entry 2715 (class 2606 OID 24767)
-- Name: subject subject_pkey; Type: CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.subject
    ADD CONSTRAINT subject_pkey PRIMARY KEY (subject_id);


--
-- TOC entry 2721 (class 2606 OID 32822)
-- Name: student student_department_id_fkey; Type: FK CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.student
    ADD CONSTRAINT student_department_id_fkey FOREIGN KEY (department_id) REFERENCES student_core.department(department_id);


--
-- TOC entry 2722 (class 2606 OID 32833)
-- Name: student_details student_details_student_id_fkey; Type: FK CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.student_details
    ADD CONSTRAINT student_details_student_id_fkey FOREIGN KEY (student_id) REFERENCES student_core.student(student_id);


--
-- TOC entry 2720 (class 2606 OID 24768)
-- Name: subject subject_semester_id_fkey; Type: FK CONSTRAINT; Schema: student_core; Owner: postgres
--

ALTER TABLE ONLY student_core.subject
    ADD CONSTRAINT subject_semester_id_fkey FOREIGN KEY (semester_id) REFERENCES student_core.semester(semester_id);


-- Completed on 2026-02-22 20:41:35

--
-- PostgreSQL database dump complete
--

